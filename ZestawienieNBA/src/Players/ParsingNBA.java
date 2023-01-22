package Players;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsingNBA {
    public static void main(String args[]) {


        ArrayList<Integer> pointss = new ArrayList<>();

        ArrayList<String> namess = new ArrayList<>();

        ArrayList<String> teamm= new ArrayList<>();

        ArrayList<Integer> assistss = new ArrayList<>();

        ArrayList<Integer> GamesPlayedd = new ArrayList<>();

        ArrayList<Float> MinutesPlayedd = new ArrayList<>();

        ArrayList<Integer> Threeptss = new ArrayList<>();

        ArrayList<String> PercThreePtss = new ArrayList<>();

        List<_NBA_> Plist= new ArrayList<_NBA_>();


        final String url = "https://basketball.realgm.com/nba/stats/2023/Totals/Qualified/points/All/desc/1/Regular_Season";


        try {
            Document doc = Jsoup.connect(url).get();
            Elements media = doc.select("table.tablesaw tbody tr");

            for (Element ele : media) {

                String names = ele.child(1).text();
                String namea = ele.child(1).text();
                String team = ele.child(2).text();String assists = ele.child(18).text();
                String gamesPlayed = ele.child(3).text();
                String minutes = ele.child(4).text();
                String points = ele.child(5).text();
                String threePointers = ele.child(9).text();
                String percentage3Pointers = ele.child(11).text();


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

            }

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
                    }

                    DefaultTableModel model = new DefaultTableModel(new String[]{ "Name", "Team", "Points", "Assists",
                             "3PT", "%3PT", "GamesPlayed", "MinutesPlayed" }, 0){
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

                        model.addRow(new Object[]{ namess.get(i), teamm.get(i), pointss.get(i),  assistss.get(i),
                                 Threeptss.get(i), PercThreePtss.get(i)+ "%", GamesPlayedd.get(i), MinutesPlayedd.get(i)});
                    }

                    JTable table = new JTable(model);
                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                    table.setRowSorter(sorter);


                    List<RowSorter.SortKey> sortKeys = new ArrayList<>(400);
                    sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
                    sorter.setSortKeys(sortKeys);

                    JFrame frame = new JFrame(" NBA ");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(new JScrollPane(table));
                    TableColumn column;
                    for (int i = 0; i < 7; i++) {
                        column = table.getColumnModel().getColumn(i);
                        column.setResizable(false);
                        if(i==0){
                            column.setPreferredWidth(120);
                            column.setResizable(false);

                        }
                        else {
                            column.setPreferredWidth(44);
                        }
                    }
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    frame.pack();
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
        }



}
