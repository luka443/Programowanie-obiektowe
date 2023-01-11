import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class test01 {
    private final static String url = "https://www.satbeams.com/satellites";

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements media = doc.select("table[cellpadding=0][id=sat_grid]");
        Elements tmp = media.first().select("TR");
        String[ ][ ] arr = new String[tmp.size()][10];
        for (Element el: tmp) {

            if(!el.children().text().contains("deorbited") && !el.children().text().contains("retired")
                    && !el.children().text().contains("failed")) {

                arr =el.children().text();
            }

        }

            //tmp=el.select("a"
        //for (int i=0; i<tmp.size(); i++) {
            //System.out.println(tmp.get(i).text());
       // }

    }
}
