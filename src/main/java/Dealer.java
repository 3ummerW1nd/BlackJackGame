import javax.swing.*;

public class Dealer extends Player {
  boolean hide = true;

  public void setHide(boolean hide) {
    this.hide = hide;
  }

  @Override
  public void showHand(JTextArea jTextArea) {
    jTextArea.setText("现在庄家拥有的手牌为：\n");
    if (hide) {
      for (int i = 0; i < getHand().size(); i++) {
        if (i == 0) {
          jTextArea.append("暗牌\n");
        } else {
          jTextArea.append(getHand().get(i).print());
        }
      }
    } else {
      for (Card card : getHand()) {
        jTextArea.append(card.print());
        // card.print();
      }
    }
  }

  public void drawTillSeventeen(Deck deck) {
    while (getScore() < 17) {
      askForCard(deck);
    }
  }
}
