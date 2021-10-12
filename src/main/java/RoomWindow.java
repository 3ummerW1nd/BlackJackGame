import java.awt.event.WindowEvent;
import javax.swing.*;

public class RoomWindow {
  private Dealer dealer;
  private UserPlayer userPlayer;
  private Game game;
  private Deck deck;
  private JFrame jFrame;
  private JPanel mainJPanel;
  private JButton doubleDownButton;
  private JButton stayButton;
  private JButton hitButton;
  private JButton startGameButton;
  private JButton showChipButton;
  private JButton quitGameButton;
  private JPanel ButtonJPanel;
  private JPanel showCardsJPanel;
  private JPanel dealerHandJPanel;
  private JPanel userHandJPanel;
  private JTextArea userPlayerHandTextArea;
  private JTextArea dealerHandTextArea;
  private GameInformationWindow gameInformationWindow;

  public void initRoom() {
    dealer = new Dealer();
    userPlayer = new UserPlayer();
    deck = new Deck();
    jFrame = new JFrame("BlackJack游戏");
    jFrame.setContentPane(mainJPanel);
    jFrame.setVisible(true);
    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    jFrame.setSize(600, 600);
    jFrame.setLocation(300, 150);
    hitButton.setEnabled(false);
    stayButton.setEnabled(false);
    doubleDownButton.setEnabled(false);
    gameInformationWindow = new GameInformationWindow();
    gameInformationWindow.getButton1().addActionListener(e -> {
      int chips = Integer.parseInt(gameInformationWindow.getStatusTextField().getText());
      if (chips > userPlayer.getChipAmount()) {
        new GameInformationWindow().show("下注失败", "余额不足");
      } else if (chips <= 0) {
        new GameInformationWindow().show("下注失败", "下注筹码数必须大于0");
      } else {
        userPlayer.bet(game, chips);
        game.initGame();
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
        startGameButton.setEnabled(false);
        showHands();
        dealGameStatus(game.checkAfterInit());
      }
      gameInformationWindow.close();
    });
    setOnClickListener();
  }

  private void setOnClickListener() {
    hitButton.addActionListener(e -> {
      clickHitButton();
      showHands();
    });
    stayButton.addActionListener(e -> {
      clickStayButton();
      showHands();
    });
    doubleDownButton.addActionListener(e -> {
      clickDoubleDownButton();
      showHands();
    });
    startGameButton.addActionListener(e -> {
      clickStartGameButton();
      showHands();
    });
    quitGameButton.addActionListener(e -> {
      clickQuitGameButton();
      showHands();
    });
    showChipButton.addActionListener(e -> {
      clickShowChipButton();
      showHands();
    });
  }

  private void clickHitButton() {
    userPlayer.hit(deck);
    doubleDownButton.setEnabled(false);
    dealGameStatus(game.checkAfterHit());
  }

  private void clickStayButton() {
    hitButton.setEnabled(false);
    stayButton.setEnabled(false);
    doubleDownButton.setEnabled(false);
    dealer.drawTillSeventeen(deck);
    dealer.setHide(false);
    dealGameStatus(game.checkAfterStay());
    startGameButton.setEnabled(true);
  }

  private void clickDoubleDownButton() {
    userPlayer.doubleDown(game);
    doubleDownButton.setEnabled(false);
  }

  private void clickStartGameButton() {
    deck = new Deck();
    game = new Game(dealer, userPlayer, deck);
    gameInformationWindow.showEnable("请问您想要赌上几个筹码？", "");
  }

  private void clickQuitGameButton() {
    jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
  }

  private void clickShowChipButton() {
    GameInformationWindow gameInformationWindow = new GameInformationWindow();
    gameInformationWindow.show("筹码信息", "你现在还有" + userPlayer.getChipAmount() + "个筹码");
  }

  private void dealGameStatus(GameStatus gameStatus) {
    switch (gameStatus) {
      case USERWIN:
        userWin(false);
        break;
      case USERWINWITHBLACKJACK:
        userWin(true);
        break;
      case DEALERWIN:
        dealerWin(false);
        break;
      case DEALERWINWITHBLACKJACK:
        dealerWin(true);
        break;
      case DRAW:
        draw();
        break;
      case CANDOUBLE:
        canDouble();
        break;
    }
  }

  private void userWin(boolean blackJack) {
    gameOver();
    if (blackJack) {
      userPlayer.addChipAmount((int) (game.getChip() * 2.5));
      showStatus("你达成了BLACKJACK！你赢了");
    } else {
      userPlayer.addChipAmount(game.getChip() * 2);
      showStatus("你赢了");
    }
  }

  private void dealerWin(boolean blackJack) {
    gameOver();
    if (blackJack)
      showStatus("庄家达成了BLACKJACK！你输了");
    else
      showStatus("你输了");
  }

  private void draw() {
    gameOver();
    userPlayer.addChipAmount(game.getChip());
    showStatus("平局了");
  }

  private void canDouble() {
    doubleDownButton.setEnabled(true);
  }

  private void show(Player player, JTextArea jTextArea) {
    player.showHand(jTextArea);
  }

  private void showHands() {
    show(userPlayer, userPlayerHandTextArea);
    show(dealer, dealerHandTextArea);
  }

  private void showStatus(String status) {
    GameInformationWindow gameInformationWindow = new GameInformationWindow();
    gameInformationWindow.show("游戏结束", status);
  }

  private void gameOver() {
    dealer.setHide(false);
    showHands();
    hitButton.setEnabled(false);
    stayButton.setEnabled(false);
    doubleDownButton.setEnabled(false);
    startGameButton.setEnabled(true);
  }
}
