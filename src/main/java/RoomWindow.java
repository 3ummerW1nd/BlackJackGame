import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setOnClickListener();
    }

    private void setOnClickListener() {
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickHitButton();
                showHands();
            }
        });
        stayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickStayButton();
                showHands();
            }
        });
        doubleDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickDoubleDownButton();
                showHands();
            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickStartGameButton();
                showHands();
            }
        });
        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickQuitGameButton();
                showHands();
            }
        });
        showChipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickShowChipButton();
                showHands();
            }
        });
    }

    private void clickHitButton(){
        userPlayer.hit(deck);
        doubleDownButton.setEnabled(false);
        checkGameStatus(game.checkAfterHit());
    }

    private void clickStayButton() {
        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
        doubleDownButton.setEnabled(false);
        dealer.drawTillSeventeen(deck);
        dealer.setHide(false);
        checkGameStatus(game.checkAfterStay());
        startGameButton.setEnabled(true);
    }

    private void clickDoubleDownButton() {
        userPlayer.doubleDown(game);
        doubleDownButton.setEnabled(false);
    }

    private void clickStartGameButton() {
        int chip = askForChips();
        game = new Game(dealer, userPlayer, deck);
        userPlayer.bet(game, chip);
        game.initGame();
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
        startGameButton.setEnabled(false);
        checkGameStatus(game.checkAfterInit());
    }

    private void clickQuitGameButton() {

    }

    private void clickShowChipButton() {
        int chip = userPlayer.getChipAmount();
        GameInformationWindow gameInformationWindow = new GameInformationWindow();
        gameInformationWindow.show("筹码信息", "你现在还有" + userPlayer.getChipAmount() + "个筹码");
    }

    private void checkGameStatus(GameStatus gameStatus) {
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

    private int askForChips() {
        //TODO:弹窗读入筹码数量
        int chip = 0;
        return chip;
    }

    private void userWin(boolean blackJack) {
        gameOver();
        if(blackJack)
            showStatus("你达成了BLACKJACK！你赢了");
        else
            showStatus("你赢了");
    }

    private void dealerWin(boolean blackJack) {
        gameOver();
        if(blackJack)
            showStatus("庄家达成了BLACKJACK！你输了");
        else
            showStatus("你输了");
    }

    private void draw() {
        gameOver();
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
