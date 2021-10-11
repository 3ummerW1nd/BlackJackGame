import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public abstract class Player {
  private List<Card> hand;

  public abstract void showHand(JTextArea jTextArea);

  public Player() {
    hand = new ArrayList<>();
  }

  public void setHand(List<Card> hand) {
    this.hand = hand;
  }

  public List<Card> getHand() {
    return hand;
  }

  public int getScore() {
    int score = 0;
    int aceCount = 0;
    for (Card card : getHand()) {
      if (card.getPoint() == Point.A)
        aceCount++;
      else
        score += card.getPoint().getValue();
    }
    while (aceCount-- > 0) {
      if (score + 11 <= 21)
        score += 11;
      else
        score += 1;
    }
    return score;
  }

  public void askForCard(Deck deck) {
    Card card = deck.nextCard();
    Point point = card.getPoint();
    hand.add(card);
  }
}
