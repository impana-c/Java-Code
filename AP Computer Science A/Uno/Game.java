import java.util.ArrayList; 
import java.util.Scanner; 

public class Game{

  private int turn;
  private Card current;
  private Deck myDeck;
  private boolean done = false;
  private Player winner;
  private boolean draw = false;
  private int draw2 = 0;
  private boolean quit = false;

  public Game(){
    Scanner input = new Scanner(System.in);
    System.out.println("-------------------------------------------------------");
    System.out.println("                  WELCOME TO UNO!");
    System.out.println("-------------------------------------------------------");
    System.out.println();
    myDeck = new Deck();
    rules();

    if (!quit){
      int x = (int) (Math.random() * myDeck.getDeckLength()) ;
      Card first = myDeck.getCardInDeck(x);
      while (first.getType().equals("Wild") || first.getType().equals("+4") || first.getType().equals("+2") || first.getType().equals("Skip/Reverse") ){
        x = (int) (Math.random() * myDeck.getDeckLength()) ;
        first = myDeck.getCardInDeck(x);
      }
      myDeck.removeCardFromDeck(x);
      System.out.println("-------------------------------------------------------");
      System.out.println("FIRST CARD: " + first);
      System.out.println("-------------------------------------------------------");

      System.out.println();
      current = first;
      turn = 1;
      winner = new Player();  
      Player a = new Player(myDeck);
      Player b = new Player(myDeck);
      Player comp = new Player(myDeck);

      System.out.println("Would you like to play against a computer or a friend? Type '0' for computer and '1' for friend!");
      int computer = input.nextInt();
      System.out.println();

      if (computer==0){
        while(!done){
          if (turn%2==1){
            System.out.println("PLAYER A's TURN!");
            play(a, comp);
            System.out.println();
          } else{
            System.out.println("COMPUTER's TURN!");
            compPlay(comp, a);
            System.out.println();
          }
        }
      } else{
        while(!done){
          if (turn%2==1){
            System.out.println("PLAYER A's TURN!");
            play(a, b);
            System.out.println();
          } else{
            System.out.println("PLAYER B's TURN!");
            play(b, a);
            System.out.println();
          }
        }
      }
      if (done){
        if (winner.equals(a)){
          System.out.println("Player A is the winner!");
        } else if (winner.equals(b)){
          System.out.println("Player B is the winner!");
        } else{
          System.out.println("Computer is the winner!");
        }
        System.out.println("Good game!");
      }
    }
  }

  public void play(Player one, Player two){
    Scanner input = new Scanner(System.in);
    System.out.println("Here are your cards:");
    one.showHand();
    System.out.println();
    if (draw){
      System.out.println("Type 'P' to place a card  or 'X' to pass:");
    } else {
      System.out.println("Type 'P' to place a card, 'D' to draw another card, or 'X' to pass:");
    }
    String x = input.nextLine();
    getInput(one, two, x);
  }

  public void getInput(Player one, Player two, String x){
    Scanner input = new Scanner(System.in);
    if ((!(x.equals("P") || x.equals("D") || x.equals("X")))){
      System.out.println("Not a Valid Format. Type 'P' to place a card, 'D' to draw another card, or 'X' to pass:");
      x = input.nextLine();
      getInput(one, two, x);
    }
    else if (x.equals("P")){
      place(one, two);
      printCurrent();
    } 
    else if (x.equals("X")){
      if (current.getType().equals("+2")){
        System.out.print("You ");
        one.addMore(2*draw2);
        System.out.println("(From the draw 2+(s)!)");
        draw2 = 0;
      }
      turn++;
      printCurrent();
    }
    else {
      if (myDeck.getDeckLength()==0){
        System.out.println("No more cards in the deck, so you must pass!");
      } 
      else if (draw){
        System.out.println("Sorry, you must pass!");
        draw = false;
        turn++;
        printCurrent();
      }
      else{
        draw = true;
        System.out.print("You ");
        one.addMore(1);
        play(one,two);
      }
    } 

  }
  public void place(Player one, Player two){
    Scanner input = new Scanner(System.in);
    System.out.println("Pick a number from the cards shown or type 10 to pass:");
    int y = input.nextInt();
    if (y==10){
      return;
    }
    if (!(y<=one.getHandLength() && y>=1)){
      System.out.println("Not a valid card. ");
      place(one,two);
    }
    Card now = one.getCardInHand(y);
    if (current.getType().equals("+2") && now.getType().equals("+2")){
      System.out.println("Let's check if the other player has a +2!");
      draw2++;
      current = now;
      one.removeCardfromHand(y);
      turn++;
      check(one);
      draw = false;
      return;
    }
    if (current.getType().equals("+2") && !(now.getType().equals("+2"))){
      System.out.println("This card is not a +2, so you must draw cards!");
      System.out.print("You ");
      one.addMore(2*draw2);
      System.out.println("(From the draw 2+s!)");
      draw2 = 0;
      turn++;
      return;
    }

    if (now.getType().equals("Wild") || now.getType().equals("+4")){
      Scanner input2 = new Scanner(System.in);
      System.out.println("Please select a color from Red, Green, Yellow, or Blue:");
      String color = input2.nextLine();
      now = new Card(color, 0);
      System.out.println("You set the color to " + color);
      if (now.getType().equals("+4")){
        two.otherAddMore(4);
      }
      turn++;
      one.removeCardfromHand(y);
      check(one);
      draw = false;
      return;
    }
    else if (now.getColor().equals(current.getColor())){
      if (now.getType().equals("+2")){
        System.out.println("Let's check if the other player has a +2!");
        draw2++;
        turn++;
      } 
      else if (now.getType().equals("Skip/Reverse")){
        current = now;
        System.out.println("Still your turn!");
      }
      else {
        turn++;
      }
      current = now;
      one.removeCardfromHand(y);
      check(one);
      draw = false;
      return;
    }
    else if (now.getNum()==current.getNum()){
      current = now;
      turn++;
      one.removeCardfromHand(y);
      check(one);
      draw = false;
      return;
    }
    else{
      System.out.println("Not a valid card. Pick another one from your hand:");
      getInput(one,two,"P");
      return;
    }
  }

  public void compPlay(Player one, Player two){
    for (int i=1; i<=one.getHandLength(); i++){
      Card now = one.getCardInHand(i);
      if (current.getType().equals("+2") && now.getType().equals("+2")){
        System.out.println("Computer placed a +2!");
        System.out.println("Now, you can only play if you have a +2.");
        draw2++;
        current = now;
        one.removeCardfromHand(i);
        turn++;
        check(one);
        draw = false;
        printCurrent();
        return;
      }
      if (current.getType().equals("+2") && !(now.getType().equals("+2"))){
        System.out.print("Computer ");
        one.addMore(2*draw2);
        System.out.println("(From the draw 2+s!)");
        draw2 = 0;
        turn++;
        draw = false;
        printCurrent();
        return;
      }
      if (now.getType().equals("Wild") || now.getType().equals("+4")){
        String[] colors = {"Red", "Green", "Yellow", "Blue"};
        int r = (int) (Math.random() * 5);
        now = new Card(colors[r], 0);
        System.out.println("Computer set the color to " + colors[r]);
        if (now.getType().equals("+4")){
          two.otherAddMore(4);
        }
        turn++;
        one.removeCardfromHand(i);
        System.out.println("Computer placed a card!");
        check(one);
        printCurrent();
        draw = false;
        return;
      }
      else if (now.getColor().equals(current.getColor())){
        if (now.getType().equals("Skip/Reverse")){
          current = now;
          System.out.println("Computer Skipped/Reversed. Still computer's turn!");
        }
        else if (now.getType().equals("+2")){
          System.out.println("Computer placed a +2!");
          draw2++;
          turn++;
        } 
        else {
          turn++;

        }
        current = now;
        one.removeCardfromHand(i);
        System.out.println("Computer placed a card!");
        check(one);
        printCurrent();
        draw = false;
        return;
      }
      else if (now.getNum()==current.getNum()){
        current = now;
        turn++;
        one.removeCardfromHand(i);
        System.out.println("Computer placed a card!");
        check(one);
        printCurrent();
        draw = false;
        return;
      }
      else{
        if (i==one.getHandLength()){
          if (myDeck.getDeckLength()==0){
            System.out.println("No more cards in the deck, so Computer will pass!");
            turn++;
            printCurrent();
          } 
          else if (draw){
            turn++;
            System.out.println("Computer will pass!");
            printCurrent();
            draw = false;
            return;
          }
          else {
            System.out.print("Computer ");
            one.addMore(1);
            draw = true;
            compPlay(one, two);
          }
          return;
        }
      }
    }
    
  }

  public void printCurrent(){
    System.out.println();
    System.out.println("-------------------------------------------------------");
    System.out.println("CURRENT CARD: " + current);
    System.out.println("-------------------------------------------------------");
  }

  public void check(Player one){
    if (one.getHandLength()==1){
      System.out.println("<<<UNO!>>>");
      return;
    }
    if (one.getHandLength()==0){
      winner = one;
      done = true;
    }
  }

  public void rules(){
    Scanner input = new Scanner(System.in);
    System.out.println("Here are the RULES:");
    System.out.println("1. Each player starts with 7 cards.");
    System.out.println("2. Player A will place the first card. This is a 2-player game. You can play against a computer or another player.");
    System.out.println("3. You will have the option to place a card, draw a card, or pass at each turn.");
    System.out.println("4. You must match the current card in either color or number.");
    System.out.println("5. You may place a skip/reverse if the previous card was a skip/reverse, regardless of color");
    System.out.println("6. You can only place a +2 if the previous card was a +2, regardless of color.");
    System.out.println("7. If the previous card(s) is/are +2s, these cards will accumulate until one player does not play a +2. In this case, the player must draw all the accumulated cards. They will skip their turn.");
    System.out.println("8. If a player places a +4, they must determine the color. The enxt player will draw 4 cards and will skip their turn. You may not stack +4s.");
    System.out.println("9. Don't forget to have fun!");
    System.out.println();
    System.out.println("Are you ready to play? Type 'C' to continue or type 'Q' to quit:");
    String x = input.nextLine();
    if (x.equals("C")){
      System.out.println("Let's get started!");
      System.out.println();
      return;
    } else{
      System.out.println("Sorry to see you go! Bye!");
      quit = true;
      return;
    }
  }
}