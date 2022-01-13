package Pirates;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
//import java.awt.Color;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Game1
{
  private Grid grid;
  private int dim = 15;
  private int userRow;
  private int msElapsed;
  private int score;
  Random ran = new Random();//
 
  
//T: Constructor
  public Game1()
  {
 Color backColor =  (new Color(0,200,250));
    grid = new Grid(dim, dim, backColor );
    userRow = dim/2;
    msElapsed = 0;
    score = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), "user.gif");
  }
  
  
//T: Plays the game in a while loop   
  public void play() throws IOException
  {
    while (!isGameOver())
    {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0)
      {
     handleCollision(new Location (userRow, 1));
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
    
  }
  
//T: Move item up/down (38/40)
//   Also handles an up or down collision 
  public void handleKeyPress() throws IOException
  {
 grid.setImage(new Location(userRow, 0), null);
 int key = grid.checkLastKeyPressed();
 
 if ((key == 38) && (userRow != 0))
 { handleCollision(new Location (userRow-1, 0));
  userRow --;
 } 
 else if ((key == 40) &&(userRow!= dim-1))
 { handleCollision(new Location (userRow+1, 0));
  userRow++;
 }
 else if (key == 32)
 { 
  grid.setImage(new Location (userRow, 1),"cannon.gif");
  
 }
 grid.setImage(new Location(userRow, 0), "user.gif");
  }
//T: Randomly place items on the right side of the screen
  public void populateRightEdge()
  {
  if (ran.nextInt(100)<5)
   grid.setImage(new Location (ran.nextInt(dim), dim-1), "get.gif");
  for (int i = ran.nextInt(3); i>0; i--) 
    if (ran.nextInt(100)<50)
   grid.setImage(new Location (ran.nextInt(dim), dim-1), "avoid.gif");
  }
//T: Moves all Avoid/Get items to the left one space
  public void scrollLeft()
  {
 String im; 
 for (int r = 0; r<dim; r++)
 {   
  moveLeft (r, 0);
 }
  }//Scroll Left
//T: Moves a given Row over one
  public void moveLeft (int row, int col)
  {
   String im; 
   if (col == dim-1)
    grid.setImage(new Location (row, dim-1), null);
   else if ((grid.getImage(new Location(row,col))=="cannon.gif") )
   { handleCann(row,col);
  moveLeft(row, col +1);
   }
   else
   { 
  im = grid.getImage(new Location (row,col+1));     
    grid.setImage(new Location(row,col), im);
    grid.setImage(new Location(userRow,0), "user.gif");
    moveLeft(row, col+1);
   }
     
  }//Move Left
   
//T: Controls what happens when the Cannon ball hits an item
  public void handleCann(int row, int col)
  { 
 String im = grid.getImage(new Location (row,col+1));
 if (im == "avoid.gif")
 {  grid.setImage(new Location (row,col), "destroy.gif");
    grid.setImage(new Location (row,col+1), null);
    score++;
 }
 else if (im == "get.gif")
 {  grid.setImage(new Location (row,col), "get.gif");
    grid.setImage(new Location (row,col+1), null);
 }
  else if ( (im == null) )
  {  
   grid.setImage(new Location (row,col+1), "cannon.gif");
   grid.pause(50);
   grid.setImage(new Location (row,col), null);
  }   
  else 
     grid.setImage(new Location (row,col), null);
  }//Handles Cannon
  
//T: Controls what happens when the User hits an item
  public void handleCollision(Location loc) throws IOException
  {
   String im = grid.getImage(loc);
   if ((im == "avoid.gif")|| (im == "destroy.gif"))
   { score--;
   }
   else if (im == "get.gif")
   { Game2 game2 = new Game2(getScore());
    game2.play();
    game2.clear();
    score=game2.getScore();
   } 
  }
 
//T: Returns score  
  public int getScore()
  {
    return score;
  }
//T: set score
  public void setScore(int s)
  {
   score = s;
  }
  
//T: changes header on applet screen 
  public void updateTitle()
  {
    grid.setTitle("Score:  " + getScore()+" ||| Time:  " +((50000-msElapsed)/100));
  }
//T: How do you know when you win?  
  public boolean isGameOver()
  {
    return msElapsed>50000 || getScore()<-5;
  }
  
//T: Clears screen 
  public void clear ()
  {
 for (int r = 0; r<dim; r++)
 for (int c = 0; c<dim; c++)
  grid.setImage(new Location(r,c), null); 
 grid.exit();
  }
  
}//Class

