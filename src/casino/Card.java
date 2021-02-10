package casino;

public class Card {

    private Face face; 
    private Suit suit; 
    int total = 0;

    public Card(Face cardFace, Suit cardSuit) { 
        this.face = cardFace;
        this.suit = cardSuit;
    }

    public int getFace() {
        return face.getFaceValue();
    }

    public String getSuit() {
        return suit.PrintSuitText();
    }

    public String toString() { 
        return face + " OF " + suit;
    }
}
