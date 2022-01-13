package Pirates;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
public class LeaderBoard  {

	private PriorityQueue <HeapEntry> Que = new PriorityQueue<HeapEntry>();
	File f = new File("High.txt");
	private Scanner file = new Scanner(f);
	private int size;
	private String leaderboard;
	
	public LeaderBoard () throws IOException
	{
		size = 0;
		while (file.hasNext())
		{				
			Que.add(new HeapEntry (file.next(), file.nextInt()));
			size++;
			}
		
	}
	
	public void add(String name, int score)
	{
		HeapEntry temp = new HeapEntry(name,score);
		
		if(size>=10)
			removeH();
		
		Que.add(temp);
		size++;
	}
	
	public String getLeader()
	{
		return leaderboard;
	}
	
	public void removeH ()
	{
		Que.remove();	
		size--;
	}
	public void print() throws IOException
	{
		String end =toString();
	
	   BufferedWriter writer = new BufferedWriter(new FileWriter("High.txt"));	
	    writer.write(end);
	     
	    writer.close();    
	}
	
	public String toString ()
	{
		String end ="";
		
		PriorityQueue <HeapEntry> temp =Que;
		String pretty ="HighScore: "+temp.peek().toString() +"\n" ;	
		for (int i =0; i<size; i++)
		{	end += temp.peek().toString() +"\n";
			pretty += i +" "+temp.peek().toString2() +"\n";
			temp.remove();
		}
		System.out.println(pretty);
		leaderboard = pretty;
		
		return end;
		
	}
	
	 public static void main(String[] args) throws IOException, InterruptedException
	 {
	  LeaderBoard p = new LeaderBoard();
	  p.add("Hannah", 2);
	  p.add("Alicia", 20);
	  p.add("Ally", 200);
	  p.add("Jake", 23);
	  p.add("Sully", 2);
	  p.add("Blake", 22);
	  p.add("Alice", 30);
	
	  p.print();
//	File f = new File("High2.txt"); 
//	FileWriter fw = new FileWriter(f);
//	PrintWriter pw = new PrintWriter(fw);
//	
//	pw.write("String");
//	pw.close();
	
	

	   
	}
	
	
}//class
