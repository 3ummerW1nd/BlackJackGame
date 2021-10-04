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
    public void showHand(GameWindow gameWindow) {
        gameWindow.getTextArea3().setText("现在" + getName() + "拥有的手牌为：\n");
//        System.out.print("现在" + getName() + "拥有的手牌为：");
        for(Card card : getHand()) {
            gameWindow.getTextArea3().append(card.print());
        }
        //System.out.println();
    }

    public int bet(GameWindow gameWindow) {
        int theRoundChips = Integer.parseInt(gameWindow.scanFromTextField());
        if(theRoundChips > chipAmount) {
            System.out.println(getName() + "没有足够的筹码了！");
            bet(gameWindow);
        }
        chipAmount -= theRoundChips;
        return theRoundChips;
    }

    public void addBet(int chip) {
        chipAmount -= chip;
    }

    public void addChip(int chip) {
        chipAmount += chip;
    }

    public void scanName(GameWindow gameWindow) {
//        name = scan.nextLine();
        name = gameWindow.scanFromTextField();
    }

    public int getChipAmount() {
        return chipAmount;
    }

    public void setChipAmount(int chipAmount) {
        this.chipAmount = chipAmount;
    }

    public boolean choice(GameWindow gameWindow) {
        int x = gameWindow.scanIntFromTextArea();
        if(x == 1)
            return true;
        else
            return false;
    }
}
