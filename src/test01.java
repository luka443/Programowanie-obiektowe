import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.log4j.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class test01 {
    final public static String url = "https://basketball.realgm.com/nba/stats/2023/Averages/Qualified/points/All/desc/1/Regular_Season";
    protected static JComboBox<String> jlist;



    public static void main(String[] args) throws IOException {
        Logger log
                = Logger.getLogger(
                test01.class.getName());
        final Document doc = Jsoup.connect(url).get();
        log.info("Parsing the content ");
        Elements table = doc.select("table.tablesaw.compact");
        Elements rows = table.select("tr");
        String names[] = new String[rows.size()] ;
        for(int i = 1; i<rows.size(); i++){
            Element row = rows.get(i);
            Elements col = row.select("td.nowrap");
            names[i] = col.text();
        }

        JFrame jf = new JFrame("Nba stats");

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 560);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jlist = new JComboBox<String>(names);
        jlist.setBackground(Color.LIGHT_GRAY);
        jlist.setForeground(Color.BLUE);
        jlist.setFont(new Font("Verdana", Font.BOLD, 12));
        jf.add(jlist).setSize(100,100);

    }
}
