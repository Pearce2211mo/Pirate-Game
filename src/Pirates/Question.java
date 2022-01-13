package Pirates;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class Question {
 private String correct;
 private String inc1;
 private String inc2;
 
 Random ran = new Random();//
 
 
 public Question()
 {
  correct = null;
  inc1 =  null;
  inc2 = null;
  
 }
 
 public Question (String c, String I1, String I2)
 {
  correct = c;
  inc1 = I1;
  inc2 = I2;
  
 } 
 
 public String getCorrect ()
 {
  return correct;
 }
 public String getIn1 ()
 {
  return inc1;
 }
 public String getIn2 ()
 {
  return inc2;
 }
}

