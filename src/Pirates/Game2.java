package Pirates;
import java.io.IOException;
import java.util.Random;
public class Game2
{
  private Grid grid;
  private int dim = 10;
  private int userRow, userCol;
  private int msElapsed;
  private int score,wrong;
  private boolean end;
  Random ran = new Random();//
  
//T: Constructor
  public Game2(int i)
  {
	wrong = 0;
	Color backColor =  (new Color(227,208,117));
    grid = new Grid(dim, dim, backColor );
    userRow = dim/2;
    userCol = 0;
    msElapsed = 0;
    score = i;
    end = false;
    updateTitle();
    populate();
    grid.setImage(new Location(userRow, userCol), "Stop0.png");
  }
  
  
//T: Plays the game in a while loop   
  public void play() throws IOException
  {
    while (!isGameOver())
    {
      grid.pause(100);
      handleKeyPress();
     // if (msElapsed % 300 == 0)
    
    	updateTitle();
      msElapsed += 100;
    }
  }
  
//T: Move item up/down (38/40)
//   Also handles an up or down collision 
  public void handleKeyPress() throws IOException
  {
	  int move;
 grid.setImage(new Location(userRow, userCol), null);
 int key = grid.checkLastKeyPressed();
 
 if ((key == 38) && (userRow != 0))//up
 {
  grid.setImage(new Location (userRow, userCol), "Sand.png");
  move = handleCollision(new Location (userRow-1, userCol));
  userRow-= move;
 } 
 else if ((key == 40) &&(userRow!= dim-1))//down
 { grid.setImage(new Location (userRow, userCol), "Sand.png");
 move =handleCollision(new Location (userRow+1, userCol));
  userRow+= move;
 }
 else if ((key == 37) && (userCol != 0))//Left
 {grid.setImage(new Location(userRow, userCol), "1stLeft.gif");
  grid.pause(150); 
  grid.setImage(new Location (userRow, userCol), "Sand.png");
  move = handleCollision(new Location (userRow, userCol-1));
  userCol -=move;
  grid.setImage(new Location(userRow, userCol), "2ndLeft.gif");
  grid.pause(150); 
 } 
 else if ((key == 39) &&(userCol!= dim-1))
 {  grid.setImage(new Location(userRow, userCol), "1stRight.gif");
 	grid.pause(150);
	grid.setImage(new Location (userRow, userCol), "Sand.png");
	move =handleCollision(new Location (userRow, userCol+1));
  userCol+=move;
  grid.setImage(new Location(userRow, userCol), "2ndRight.gif");
  grid.pause(150);
  }
 else 
  grid.setImage(new Location(userRow, userCol), "Stop0.png");
 grid.setImage(new Location(userRow, userCol), "Stop0.png");
  }
//T: Randomly place items on the screen
  public void populate()
  {
	 
  for (int i = ran.nextInt(3); i>0; i--) 
   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "Xx.png");
  for (int i = ran.nextInt(4)+8; i>0; i--) 
	   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "grass.png");
  for (int i = ran.nextInt(4)+10; i>0; i--) 
	   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "rocks.png");
  for (int i = ran.nextInt(4)+1; i>0; i--) 
	   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "grass3.png");
  for (int i = ran.nextInt(4); i>0; i--) 
	   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "puddle.png");
  
  if (ran.nextInt(100)<5)
   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "Xx.png");
  if (ran.nextInt(100)<30)
	   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "crab.png");
  if (ran.nextInt(100)<10)
	   grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "shell.png");
  
  grid.setImage(new Location (ran.nextInt(dim), ran.nextInt(dim)), "Xx.png");
  grid.setImage(new Location (0, 0), "Monkey.png");
  
  }
  
  
//T: Controls what happens when the User hits an item
  public int handleCollision(Location loc) throws IOException
  {
   String im = grid.getImage(loc);
   if (im == "Xx.png")
   {   
    grid.setImage(new Location(userRow, userCol), "Stop0.png");
    grid.setImage(loc, "Chest.png");
    
   for (int i =1; i<6; i++) 
   { 
		Read r = new Read(i);
		r.setThred(i-wrong);
	    r.DisplayQ();
	   while (!r.isDone())
	    grid.pause(100);
	    
	    if (r.isCorrect()==true)
	    score++; 
	    else
	    {wrong ++;
	     score--;
	    } 
   }//for
   
   if (wrong ==0)
	   score +=5;
   grid.exit();
   end = true;
   }//if
   else if (im == "Sand.png")
	   return 1;
   else if (im == "crab.png")
   {   score+=2;
	   return 1;	}
   else if (im == "shell.png")
   {   score+=5;
	   return 1;	}
   else if (im != null)
	   return 0;
   
   return 1;
	
   
  }
  
//T: Returns score  
  public int getScore()
  {
    return score;
  }
  
//T: changes header on applet screen 
  public void updateTitle()
  {
    grid.setTitle("Game:  " + getScore());
  }
//T: How do you know when you win?  
  public boolean isGameOver()
  {
   return end;
  }
  
//T: Clears screen 
  public void clear ()
  {
 for (int r = 0; r<dim; r++)
 for (int c = 0; c<dim; c++)
  grid.setImage(new Location(r,c), null); 
  }
//Test Game 
}//Class
