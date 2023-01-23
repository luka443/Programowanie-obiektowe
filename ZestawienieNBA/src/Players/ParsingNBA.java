package Players;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.TableView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParsingNBA {
    public static void main(String args[]) throws IOException {


        ArrayList<Integer> pointss = new ArrayList<>();

        ArrayList<String> namess = new ArrayList<>();

        ArrayList<Integer> reboundss = new ArrayList<>();

        ArrayList<String> teamm= new ArrayList<>();

        ArrayList<Integer> assistss = new ArrayList<>();

        ArrayList<Integer> GamesPlayedd = new ArrayList<>();

        ArrayList<Float> MinutesPlayedd = new ArrayList<>();

        ArrayList<Integer> Threeptss = new ArrayList<>();

        ArrayList<String> PercThreePtss = new ArrayList<>();

        List<_NBA_> Plist= new ArrayList<_NBA_>();

        //BasicConfigurator.configure();
        Logger logger = LogManager.getLogger(ParsingNBA.class);

        final String url = "https://basketball.realgm.com/nba/stats/2023/Totals/Qualified/points/All/desc/1/Regular_Season";

        Properties Props = new Properties();

        try {
            Document doc = Jsoup.connect(url).get();

            logger.info("" +
                    "Trying connect to: " + url + "... ... ...");
            Elements media = doc.select("table.tablesaw tbody tr");
            logger.info("Parsing the content ...");

            for (Element ele : media) {

                String names = ele.child(1).text();
                String namea = ele.child(1).text();
                String team = ele.child(2).text();String assists = ele.child(18).text();
                String gamesPlayed = ele.child(3).text();
                String minutes = ele.child(4).text();
                String points = ele.child(5).text();
                String threePointers = ele.child(9).text();
                String percentage3Pointers = ele.child(11).text();
                String rebounds = ele.child(17).text();


                if(points.contains(",")) {
                    points = points.replace(",","");
                }

                if(minutes.contains(",")) {
                    minutes = minutes.replace(",","");
                }
                char[] c = percentage3Pointers.toCharArray();

                char temp = c[0];
                c[0] = c[1];
                c[1] = temp;
                String newPoints = new String(c);

                char[] b = newPoints.toCharArray();

                char temp1 = b[1];
                b[1] = b[2];
                b[2] = temp1;
                String newPoints1 = new String(b);

                _NBA_ obj = new _NBA_(names);
                Plist.add(obj);
                obj.setName(namea);
                obj.setTeam(assists);
                obj.setRebounds(Integer.parseInt(rebounds));
                obj.setTeam(team);
                obj.setAssists(Integer.parseInt(assists));
                obj.setGamesPlayed(Integer.parseInt(gamesPlayed));
                obj.setMinutesPlayed(Float.parseFloat(minutes));
                obj.setPoints(Integer.parseInt(points));
                obj.setThreePointers(Integer.parseInt(threePointers));
                obj.setPercentageOf3P(newPoints1);


                pointss.add(obj.getPoints());
                assistss.add(obj.getAssists());
                namess.add(obj.getName());
                Threeptss.add(obj.getThreePointers());
                PercThreePtss.add(obj.getPercentageOf3P());
                teamm.add(obj.getTeam());
                GamesPlayedd.add(obj.getGamesPlayed());
                MinutesPlayedd.add(obj.getMinutesPlayed());
                reboundss.add(obj.getRebounds());

            }

            }
        catch (MalformedURLException e) {
            logger.error("[RUN] Problem with URL: " + url);

        }
        catch (UnknownHostException e) {
            logger.error("Cannot connect to: " + url + " ("+e.getLocalizedMessage()+")",e);
        }

        catch (SocketTimeoutException e) {
            logger.error("Timeout Problem with URL: " + url + "\n\t - "+e.getLocalizedMessage()+", current timeout: ");

        }

        catch (HttpStatusException e) {
            logger.error("Problem with connection to: " + url + " ("+e.getLocalizedMessage()+")",e);
        }

        catch (NoRouteToHostException e) {
            logger.error("Problem with connection to: " + url + " ("+e.getLocalizedMessage()+")",e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                        logger.error("wystąpił bład");
                    }

                    DefaultTableModel model = new DefaultTableModel(new String[]{ "Name", "Team", "Points", "Assists", "Rebounds",
                             "3PT", "%3PT", "Games", "MinutesPlayed" }, 0){
                        @Override
                        public Class<?> getColumnClass(int column)
                        {
                            Class<?> returnValue;
                            if ((column >= 2) && (column < getColumnCount()))
                            {
                                returnValue = getValueAt(0, column).getClass();
                            }
                            else
                            {
                                returnValue = Object.class;
                            }

                            return returnValue;
                        };
                    };

                    int align = DefaultTableCellRenderer.RIGHT;

                    for(int i= 0; i<pointss.size()-1;i++) {

                        model.addRow(new Object[]{ namess.get(i), teamm.get(i), pointss.get(i),  assistss.get(i), reboundss.get(i),
                                 Threeptss.get(i), PercThreePtss.get(i)+ "%", GamesPlayedd.get(i), MinutesPlayedd.get(i)});

                    }

                    JTable table = new JTable(model);
                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                    table.setRowSorter(sorter);
                    table.setRowHeight(30);


                    table.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            int row = table.rowAtPoint(evt.getPoint());
                            int col = table.columnAtPoint(evt.getPoint());

                            if (row >= 0 && col >= 0) {
                                logger.info("Pressed row:"+row +" and column:" +col );
                            }
                        }
                    });
                    DefaultTableCellRenderer intRenderer = (DefaultTableCellRenderer)
                            table.getDefaultRenderer(Integer.class);
                    intRenderer.setHorizontalAlignment(SwingConstants.LEFT);
                    DefaultTableCellRenderer floatRenderer = (DefaultTableCellRenderer)
                            table.getDefaultRenderer(Float.class);
                    floatRenderer.setHorizontalAlignment(SwingConstants.LEFT);

                    DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)
                            table.getDefaultRenderer(String.class);
                    stringRenderer.setHorizontalAlignment(SwingConstants.LEFT);

                    List<RowSorter.SortKey> sortKeys = new ArrayList<>(400);
                    logger.trace(sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING)));
                    sorter.setSortKeys(sortKeys);
                    for(int t=0;t<10;t++) {
                        Props.put(String.valueOf(t), String.valueOf(table.getValueAt(t, 0)));
                    }
                    try {
                        Props.store(new FileOutputStream("Pprop.properties"),null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    JFrame frame = new JFrame(" NBA ");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    frame.add(new JScrollPane(table));
                    TableColumn column;
                    TableView.TableRow row;
                    for (int i = 0; i < 9; i++) {

                        column = table.getColumnModel().getColumn(i);
                        column.setResizable(false);
                        if(i==0){
                            table.setFont(new Font("Arial", Font.BOLD, 14));
                            column.setPreferredWidth(200);
                            column.setResizable(false);

                        }
                        else if(i ==8){
                            column.setResizable(false);
                            column.setPreferredWidth(120);
                        }
                        else if(i ==4){
                            column.setResizable(false);
                            column.setPreferredWidth(65);
                        }
                        else if(i ==7){
                            column.setResizable(false);
                            column.setPreferredWidth(55);
                        }
                        else {
                            column.setPreferredWidth(54);
                        }
                    }
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    frame.pack();
                    frame.setSize(700,500);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
        }


}
