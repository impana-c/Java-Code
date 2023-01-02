import java.util.ArrayList; 

public class Player{
  private ArrayList<Card> playerHand;
  private Deck classDeck;

  public Player(Deck playingDeck){
    playerHand = new ArrayList<Card>();
    classDeck = playingDeck; 
    for (int i = 0; i<3; i++){
      int x = (int) (Math.random() * classDeck.getDeckLength()) ;
      Card y = classDeck.getCardInDeck(x);
      playerHand.add(y);
      classDeck.removeCardFromDeck(x);
    }
  }
  public Player(){
    playerHand = new ArrayList<Card>();
  }

  public void removeCardfromHand(int x){
    playerHand.remove(x-1);
  }

  public void showHand(){
    for (int i=1; i<=playerHand.size(); i++){
      Card x = playerHand.get(i-1);
      System.out.println(i + ") " + x);
    }
  }

  public Card getCardInHand(int x){
    return playerHand.get(x-1);
  }

  public void addMore(int z){
    for (int i = 0; i<z; i++){
      int x = (int) (Math.random() * classDeck.getDeckLength()) ;
      Card y = classDeck.getCardInDeck(x);
      playerHand.add(y);
      classDeck.removeCardFromDeck(x);
    }
    System.out.println("drew " + z + " card(s)!");
  }

  public void otherAddMore(int z){
    for (int i = 0; i<z; i++){
      int x = (int) (Math.random() * classDeck.getDeckLength()) ;
      Card y = classDeck.getCardInDeck(x);
      playerHand.add(y);
      classDeck.removeCardFromDeck(x);
    }
    System.out.println("The next player has " + z + " more cards!");
  }

  public int getHandLength(){
    return playerHand.size();
  }

}