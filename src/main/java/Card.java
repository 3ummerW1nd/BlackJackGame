public class Card {
    private Suit suit;
    private Point point;

    public Card(Suit suit, Point point) {
        this.suit = suit;
        this.point = point;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String print() {
        return getSuit().toString() + getPoint().toString() + " ";
    }

}
