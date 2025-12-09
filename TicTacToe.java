import java.util.*;
public class TicTacToe
{
  static int c=0;  //counter for cells filled
  static char done[]=new char[9];
  public static void main(String args[])
  {
      Scanner sc=new Scanner(System.in);
      System.out.println("LET'S PLAY TIC-TAC-TOE! YOU ARE 'X'");
      System.out.println("HERE IS HOW THE PLACEMENT WORKS:");
      System.out.println("1|2|3");
      System.out.println("-+-+-");
      System.out.println("4|5|6");
      System.out.println("-+-+-");
      System.out.println("7|8|9");
      System.out.println();
      char GameBoard[][]={{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '}};
      System.out.println("Here is a clean slate!");
      printBoard(GameBoard);
      boolean game=false; //holds the current status of game(ended or not)
      //use of all functions to handle to game-main working
       while(!game) //while game has not ended
       {
        System.out.println("ENTER YOUR PLACEMENT (1-9)");
        int userpos=sc.nextInt(); // user position
        placePiece(GameBoard,userpos,"user"); //places piece for user
        printBoard(GameBoard);
        if(gameOver(GameBoard)) //checks if game is over
         break;
        else //if not then cpu places piece
        { 
         System.out.println("MY TURN!");
         int cpupos=generate();
         placePiece(GameBoard,cpupos,"cpu");
         printBoard(GameBoard);
         game=gameOver(GameBoard);
        }
       }
       if(winner(GameBoard,"user")) //checking winner and printing msg
       {
        System.out.println("YOU WIN!");  
       }
       else if(winner(GameBoard,"cpu"))
       {
        System.out.println("CPU WINS! BETTER LUCK NEXT TIME");  
       }
       else
       {
        System.out.println("IT WAS A TIE! UNTIL NEXT TIME.");
       }
  }
  //prints the board at the instant
  public static void printBoard(char GameBoard[][])
  {
       for(int i=0;i<5;i++)
       {
          for(int j=0;j<5;j++)
          {
            System.out.print(GameBoard[i][j]);
          }
          System.out.println();   
       }
      System.out.println();
  }
  //to generate cpu's position
  public static int generate() 
  {
      Random rnd = new Random(); //random selection
      int cpupos=(rnd.nextInt(9)+1);
      while(done[cpupos-1]=='X' || done[cpupos-1]=='O')//to check if the generated position is already filled
      {
        cpupos=(rnd.nextInt(9)+1);//regenerating if filled
      }
      return cpupos;
  }
  //placing piece,also prints msg for trying to overwrite cell
  public static void placePiece(char GameBoard[][],int pos, String player) 
  {
    char symbol=' ';
    if(player.equals("user"))
    {
     symbol='X';   
    }
    else
    {
     symbol='O';
    }
    
    switch(pos) //brute forcing the placement of pieces
    {
        case 1: if(GameBoard[0][0]==' ')
                {
                GameBoard[0][0]=symbol;
                done[0]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 2: if(GameBoard[0][2]==' ')
                {
                GameBoard[0][2]=symbol;
                done[1]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 3: if(GameBoard[0][4]==' ')
                {
                GameBoard[0][4]=symbol;
                done[2]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 4: if(GameBoard[2][0]==' ')
                {
                GameBoard[2][0]=symbol;
                done[3]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 5: if(GameBoard[2][2]==' ')
                {
                GameBoard[2][2]=symbol;
                done[4]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 6: if(GameBoard[2][4]==' ')
                {
                GameBoard[2][4]=symbol;
                done[5]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 7: if(GameBoard[4][0]==' ')
                {
                GameBoard[4][0]=symbol;
                done[6]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 8: if(GameBoard[4][2]==' ')
                {
                GameBoard[4][2]=symbol;
                done[7]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
        case 9: if(GameBoard[4][4]==' ')
                {
                GameBoard[4][4]=symbol;
                done[8]=symbol;
                c++;
                }
                else
                System.out.println("CELL IS FULL,YOU LOSE THIS TURN");
                break;
    }
  }
  public static boolean gameOver(char GameBoard[][])  //checks if game is over
  {
      boolean flag=false;
      if(c==9) //c=no of cells filled
      {
        flag=true;  //game ended bcoz all cells filled
      }
      else if(winner(GameBoard,"user")) //checks winner if not all cells filled
      {
        flag=true;
      }
      else if(winner(GameBoard,"cpu"))
      {
        flag=true;
      }
      return flag; //for appropriate msg for winner
  }
  public static boolean winner(char GameBoard[][],String player) //checks winner
  {
      boolean flag=false;
      char symbol=' ';
     if(player.equals("user"))
    {
     symbol='X';   //assigning symbol to players
    }
    else
    {
     symbol='O';
    }
    //all conditions of winning:
     if(GameBoard[0][0]==symbol && GameBoard[0][2]==symbol && GameBoard[0][4]==symbol)
     {
      flag=true;   
     }
     else if(GameBoard[0][0]==symbol && GameBoard[2][0]==symbol && GameBoard[4][0]==symbol)
     {
      flag=true;  
     }
     else if(GameBoard[0][0]==symbol && GameBoard[2][2]==symbol && GameBoard[4][4]==symbol)
     {
      flag=true;  
     }
     else if(GameBoard[2][0]==symbol && GameBoard[2][2]==symbol && GameBoard[2][4]==symbol)
     {
      flag=true;  
     }
     else if(GameBoard[4][0]==symbol && GameBoard[4][2]==symbol && GameBoard[4][4]==symbol)
     {
      flag=true;  
     }
     else if(GameBoard[0][2]==symbol && GameBoard[2][2]==symbol && GameBoard[4][2]==symbol)
     {
      flag=true;  
     }
     else if(GameBoard[0][4]==symbol && GameBoard[2][4]==symbol && GameBoard[4][4]==symbol)
     {
      flag=true;  
     }
     else if(GameBoard[0][4]==symbol && GameBoard[2][2]==symbol && GameBoard[4][0]==symbol)
     {
      flag=true;  
     }
    return flag;
  }
}
