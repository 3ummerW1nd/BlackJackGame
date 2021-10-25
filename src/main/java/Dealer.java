import javax.swing.*;

public class Dealer extends Player {
  private boolean hide = true;

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

  @Override
  public String win(boolean blackJack) {
    setHide(false);
    if (blackJack)
      return "庄家达成了BLACKJACK！庄家胜利";
    else
      return "庄家胜利";
  }

  public void drawTillSeventeen(Deck deck) {
    while (getScore() < 17) {
      askForCard(deck);
    }
  }
}
