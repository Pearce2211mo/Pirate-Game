package Pirates;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Read extends JFrame {
 boolean done, correct;
 int thred;
 int current;
 final int SIZE = 5;
 Random ran = new Random();//
 Question [] Q = new Question [SIZE];
 
 JButton op0 = new JButton ();
 JButton op1 = new JButton ();
 JButton op2 = new JButton ();
 Listener ears = new Listener ();
 JFrame map = new JFrame ();
 JPanel opt = new JPanel ();
 
 String chest = "C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Chest0.png";
 
 public Read(int num)throws IOException
 {
  correct = false;
  done = false;
  thred = 1;
  
  map = new JFrame ("Question "+ num+ " of 5");
  
  int i = 0;
  File f1 = new File("Words.txt");
  File f2 = new File("Words2.txt");
  File f3 = new File("Words3.txt");
  Scanner file1 = new Scanner (new File ("Words.txt"));
  Scanner file2 = new Scanner (new File ("Words2.txt"));
  Scanner file3 = new Scanner (new File ("Words3.txt"));
  while (i< SIZE)
  { Q [i] = new Question (file1.next(),file2.next(),file3.next());
   i++;
  }
 
 }//read
 
 public void DisplayQ () throws IOException
 {
  
  JLabel Quest = new JLabel( "<html> <center>-    Choose the word that is <br/> spelled correctly-</center></html>");
  JLabel back = new JLabel (new ImageIcon(ImageIO.read
		    (new File (getChest()) )));
  
  int i = ran.nextInt(SIZE);
  
  Quest.setFont(new Font("Arial", Font.PLAIN, 20));
  
  JPanel Qs = new JPanel ();
  Qs.setOpaque(true);
  Qs.setLayout(new GridLayout(1,2));
  Qs.add(Quest);
  Qs.add(back);
  
  current = i;
  op0 = new JButton (Q[i].getCorrect());
  op1 = new JButton (Q[i].getIn1());
  op2 = new JButton (Q[i].getIn2());
  
  
  int x = ran.nextInt(4);
    
  opt.setLayout(new GridLayout(3,1));
  
  switch (x)
  {
   case 0:
    opt.add(op0);
    opt.add(op1);
    opt.add(op2);
   break;
   case 1:
    opt.add(op1);
    opt.add(op2);
    opt.add(op0);
   break;
   case 2:
    opt.add(op2);
    opt.add(op0);
    opt.add(op1);
   break;
   case 3:
    opt.add(op2);
    opt.add(op1);
    opt.add(op0);
   break;
  }//switch
  
  op0.addActionListener((ActionListener) ears);
  op1.addActionListener(ears);
  op2.addActionListener(ears);
  
  op0.setFont(new Font("Arial", Font.PLAIN, 40));
  op1.setFont(new Font("Arial", Font.PLAIN, 40));
  op2.setFont(new Font("Arial", Font.PLAIN, 40));
  
  op0.setBackground(Color.BLUE);
  op1.setBackground(Color.cyan);
  op2.setBackground(Color.decode("00500"));
  op0.setForeground(Color.WHITE);
  op2.setForeground(Color.WHITE);
  Qs.setBackground(Color.decode("#DCC6A5"));
  
  map.setLayout(new GridLayout(2,1));
  map.setLocationRelativeTo(null);
  map.setDefaultCloseOperation(map.EXIT_ON_CLOSE);
  map.setBounds(500, 50, 500, 600);
  map.setLocation(750,120);
  Qs.setOpaque(true);
  map.add(Qs);
  map.add(opt);
  map.setVisible(true);
 
 }//display
 public String getChest () throws IOException
 {
	if (thred == 1)
	chest = ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Chest0.png");
	else if (thred == 2)
	chest = ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Chest1.png");
	else if (thred == 3)
	chest = ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Chest2.png");
	else if (thred == 4)
	chest = ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Chest3.png");
	else if (thred == 5)
	chest = ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Chest4.png");
	return chest;
 }
 public void setThred(int i)
 { thred = i;	}
 public boolean isDone ()
 {
  return done;
 }
 
 public boolean isCorrect()
 {
  return correct;
 }
 
 private class Listener implements ActionListener 
 {
  @Override
  public void actionPerformed (ActionEvent e) 
  {
	  if  (e.getSource() == op0)
	   { correct = true;    
	    done = true;
	    map.dispose();
	   }
	   else if  (e.getSource() == op1)
	   { done = true;
	    map.dispose();
	   } 
	   else if  (e.getSource() == op2)
	   { done = true;
	    map.dispose();
   }
  }//ActPre
 }//Listener
 

 public static void main(String[] args) throws IOException, InterruptedException
 {
  Read r = new Read (1);
  r.DisplayQ();

   
}
}