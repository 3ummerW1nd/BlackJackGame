import javax.swing.*;

public class Dealer extends Player{

    boolean hide = true;

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    @Override
    public String getName() {
        return "庄家";
    }

    @Override
    public void showHand(JTextArea jTextArea) {
        //System.out.print("现在" + getName() + "拥有的手牌为：");
        jTextArea.setText("现在" + getName() + "拥有的手牌为：\n");
        if(hide) {
            for(int i = 0; i < getHand().size(); i ++) {
                if(i == 0) {
                    jTextArea.append("暗牌\n");
                } else {
                    jTextArea.append(getHand().get(i).print());
                }
            }
        } else {
            for(Card card : getHand()) {
                jTextArea.append(card.print());
                //card.print();
            }
        }
    }

    public void showHideCard() {

        System.out.print("庄家的暗牌是 ");
        getHand().get(0).print();
        System.out.println();
        hide = false;
    }

    public void drawTillSeventeen(Deck deck){
        while(getScore() < 17) {
            askForCard(deck);
        }
    }

}
