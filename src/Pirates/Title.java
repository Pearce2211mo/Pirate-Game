package Pirates;
import javax.swing.*;
import javax.imageio.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Title extends JFrame {
 
 JFrame frame;
 JLabel back;
 JButton op1, op2, op3,op4;
 Listener ears = new Listener ();
 int thred = 0;
 boolean done = false;
 
 
 public Title () throws IOException{
  frame = new JFrame ("Pirates' Cove");
  JLabel back = new JLabel (new ImageIcon(ImageIO.read
    (new File ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Ocean.jpg"))));
  
  op1 = new JButton ("Play");
  op2 = new JButton ("Hint");
   
  op2.addActionListener((ActionListener) ears);
  op1.addActionListener(ears);
  
  
  back.setLayout(new FlowLayout());
  back.add(op1);
  back.add(op2);
 
  frame.setContentPane(back);
    
  frame.setResizable(false);
  frame.setLocation(450,150);
  frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  frame.pack();
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
 }
 public JFrame hint() throws IOException{
  frame = new JFrame ("Instruction");
  JLabel back = new JLabel (new ImageIcon(ImageIO.read
    (new File ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Key.png"))));
  
  op1 = new JButton ("Play");
  op3 = new JButton ("Back");
    
  op3.addActionListener((ActionListener) ears);
  op1.addActionListener(ears);
  
  back.setLayout(new FlowLayout());
  back.add(op1);
  back.add(op3);
  
  frame.setContentPane(back);
  
  frame.setResizable(false);
  frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  frame.pack();
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  return frame;
 }
 public String win(int score) throws IOException
 {
	  
  frame = new JFrame ("Win");
  JLabel back = new JLabel (new ImageIcon(ImageIO.read
    (new File ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Win.png"))));
  
  op1 = new JButton ("Play again");
  op3 = new JButton ("Main Menu");
  op4 = new JButton ("High Scores");
    
  op3.addActionListener((ActionListener) ears);
  op1.addActionListener(ears);
  op4.addActionListener(ears);
  
  back.setLayout(new FlowLayout());
  back.add(op1);
  back.add(op3);
  back.add(op4);
  
  frame.setContentPane(back);
  
  frame.setResizable(false);
  frame.setLocation(450,150);
  frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  frame.pack();
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  String s = JOptionPane.showInputDialog(null, "Your Name:", "Winner with a HighScore of "+ score,JOptionPane.INFORMATION_MESSAGE);
  LeaderBoard LB = new LeaderBoard();
	 LB.add(s, score);
	 LB.print();
  
  return LB.getLeader();
 }
 public JFrame lose() throws IOException{
  frame = new JFrame ("Win");
  JLabel back = new JLabel (new ImageIcon(ImageIO.read
    (new File ("C:\\Users\\Frog\\Desktop\\Games\\Pirates\\src\\Pictures\\Lose.jpg"))));
  
  op1 = new JButton ("Try again");
  op3 = new JButton ("Main Menu");
    
  op3.addActionListener((ActionListener) ears);
  op1.addActionListener(ears);
  
  back.setLayout(new FlowLayout());
  back.add(op1);
  back.add(op3);
  
  frame.setContentPane(back);
  
  frame.setResizable(false);
  frame.setLocation(450,150);
  frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  frame.pack();
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  return frame;
 }
 public JFrame HighScore(String scores) throws InterruptedException, IOException
 {
	 JFrame box = new JFrame ("High Scores: ");
	 JTextArea text = new JTextArea();
	 text.setBackground(Color.BLACK);
	 text.setForeground(Color.WHITE);
	 text.setFont(new Font("Consolas", Font.PLAIN, 40));
	 text.setEditable(false);
	 
	 text.setText(scores);
	 
	 box.add(text);
	 box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 box.setSize(new Dimension (500,650));
	 box.setLocation(500, 150);
	 box.setBackground(Color.BLUE);
	 box.setVisible(true);
	 
	 Thread.sleep(3000);
	 box.dispose();
	 
	 
	 return box;
 }
 
 public void setDone (boolean b)
 {
  done = b;
 }
 public void setThred (int x)
 {
  thred = x;
 }
 public int playGame () throws IOException
 {
  Game1 game = new Game1 ();
   game.play();   
   game.clear();
   return game.getScore();
 }
 
 
 private class Listener implements ActionListener
 {
  @Override
  public void actionPerformed (ActionEvent e)
  {
   if  (e.getSource() == op1)//plays game
   { 
    frame.dispose();
    thred = 1;
   }
    
   else if  (e.getSource() == op2)//option menu
   { 
    frame.dispose();
    thred = 2;
   }
   else if  (e.getSource() == op3)//Main screen
   { 
    frame.dispose();
    thred = 3;
   }
   else if  (e.getSource() == op4)//HighScores
   {
	 thred = 6;
	   
   }
  }
 }
 
 public static void main (String [] args ) throws IOException, InterruptedException {
  
  Title t = new Title();
  int score = 0;
  String Scores = "";
  
  while (!t.done)
  {
   switch ( t.thred)
   {
    case 0:    //Sleeps/pause game
     Thread.sleep(200);
    break;
    case 1:    //Plays Game
     score = t.playGame();
     if (score<15)
      t.thred = 5;
     else
      t.thred = 4;        
    break;
    case 2:     //Instructions
     t.setThred(0);
     t.hint();
    break;
    case 3:     //Home Screen
     t= new Title();
    break;
    case 4:     //Winning screen
     t.setThred(0);
     Scores = t.win(score);
    break;
    case 5:
     t.setThred(0);
     t.lose();
    break;
    case 6:
    	t.setThred(0);
    	t.HighScore(Scores);
    break;
    
   }//switch
   
  }//while
 }
}
