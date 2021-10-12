public class Card {
  private Suit suit;
  private Point point;

  public Card(Suit suit, Point point) {
    this.suit = suit;
    this.point = point;
  }

  public Suit getSuit() {
    return suit;
  }

  public Point getPoint() {
    return point;
  }

  public String print() {
    return getSuit().toString() + getPoint().toString() + "\n";
  }
}
