import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FlySat extends WebPage{
    

    private String Address =  "https://www.flysat.com/en/satellitelist";
    
    private ArrayList<Satellite> sats = new ArrayList<>();

    public FlySat(String url) {
        super(url);
        this.Address=url;
    }
    
    @Override
    public ArrayList<Satellite> getSatellites(){
        URL url = null;
        
        try{
            url = new URL(Address);
            
            
            System.out.println("Trying connect to: " + url);
            Connection.Response r = Jsoup.connect(url.toString())
                              .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                              .header("Accept-Encoding", "gzip, deflate")
                              .header("Content-Type", "text/html; charset=ISO-8859-1")
                              .referrer("http://www.google.com")
                              .ignoreContentType(true)
                              .maxBodySize(0)
                              .timeout(600000)
                              .execute();
            System.out.println("Successfully connected to " + url);
            System.out.println("Parsing the content");
            Document doc = r.parse();
            
            // zbieramy satelity z lewych tabelek:
            System.out.println("getting satellite names from the left Tables");
            
            Elements left = doc.select("td[valign=top]");
            Elements rows = left.select("table tr");
            
            if (rows.size() > 1) {
                getInfoFromRows(rows);
            } else {
                System.out.println("No rows found on the left");
            }
            
            // robimy teraz to samo dla prawych tabelek:
            System.out.println("getting satellites names from the right Tables");
            
            Elements right = doc.select("td[style=vertical-align: top;]");
            rows = right.select("table tr");
            
            if (rows.size() > 1) {
                getInfoFromRows(rows);
            } else {
                System.out.println("No rows found on the right");
            }
            
            //wyswietlmy ile satelit zebralismy
            System.out.println("Returning all the satellites: "+sats.size());
            
            //zwracamy satelity z naszej metody
            return sats;
        }	
        catch (MalformedURLException e) {

            return null;
        }
        catch (UnknownHostException e) {

            return null;
        }
        catch (IOException e) {

            return null;
        }
    }
    

    private void getInfoFromRows(Elements rows){
        // rowspan = ile kolejnych satelit nalezy do clustera , clust= przypisz wtedy ta nazwe clustera do nich
        int rowspan=0;
        String clust="";
        // petla by sprawdzic kazda tabele od gory na dol, kazda tabela zawiera columny | Cluster Satellite Position incl. Band UpDated |
        for (int i = 1; i < rows.size(); i++) {
            //zmienna, na wypadek erroru/bledu, by wiedziec przy jakim row i co zawieral, ze wywolal blad
            Elements ifErr = null;
            try{
                //Bierzemy tabele o indeksach od 1 do max
                Element row = rows.get(i);
                //Niektore tabele pomiedzy satelitami maja informacje o alpsat i jej stronie, ktore pomijamy
                if(row.toString().contains("alpsat"))continue;
                //Z kazdej tabelki bierzemy wszystkie kolumny
                Elements cols = row.select("td");
                //Oczywiscie sprawdzamy, czy nie sa puste
                if(cols.size()>0 ||  !cols.isEmpty()){
                    //zapisujemy nasze kolumny, do debuga na wypadek errora, by mozna bylo je wyswietlic i sprawdzic co spowodowalo te bledy
                    ifErr = cols;
                    
                    //Sprawdzamy informacje dla clustera jako col o indeksie 0, jednak kazdy kolejny satelita ktory tez
                    // nalezy do tego clastera, musi byc przesuniety o 1 indeks w lewo, poniewaz u niego indeks 0 to juz nie cluter, a nazwa
                    String cluster = cols.get(0).text();
                    int ignore=0;
                    if(!cluster.isEmpty() && rowspan==0){
                        String strRowspan = cols.get(0).attr("rowspan");
                        System.out.println("There is a cluster!!!: "+cluster + " , it contains "+strRowspan+ " satellites");
                        rowspan=Integer.parseInt(strRowspan);
                        ignore=1;
                        clust=cluster;
                        // w skrocie: jezeli jest cluster to ustawiamy wartosc rowspan, czyli informacja ile ponizszych satelit jest w tym
                        // clusterze. Kazdemu ponizszemu satelicie dodaj cluster jako nazwa naszego clust, a reszte indeksow przesun o 1.
                        // do tego musimy zignorowac przesuniecie indeksow dla pierwszego satelity w clusterze.
                    }
                    
                    // tutaj wszytujemy Stringi kolejnych 5 kolumn, by potem je obrobic i zapisac
                    String satellite = cols.get(1-(rowspan>0?1:0)+ignore).text();
                    String position = cols.get(2-(rowspan>0?1:0)+ignore).text();
                    String incl = cols.get(3-(rowspan>0?1:0)+ignore).text();
                    String band = cols.get(4-(rowspan>0?1:0)+ignore).text();
                    String updated = cols.get(5-(rowspan>0?1:0)+ignore).text();
                    // pamietamy by po kazdym satelicie ktore znajduje sie w clusterze zmniejszaj rowspan i nadpisywac cluster.
                    if(rowspan>=1){rowspan--;cluster=clust;}
                    
                    //Wyswietlamy informacje o kazdym wczytanym satelicie, do analizy
                    System.out.println("Cluster: " + cluster + " Satellite: " + satellite + " Position: " + position + " incl: " + incl + " Band: " + band + " UpDated: " + updated);
                    
                    //Tutaj bedziemy rozdzielac nazwy satelity ze stringa satellite do listy nazw satNames
                    ArrayList<String> satNames = new ArrayList<>();
                    
                    //Najpierw sprawdzamy czy zawiera dodatkowa nazwe w nawiasach. Chcemy tez dodac ta nazwe na koncu
                    String addLast=null;
                    if(satellite.contains("(")){
                        //jezeli tak, to rozdzielamy od nawiasa do przed ostatniego chara i zapisujemy
                        addLast=(satellite.substring(satellite.indexOf('(')+1 ,satellite.length()-1));
                        //nadpisujemy teraz naszego stringa z nazwami, by moc przejsc do kolejnego kroku
                        satellite=satellite.substring(0,satellite.indexOf('('));
                    }
                    // w kolejnym kroku rozpatrujemy przypadek rodzielenia nazw satelit przez '/'.
                    String[] nn= satellite.split("/");
                    //jest to o tyle przyjemne w tym przypadku, iz usunelismy wczesniej nawiasy, wiec jedyne co pozostanie nam w tym
                    // przypadku, to albo pojedynczy string z indeksem 0, albo wiele stringow oddzielonych '/'
                    for(String nname:nn){
                        //kazde z przypadkow dodajemy do naszych nazw
                        satNames.add(nname);
                    }
                    //mozemy teraz dodac, o ile nie byla pusta, nazwe dodatkowa w nawiasach.
                    if(addLast!=null)satNames.add(addLast);
                    System.out.println(satNames);// dla testow mozemy je wyswietlic
                    //Tworzymy nowego satelite, ktoremu nadajemy nazwe, poniewaz nie istnieje pusty konstruktor.
                    Satellite sat = new Satellite(satNames.get(0));
                    //Dodajemy nazwe o indeksie 0 i od razu ja usuwamy
                    satNames.remove(0);
                    //Jezeli istnieja dodatkowe nazwy to mozemy je wszystkie dodac na raz korzystajac z metody addNames
                    if(satNames.size()>0){
                        sat.addNames(satNames);
                    }
                    
                    //Rozdzielamy String pozycji na char, ktory zwraca np. E , W , N , S
                    //W tym przypadku mozemy zauwazyc, ze nasz znak jest ZAWSZE na koncu.
                    char direction=position.charAt(position.length()-1);
                    // i na number, ktory jest floatem i zwraca dana wartosc pozycji
                    //W tym celu ze Stringa position usuwamy WSZYSTKO co nie jest liczba, lub kropka i ew. wszelkie spacje.
                    float number= Float.parseFloat(position.replaceAll("[^\\d.]", "").replaceAll(" ",""));
                    //teraz ustawiamy te pozycje w naszym satelicie
                    sat.setSatellitePosition(number);
                    sat.setSatellitePositionDirection(direction);
                    
                    //jezeli Satelita nalezy do clustera, to zrobmy to samo dla clustera:
                    if(!cluster.isEmpty()){
                        char direction2=cluster.charAt(cluster.length()-1);
                        float number2= Float.parseFloat(cluster.replaceAll("[^\\d.]", "").replaceAll(" ",""));
                        //wstawiamy dane do naszego satelity
                        sat.setOrbitalPosition(number2);
                        sat.setOrbitalPositionDirection(direction2);
                    }
                    
                    //Ustawiamy Band dla naszego satelity, ktory jest zwyklym Stringiem
                    sat.setBand(band);
                    
                    //Ustariamy date, w tym celu bedziemy parsowac naszego Stringa do daty, o formacie dd.MM.yyyy.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    try {
                        sat.setUpdated(sdf.parse(updated));
                    } catch (ParseException ex) {
                        System.out.println("blad");
                    }
                    
                    //Na koncu dodajemy naszego satelite do naszej grupy satelit.
                    sats.add(sat);
                }
            }catch(IndexOutOfBoundsException e){
                //Na wypadek gdyby satelita nie byla w formacie : Cluster Satellite Position incl. Band UpDated

            }
        }
    }
}
