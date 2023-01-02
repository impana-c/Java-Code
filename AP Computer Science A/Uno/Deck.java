import java.util.ArrayList; 
public class Deck{
  private ArrayList<Card> cardDeck;

  public Deck(){
    cardDeck = new ArrayList<Card>();
    String[] colors = {"Red", "Green", "Yellow", "Blue"};
    for (String x : colors){
      for (double i=0.5; i<4.75; i+=0.25) {
        int y = (int) (2 * i);
        cardDeck.add(new Card(x,y));
      }
      for (int i=0; i<2; i++){
        cardDeck.add(new Card(x,"+2"));
        cardDeck.add(new Card(x,"Skip/Reverse"));
      }
    }
    for (int i=0; i<4; i++){
      cardDeck.add(new Card("Choose","Wild"));
      cardDeck.add(new Card("Choose","+4"));
    }
  }

  public void showDeck(){
    for (Card x : cardDeck){
      System.out.println(x);
    }
  }

  public void removeCardFromDeck(int x){
    cardDeck.remove(x);
  }

  public int getDeckLength(){
    return cardDeck.size();
  }

  public Card getCardInDeck(int x){
    return cardDeck.get(x);
  }
}