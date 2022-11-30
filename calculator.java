package lab1;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import java.awt.*;


public class calculator{
	static JTextField l;
	static JFrame f;		
	static String txt = "";
	static int num;
	public static void main(String args[]) {
		
		
		class MyActionListener implements java.awt.event.ActionListener {						
        	public void actionPerformed(ActionEvent e) {
        		
        		String wysw = e.getActionCommand();
        		
        		if(wysw == "C") {
        			l.setText("0");
        			txt="";
        		}
        		else if(wysw=="+") {
        			num =Integer.parseInt(txt);
        			txt ="";
        			System.out.println(num);
        			
        		}
        		else if(wysw =="=") {
        			int num2 = Integer.parseInt(txt) + num;
        			System.out.println(num2);
        			l.setText(Integer.toString(num2));
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
        bd = new JButton("/");
        bd.setPreferredSize(new Dimension(60, 30));
        bm = new JButton("*");
        bm.setPreferredSize(new Dimension(60, 30));
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