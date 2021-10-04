import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Card> hand;
    private String name;

    public abstract String getName();

    public abstract void showHand(GameWindow gameWindow);

    public Player() {
        hand = new ArrayList<>();
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
        int score = 0;
        int aceCount = 0;
        for(Card card : getHand()) {
            if(card.getPoint() == Point.A)
                aceCount ++;
            else
                score += card.getPoint().getValue();
        }
        while(aceCount -- > 0) {
            if(score + 11 <= 21)
                score += 11;
            else
                score += 1;
        }
        return score;
    }

    public void askForCard(Deck deck) {
        Card card = deck.nextCard();
        Point point = card.getPoint();
        hand.add(card);
    }

    public void blackJack() {
        System.out.print(getName()+"达成了blackJack！");
        for(Card card : getHand()) {
            card.print();
        }
        System.out.println();
    }
}
