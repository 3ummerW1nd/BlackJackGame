import java.util.ArrayList;

public class Game {
  private Dealer dealer;
  private UserPlayer userPlayer;
  private Deck deck;
  private int chip;

  public void setChip(int chip) {
    this.chip = chip;
  }

  public int getChip() {
    return chip;
  }

  public void doubleChip() {
    this.chip = this.chip * 2;
  }

  public Game(Dealer dealer, UserPlayer userPlayer, Deck deck) {
    this.dealer = dealer;
    this.userPlayer = userPlayer;
    this.deck = deck;
  }

  public void initGame() {
    dealer.setHide(true);
    dealer.setHand(new ArrayList<>());
    userPlayer.setHand(new ArrayList<>());
    deck.shuffle();
    dealer.askForCard(deck);
    userPlayer.askForCard(deck);
    dealer.askForCard(deck);
    userPlayer.askForCard(deck);
  }

  public GameStatus checkAfterInit() {
    if (userPlayer.getScore() == 21 && dealer.getScore() == 21) {
      return GameStatus.DRAW;
    } else if (userPlayer.getScore() == 21) {
      return GameStatus.USERWINWITHBLACKJACK;
    } else if (userPlayer.getScore() == 11 && userPlayer.getChipAmount() >= chip) {
      return GameStatus.CANDOUBLE;
    }
    return GameStatus.CONTINUE;
  }

  public GameStatus checkAfterHit() {
    if (userPlayer.getScore() > 21) {
      return GameStatus.DEALERWIN;
    } else if (userPlayer.getScore() == 21) {
      return GameStatus.USERWIN;
    }
    return GameStatus.CONTINUE;
  }

  public GameStatus doubleDown() {
    userPlayer.doubleDown();
    doubleChip();
    return GameStatus.AFTERDOUBLEDOWN;
  }

  public GameStatus checkAfterStay() {
    if (dealer.getScore() > 21) {
      return GameStatus.USERWIN;
    } else if (dealer.getScore() == 21) {
      if (dealer.getHand().size() == 2)
        return GameStatus.DEALERWINWITHBLACKJACK;
      return GameStatus.DEALERWIN;
    } else {
      if (userPlayer.getScore() > dealer.getScore()) {
        return GameStatus.USERWIN;
      } else if (userPlayer.getScore() == dealer.getScore()) {
        return GameStatus.DRAW;
      } else {
        return GameStatus.DEALERWIN;
      }
    }
  }
}
