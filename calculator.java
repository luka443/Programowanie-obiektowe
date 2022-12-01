package lab3;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import java.awt.*;
public class calculator{
	static JTextField l;
	static JFrame f;		
	static String txt = "";
	static int num;	
	static boolean mnozenie = false;
	static boolean dzielenie = false;
	static boolean odejmowanie = false;
	public static void main(String args[]) {
																			
		class MyActionListener implements java.awt.event.ActionListener {			
        	public void actionPerformed(ActionEvent e) {         		
        		String wysw = e.getActionCommand();
        		
        		if(wysw == "C") {
        			l.setText("0");
        			txt="0";
        		}
        		else if(wysw=="+") {     
        			num =Integer.parseInt(l.getText());	        			
        			txt ="";        			       			
        		}
        		else if(wysw =="-") {
        			num = Integer.parseInt(l.getText());
        			odejmowanie =true;
        			txt ="";
        		}
        		else if(wysw =="/") {
        			num = Integer.parseInt(l.getText());
        			dzielenie =true;
        			txt ="";
        		}
        		else if(wysw =="-") {
        			num = Integer.parseInt(l.getText());
        			odejmowanie = true;
        			txt ="";
        		}
        		else if(wysw == "*"){
        			num =Integer.parseInt(l.getText());	
        			System.out.println(l.getText());
        			mnozenie = true;
        			txt= "";
        		}
        		else if(wysw =="=") {
        			int num2 = Integer.parseInt(txt) + num;
        			int num1 =  num - Integer.parseInt(txt);
        			int num3 = Integer.parseInt(l.getText()) * num;    
        			
        			if(mnozenie == true) {
        				l.setText(Integer.toString(num3));
        				mnozenie =false;
        			}
        			else if(odejmowanie == true) {
        				l.setText(Integer.toString(num1));
        				odejmowanie =false;
        			}
        			else if(dzielenie == true) {
        				int num4 = Integer.parseInt(txt) / num;
        				l.setText(Integer.toString(num4));
        				dzielenie =false;
        			}
        			else {
        				l.setText(Integer.toString(num2));
        			}
        			txt ="";
        		}
        		else {
        			txt +=wysw;
        			l.setText(txt); 
        		}
        	}
        } 			
        f = new JFrame("My First Calculator");
                    
        l = new JTextField(22);
        l.setBackground(Color.WHITE);
        l.setEditable(false);       
        l.setFont(new Font("Arial Black", Font.BOLD,12));
        l.setHorizontalAlignment(SwingConstants.RIGHT);
        l.setText("0");               
        JButton ra, bs, bd, bm, be, beq, beq1;
        JButton b[] = new JButton[10];
        JPanel p = new JPanel();
        
        p.add(l);
        for(int i = 0;i<10;i++) {
        	b[i]= new JButton(""+i);
        	b[i].addActionListener(new MyActionListener());
        	b[i].setPreferredSize(new Dimension(60, 30));       	
        	p.add(b[i]);
        }                                   
        beq1 = new JButton("=");
        beq1.setPreferredSize(new Dimension(60, 30));
        beq1.addActionListener(new MyActionListener());
        ra = new JButton("+");  
        ra.setPreferredSize(new Dimension(60, 30));
        ra.addActionListener(new MyActionListener());
        bs = new JButton("-");
        bs.setPreferredSize(new Dimension(60, 30));
        bs.addActionListener(new MyActionListener());
        bd = new JButton("/");
        bd.setPreferredSize(new Dimension(60, 30));
        bd.addActionListener(new MyActionListener());
        bm = new JButton("*");
        bm.setPreferredSize(new Dimension(60, 30));
        bm.addActionListener(new MyActionListener());
        beq = new JButton("C");                 
        beq.setPreferredSize(new Dimension(60, 30));
        beq.addActionListener(new MyActionListener());
        p.add(ra);       
        p.add(bs);     
        p.add(bm);      
        p.add(bd);             
        p.add(beq);
        p.add(beq1);
         
        p.setBackground(Color.LIGHT_GRAY);      
        f.add(p);
 
        f.setSize(300, 220);                              
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.show();
    }        
}