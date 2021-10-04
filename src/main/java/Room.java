import javax.swing.*;

public class Room {
    private Dealer dealer;
    private UserPlayer userPlayer;
    private GameWindow gameWindow;

    public void initRoom() {
        dealer = new Dealer();
        userPlayer = new UserPlayer();
        JFrame jFrame = new JFrame("BlackJack游戏");
        gameWindow = new GameWindow(jFrame);
        gameWindow.getTextArea2().append("您好，请输入您的姓名：\n");
        //System.out.println("您好，请输入您的姓名：");
        userPlayer.scanName(gameWindow);
        gameWindow.getTemp().setText("false");
        gameWindow.getTextArea2().append("您好" + userPlayer.getName() + ", 很高兴认识你！送你100个筹码\n");
        //System.out.println("您好" + userPlayer.getName() + ", 很高兴认识你！");
    }

    public void askForGame() {
        gameWindow.getTextArea2().append(userPlayer.getName() + "请问是否要赌一把\n");
        //System.out.println(userPlayer.getName() + "请问是否要赌一把？");
        while(userPlayer.choice(gameWindow)) {
            gameWindow.getTemp1().setText("");
            gameWindow.clearWindows();
            playGame();
            gameWindow.getTextArea2().append(userPlayer.getName() + "你现在还有" + userPlayer.getChipAmount() + "个筹码\n");
            //System.out.println(userPlayer.getName() + "你现在还有" + userPlayer.getChipAmount() + "个筹码");
            if(userPlayer.getChipAmount() <= 0) {
                gameWindow.getTextArea2().append(userPlayer.getName() + "，你已经输完啦！别赌啦！\n");
                //System.out.println(userPlayer.getName() + "，你已经输完啦！别赌啦！");
                break;
            }
            gameWindow.getTextArea2().append(userPlayer.getName() + "请问是否要再来赌一把？\n");
            //System.out.println(userPlayer.getName() + "请问是否要再来赌一把？");
        }
    }

    public void playGame() {
        Game game = new Game(dealer, userPlayer, gameWindow);
        game.initGame();
        if(game.check())
            game.playGame();
    }

}
