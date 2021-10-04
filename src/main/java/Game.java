import java.util.ArrayList;

public class Game {
    private GameWindow gameWindow;
    private Dealer dealer;
    private UserPlayer userPlayer;
    private Deck deck;
    private int chip;

    public Game(Dealer dealer, UserPlayer userPlayer, GameWindow gameWindow) {
        this.dealer = dealer;
        this.userPlayer = userPlayer;
        this.gameWindow = gameWindow;
    }

    public void initGame() {
        dealer.setHide(true);
        dealer.setHand(new ArrayList<>());
        userPlayer.setHand(new ArrayList<>());
//        System.out.println("您好" + userPlayer.getName() + ", 请输入你要赌上的筹码数量：");
        gameWindow.getTextArea2().append("您好" + userPlayer.getName() + ", 请输入你要赌上的筹码数量：\n");
        gameWindow.getTemp().setText("false");
        chip = userPlayer.bet(gameWindow);
        deck = new Deck();
        deck.getNewDeck();
        dealer.askForCard(deck);
        dealer.askForCard(deck);
        dealer.showHand(gameWindow);
        userPlayer.askForCard(deck);
        userPlayer.askForCard(deck);
        userPlayer.showHand(gameWindow);
    }

    public boolean check() {
        if(userPlayer.getScore() == 21 && dealer.getScore() == 21) {
            dealer.showHideCard();
            userPlayer.blackJack();
            dealer.blackJack();
            draw();
            return false;
        } else if(userPlayer.getScore() == 21){
            userPlayer.blackJack();
            userWin();
            return false;
        } else if(dealer.getScore() == 21) {
            dealer.showHideCard();
            dealer.blackJack();
            userLoss();
            return false;
        } else if(userPlayer.getScore() >= 17 && userPlayer.getChipAmount() >= chip){
            gameWindow.getTextArea2().append(userPlayer.getName() + "你的点数目前大于17点，富贵险中求，请问是否加倍投注？\n");
//            System.out.println(userPlayer.getName() + "你的点数目前大于17点，富贵险中求，请问是否加倍投注？");
            if(userPlayer.choice(gameWindow)) {
                gameWindow.getTemp1().setText("");
                userPlayer.addBet(chip);
                chip *= 2;
            }
        }
        return true;
    }

    public void playGame() {
        gameWindow.getTextArea2().append("请问是否继续抽牌？\n");
//        System.out.println("请问是否继续抽牌？");
        while(userPlayer.choice(gameWindow)){
            gameWindow.getTemp1().setText("");
            userPlayer.askForCard(deck);
            userPlayer.showHand(gameWindow);
            if(userPlayer.getScore() > 21) {
                userLoss();
                return;
            } else if(userPlayer.getScore() == 21) {
                break;
            }
            gameWindow.getTextArea2().append("请问是否还要继续抽牌？\n");
//            System.out.println("请问是否还要继续抽牌？");
        }
        dealer.showHideCard();
        dealer.showHand(gameWindow);
        while(dealer.getScore() < 17) {
            dealer.askForCard(deck);
            dealer.showHand(gameWindow);
            if(dealer.getScore() > 21) {
                userWin();
                return;
            } else if(dealer.getScore() == 21) {
                break;
            }
        }
        if(userPlayer.getScore() > dealer.getScore()) {
            userWin();
            return;
        } else if(userPlayer.getScore() == dealer.getScore()){
            draw();
            return;
        }
        userLoss();
    }

    public void draw() {
        //gameWindow.clearWindows();
        gameWindow.getTextArea2().append("平局了！\n");
//        System.out.println("平局了！");
        userPlayer.addChip(chip);
    }

    public void userWin() {
        //gameWindow.clearWindows();
        gameWindow.getTextArea2().append(userPlayer.getName() +"你赢了！\n");
//        System.out.println(userPlayer.getName() + ", 你赢了！");
        userPlayer.addChip((int)(chip * 1.5));
    }

    public void userLoss() {
        //gameWindow.clearWindows();
        gameWindow.getTextArea2().append(userPlayer.getName() + ", 你输了。\n");
//        System.out.println(userPlayer.getName() + ", 你输了。");
    }
}
