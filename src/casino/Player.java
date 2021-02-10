package casino;

import java.util.ArrayList;
public class Player {

    private String nickName;
    private int playerNumOfCards;
    ArrayList<Card> playerHand;

    public Player(String name) {
        this.nickName = name;
        playerHand = new ArrayList<Card>();

    }

    public String getNickName() {
        return nickName;
    }

    public void addCard(Card aCard) {
        playerHand.add(aCard);
        this.playerNumOfCards++;
    }

    public int getHandSum() {
        int totalSum = 0;
        for (Card countSum : playerHand) {
            totalSum = totalSum + countSum.getFace();
        }
        return totalSum;
    }

    public void getPlayerHand(boolean hideCard) {
        System.out.println(this.nickName + "r current hand.");
        for (int c = 0; c < playerNumOfCards; c++) {
            if (c == 0 && !hideCard) {
                System.out.println("[Hidden card]");
            } else {
                System.out.println(playerHand.get(c).toString());
            }
        }
    }
}
