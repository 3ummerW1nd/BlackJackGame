import java.util.*;

public class Deck {
  private Set<Card> cards = new HashSet<>();

  public void shuffle() {
    cards = new HashSet<>();
    List<Point> points = Point.getAllPoints();
    List<Suit> suits = Suit.getAllSuits();
    for (Point point : points) {
      for (Suit suit : suits) {
        Card card = new Card(suit, point);
        cards.add(card);
      }
    }
  }

  public Card nextCard() {
    ArrayList<Card> list = new ArrayList<>(cards);
    int randomIndex = new Random().nextInt(list.size());
    cards.remove(list.get(randomIndex));
    return list.get(randomIndex);
  }
}
