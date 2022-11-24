package lab1;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import java.awt.*;

public class calculator{
	public static void main(String args[]) {
    
		JTextField l;
		JFrame f;
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
        	b[i].setPreferredSize(new Dimension(60, 30));       	
        	p.add(b[i]);
        }                                   
        beq1 = new JButton("=");
        beq1.setPreferredSize(new Dimension(60, 30));
        ra = new JButton("+");  
        ra.setPreferredSize(new Dimension(60, 30));
        bs = new JButton("-");
        bs.setPreferredSize(new Dimension(60, 30));
        bd = new JButton("/");
        bd.setPreferredSize(new Dimension(60, 30));
        bm = new JButton("*");
        bm.setPreferredSize(new Dimension(60, 30));
        beq = new JButton("C");                 
        beq.setPreferredSize(new Dimension(60, 30));
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