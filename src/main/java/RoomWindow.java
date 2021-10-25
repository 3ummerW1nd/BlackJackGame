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
        gameInformationWindow.close();
        userPlayer.bet(game, chips);
        game.initGame(dealer, userPlayer, deck);
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
        startGameButton.setEnabled(false);
        showHands();
        dealGameStatus(game.checkAfterInit(userPlayer, dealer));
      }
    });
    setOnClickListener();
  }

  private void setOnClickListener() {
    hitButton.addActionListener(e -> {
      clickHitButton();
    });
    stayButton.addActionListener(e -> {
      clickStayButton();
    });
    doubleDownButton.addActionListener(e -> {
      clickDoubleDownButton();
    });
    startGameButton.addActionListener(e -> {
      clickStartGameButton();
      showHands();
    });
    quitGameButton.addActionListener(e -> {
      clickQuitGameButton();
    });
    showChipButton.addActionListener(e -> {
      clickShowChipButton();
    });
  }

  private void clickDoubleDownButton() {
    showHands();
    gameInformationWindow.show("你选择了加倍", "你将自动获得一张牌，之后自动选择停牌");
    dealGameStatus(game.doubleDown(userPlayer));
  }

  private void clickStayButton() {
    hitButton.setEnabled(false);
    stayButton.setEnabled(false);
    doubleDownButton.setEnabled(false);
    dealer.drawTillSeventeen(deck);
    dealer.setHide(false);
    dealGameStatus(game.checkAfterStay(userPlayer, dealer));
    startGameButton.setEnabled(true);
    showHands();
  }

  private void clickHitButton() {
    userPlayer.hit(deck);
    doubleDownButton.setEnabled(false);
    dealGameStatus(game.checkAfterHit(userPlayer));
    showHands();
  }

  private void clickStartGameButton() {
    deck = new Deck();
    game = new Game();
    gameInformationWindow.showEnable("请问您想要赌上几个筹码？", "");
    showHands();
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
        gameOver();
        showStatus(userPlayer.win(false));
        break;
      case USERWINWITHBLACKJACK:
        gameOver();
        showStatus(userPlayer.win(true));
        break;
      case DEALERWIN:
        gameOver();
        showStatus(dealer.win(false));
        break;
      case DEALERWINWITHBLACKJACK:
        gameOver();
        showStatus(dealer.win(true));
        break;
      case DRAW:
        gameOver();
        userPlayer.addChipAmount(game.getChip());
        showStatus("平局了");
        break;
      case CANDOUBLE:
        doubleDownButton.setEnabled(true);
        break;
      case AFTERDOUBLEDOWN:
        doubleDownButton.setEnabled(false);
        userPlayer.hit(deck);
        doubleDownButton.setEnabled(false);
        GameStatus status = game.checkAfterHit(userPlayer);
        if(status == GameStatus.CONTINUE) {
          clickStayButton();
        } else {
          dealer.setHide(false);
          dealGameStatus(status);
        }
        showHands();
    }
  }

  private void showHands() {
    userPlayer.showHand(userPlayerHandTextArea);
    dealer.showHand(dealerHandTextArea);
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
