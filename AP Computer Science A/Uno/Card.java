public class Card{
  private String color;
  private int num;
  private String type;

  public Card(String c, int n){
    color = c;
    num = n;
    type = "Normal";
  }

  public Card(String c, String t){
    color = c;
    num = 0;
    type = t;
  }

  public String getColor(){
    return color;
  }

  public int getNum(){
    return num;
  }

  public String getType(){
    return type;
  }

  public void setColor(String x){
    color = x;
  }

  public String toString(){
    return "Color: " + this.color + ", Number: " + this.num + ", Type: " + this.type;
  }
}