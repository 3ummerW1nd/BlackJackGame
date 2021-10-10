import javax.swing.*;
import java.util.Scanner;

public class UserPlayer extends Player{
    private int chipAmount;
    Scanner scan;

    public void addChipAmount(int chipAmount) {
        this.chipAmount += chipAmount;
    }

    public UserPlayer() {
        this.chipAmount = 100;
        scan = new Scanner(System.in);
    }

    @Override
    public void showHand(JTextArea jTextArea) {
        jTextArea.setText("现在玩家拥有的手牌为：\n");
        for(Card card : getHand()) {
            jTextArea.append(card.print());
        }
        //System.out.println();
    }

    public void bet(Game game, int chip) {
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
