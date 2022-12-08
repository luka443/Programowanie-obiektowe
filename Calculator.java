package k;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
public class Calculator{
	static JTextField l;
	static JFrame f;		
	static String txt = "";
	static int num;	
	static boolean mnozenie = false;
	static boolean dzielenie = false;
	static boolean odejmowanie = false;
	static boolean dodawanie = false;
	static int num2 = 0;
	public static void main(String args[]) {
		ArrayList<Integer> arr = new ArrayList<>();
																			
		class MyActionListener implements java.awt.event.ActionListener {			
        	public void actionPerformed(ActionEvent e) {         		
        		String wysw = e.getActionCommand();
        		
        		switch(wysw){
        		case "C":
        			l.setText("0");
        			txt="";
        			arr.clear();
        			num2 = 0;
        			break;       		
        		case "+":                			
        			dodawanie = true;
        			txt ="";  
        			break;
        		case "-":
        			num = Integer.parseInt(l.getText());
        			odejmowanie =true;
        			txt ="";
        			break;
        		case "/":        			
        			dzielenie =true;
        			txt ="";
        			break;
        		case "*":        				        			
        			mnozenie = true;
        			txt= "";
        			break;
        		case "=":        			        			        			            			
        			if(mnozenie == true) {    
        				num2 =arr.get(0);
        				for( int t =1;t<arr.size();t++) {
        					num2 *= arr.get(t);        					
        				}        				        			
        				l.setText(Integer.toString(num2));
        				arr.clear();
        				arr.add(num2);
        				num2 = 0;
        				mnozenie =false;
        			}
        			else if(odejmowanie == true) {
        				num2 = arr.get(0);
        				for( int t =1;t<arr.size();t++) {
        					num2 -= arr.get(t);        					
        				}        				        			
        				l.setText(Integer.toString(num2));
        				arr.clear();
        				arr.add(num2);
        				num2 = 0;
        				odejmowanie =false;
        			}
        			else if(dzielenie == true) {
        				try {
        					num2 =arr.get(0);
            				for( int t =1;t<arr.size();t++) {
            					num2 /= arr.get(t);        					
            				}        				        			
            				l.setText(Integer.toString(num2));
            				arr.clear();
            				arr.add(num2);
            				num2 = 0;
            				dzielenie =false;
        				}
        				catch (ArithmeticException eee) {
        			         l.setText("Error, Division by zero!");
        			         dzielenie=false;
        			      }
        			}
        			else if(dodawanie == true){       				
        				for( int t =0;t<arr.size();t++) {
        					num2 += arr.get(t);   
        					System.out.println(arr);
        				}        				        			
        				l.setText(Integer.toString(num2));
        				arr.clear();
        				arr.add(num2);
        				num2 = 0;
    					dodawanie = false;    					
        			}
        			else {
        				l.setText(l.getText());
        			}   
        			txt = "";
        			break;
        			
        		default:
        			txt +=wysw;        			
        			l.setText(txt); 
        			System.out.println(txt);
        			arr.add(Integer.parseInt(txt));
        			if(txt.length()>1) {
        				arr.remove(arr.size() - 2);
        			}
        			
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
        JButton ra, bs, bd, bm, beq, beq1;
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