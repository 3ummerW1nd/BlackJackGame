import javax.swing.*;

public class UserPlayer extends Player {
  private int chipAmount;
  private int chipThisGame;

  public void addChipAmount(int chipAmount) {
    this.chipAmount += chipAmount;
  }

  public UserPlayer() {
    this.chipAmount = 100;
  }

  @Override
  public void showHand(JTextArea jTextArea) {
    jTextArea.setText("现在玩家拥有的手牌为：\n");
    for (Card card : getHand()) {
      jTextArea.append(card.print());
    }
  }

  @Override
  public String win(boolean blackJack) {
    if (blackJack) {
      addChipAmount((int) (chipThisGame * 2.5));
      return "你达成了BLACKJACK！你赢了";
    } else {
      addChipAmount(chipThisGame * 2);
      return "你赢了";
    }
  }

  public void bet(Game game, int chip) {
    chipAmount -= chip;
    game.setChip(chip);
    chipThisGame = chip;
  }

  public int getChipAmount() {
    return chipAmount;
  }

  public void hit(Deck deck) {
    askForCard(deck);
  }

  public void doubleDown() {
    chipAmount -= chipThisGame;
  }
}
