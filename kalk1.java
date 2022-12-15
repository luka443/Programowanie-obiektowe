package k;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class kalk1 {
    static String ZnakKtoryTrzbaPolaczyc;
    static JTextField l;
    static JFrame jf;
    static JPanel p;
    static String rowna_sie = "=";
    static String znaki = "+-/*";
    static String cyfra = "0123456789";
    static String Clear = "C";
    static String cyfry_i_znaki[] = new String[]
            {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", "=", "C", "/"};
    public static String polacz_w_liczbe(List<String> lista){
        ZnakKtoryTrzbaPolaczyc = "";
        for (int j = 0; j<lista.size();j++){
            ZnakKtoryTrzbaPolaczyc += lista.get(j);}
        return ZnakKtoryTrzbaPolaczyc;
    }
    public static void createAndShowGUI() {
            l = new JTextField(22);
            jf = new JFrame("My First Calculator");
            l.setBackground(Color.WHITE);
            l.setEditable(false);
            l.setFont(new Font("Arial Black", Font.BOLD, 12));
            l.setHorizontalAlignment(SwingConstants.RIGHT);
            l.setText("0");
            p = new JPanel();
            p.add(l);
            jf.add(p);
            boolean[] CzyLiczba = {true};
            String[] numbefore = {new String("0")};
            String[] znakk = {new String()};
            List<String> num1 = new ArrayList<>();
            List<String> num2 = new ArrayList<>();
            Integer[] ar = {0, 0, 0};
            num1.add("0");
            num2.add("0");
            ActionListener myActionListener = e -> {
                String currentnum = e.getActionCommand();  
                try {
                    if (znaki.contains(numbefore[0]) && znaki.contains(currentnum)) {
                        znakk[0] = currentnum;
                    } else if (znaki.contains(numbefore[0]) && cyfra.contains(currentnum)) {
                        num2.add(currentnum);
                        String liczba2 = polacz_w_liczbe(num2);
                        ar[1] = Integer.valueOf(liczba2);
                        l.setText(String.valueOf(ar[1]));
                    } else if (znaki.contains(numbefore[0]) && rowna_sie.contains(currentnum)) {
                        ar[2] = ans1(znakk[0], ar[0], ar[0]);
                        ar[1] = ar[0];
                        ar[0] = ar[2];
                        l.setText(String.valueOf(ar[2]));
                    } else if (Clear.contains(currentnum)) {
                        currentnum = "0";
                        l.setText("0");
                        num1.clear();
                        num1.add("0");
                        ar[0] = 0;
                        num2.clear();
                        num2.add("0");
                        ar[1] = 0;
                        znakk[0] = "";
                        CzyLiczba[0] = true;
                        ar[2] = 0;
                    } else if (cyfra.contains(numbefore[0]) && znaki.contains(currentnum)) {
                        ar[0] = ans1(znakk[0], ar[0], ar[1]);
                        znakk[0] = currentnum;
                        ar[1] = 0;
                        ar[2] = ar[0];
                        l.setText(String.valueOf(ar[0]));
                        num1.clear();
                        num2.clear();
                        CzyLiczba[0] = false;
                    } else if (cyfra.contains(numbefore[0]) && cyfra.contains(currentnum)) {
                        if (CzyLiczba[0]) {
                            num1.add(currentnum);
                            String liczba = polacz_w_liczbe(num1);
                            ar[0] = Integer.valueOf(liczba);
                            l.setText(String.valueOf(ar[0]));
                        } else {
                            num2.add(currentnum);
                            String liczba2 = polacz_w_liczbe(num2);
                            ar[1] = Integer.valueOf(liczba2);
                            l.setText(String.valueOf(ar[1]));
                        }
                    } else if (cyfra.contains(numbefore[0]) && rowna_sie.contains(currentnum)) {
                        if (znaki.contains(znakk[0])) {
                            ar[2] = ans1(znakk[0], ar[0], ar[1]);
                        }
                        num2.clear();
                        l.setText(String.valueOf(ar[2]));
                    } else if (rowna_sie.contains(numbefore[0]) && znaki.contains(currentnum)) {
                        ar[0] = ar[2];
                        znakk[0] = currentnum;
                    } else if (rowna_sie.contains(numbefore[0]) && cyfra.contains(currentnum)) {
                        num1.clear();
                        num1.add(currentnum);
                        String liczba = polacz_w_liczbe(num1);
                        ar[0] = Integer.valueOf(liczba);
                        l.setText(liczba);
                        num2.clear();
                        ar[1] = 0;
                        num2.add("0");
                    } else if (rowna_sie.contains(numbefore[0]) && rowna_sie.contains(currentnum)) {
                        ar[2] = ans1(znakk[0], ar[2], ar[1]);
                        ar[0] = ar[2];
                        l.setText(String.valueOf(ar[2]));
                    }
                    numbefore[0] = currentnum;     
                }
                catch(ArithmeticException przezero) {
                	l.setText("!!! Dzielenie przez zero !!!");
                }
            };
            int a = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 5; j++) {
                    JButton lu = new JButton(cyfry_i_znaki[a]);
                    lu.setPreferredSize(new Dimension(60, 30));
                    lu.addActionListener(myActionListener);
                    p.add(lu);
                    a++;
                }
            }
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setSize(300, 220);
            jf.setResizable(false);
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { createAndShowGUI(); }
        });
    }
    public static int ans1(String o, int num1, int num2){
        switch (o) {
            case "+":  num1 += num2; break;
            case "-":  num1 -= num2; break;
            case "/":
                //try {
                    num1 /= num2;
                    break;
              //  }
                //catch(ArithmeticException przezero){
                 //   l.setText("!!! Dzielenie przez zero !!!");
              //  }
            case "*": num1 *= num2; break;
        }
        return num1;
    }
}