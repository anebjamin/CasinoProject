package casino;

import java.util.Random;

public class DeckOfCards {

    private Card[] deck;
    private static final Random random = new Random();

    private int currentCard;
    private static int NUMBER_OF_CARDS = 52;

    public DeckOfCards() {

        Face[] faces = {Face.ACE, Face.DEUCE, Face.THREE, Face.FOUR, Face.FIVE, Face.SIX,
            Face.SEVEN, Face.EIGHT, Face.NINE, Face.TEN, Face.JACK, Face.QUEEN,
            Face.KING};
        Suit[] suits = {Suit.HEARTS, Suit.SPADES, Suit.DIAMONDS, Suit.CLUBS};

        deck = new Card[NUMBER_OF_CARDS];
        currentCard = 0;

        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public void shuffleDeck() {
        currentCard = 0;

        for (int first = 0; first < deck.length; first++) {

            int second = random.nextInt(NUMBER_OF_CARDS);

            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    public void getCardDeck() {
        int start = 1;
        for (Card k : deck) {
            System.out.println("" + start + "/52 " + k);
            start++;
        }
    }

    public Card dealNextCard() {
        Card topCard = this.deck[0];
        for (int currentCard = 1; currentCard < NUMBER_OF_CARDS; currentCard++) {
            this.deck[currentCard - 1] = this.deck[currentCard];
        }
        this.deck[NUMBER_OF_CARDS - 1] = null;
        this.NUMBER_OF_CARDS--;
        return topCard;
    }
}
