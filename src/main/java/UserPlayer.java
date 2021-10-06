import javax.swing.*;
import java.util.Scanner;

public class UserPlayer extends Player{
    private String name;
    private int chipAmount;
    Scanner scan;

    public UserPlayer() {
        this.chipAmount = 100;
        scan = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void showHand(JTextArea jTextArea) {
        jTextArea.setText("现在" + getName() + "拥有的手牌为：\n");
//        System.out.print("现在" + getName() + "拥有的手牌为：");
        for(Card card : getHand()) {
            jTextArea.append(card.print());
        }
        //System.out.println();
    }

    public void bet(Game game, int chip) {
//        int theRoundChips = Integer.parseInt(gameWindow.scanFromTextField());
//        if(theRoundChips > chipAmount) {
//            System.out.println(getName() + "没有足够的筹码了！");
//            bet(gameWindow);
//        }
//        chipAmount -= theRoundChips;
//        return theRoundChips;
        chipAmount -= chip;
        game.setChip(chip);
    }

    public int getChipAmount() {
        return chipAmount;
    }

    public void hit(Deck deck) {
        askForCard(deck);
    }

    public void doubleDown(Game game) {
        int chip = game.getChip();
        chipAmount -= chip;
        game.doubleChip();
    }

}
