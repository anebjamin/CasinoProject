package casino;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.Random;
import java.util.ArrayList;

public class Casino {

    public static Scanner input = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        signUp();    
    }

    public static void logInMenu() throws FileNotFoundException, IOException {
        System.out.println("Please enter what you would like to do\n  1) sign up\n  2) log in\n  3) exit\n");
        int menuSelect = input.nextInt();
        switch (menuSelect) {
            case 1:
                signUp();
                break;
        }
    }

    public static void gamesMenu(int userBalance) throws FileNotFoundException, IOException {
        userBalance = userBalance;
        System.out.println("what game would you like to play?\n 1: roulette\n 2: blackjack\n 3: higher or lower\n 4: exit");
        int menuSelect = input.nextInt();
        switch (menuSelect) {
            case 1:
                roulette(userBalance);
                break;
            case 2:
                blackjack(userBalance);
                break;
            case 3:
                higherLower(userBalance);
                break;
            case 4:
                signOut(userBalance);
                break;

        }
    }

    public static void signUp() throws FileNotFoundException, IOException {
        String folderDirectory = System.getProperty("user.dir") + "\\users.txt";
        File file = new File(folderDirectory);
        ArrayList<userBuild> masterList = new ArrayList<>();
        Scanner scanFile = new Scanner(file);
        int count = 0;
        while (scanFile.hasNextLine()) {
            String userID = scanFile.next();
            String userName = scanFile.next();
            String userPW = scanFile.next();
            String userBal = scanFile.next();
            userBuild newUser = new userBuild(userID, userName, userPW, userBal);
            masterList.add(newUser);
            count++;
        }
        String lastUser = (masterList.get(count - 1)).toString();
        int NewUserID = Integer.parseInt(lastUser.substring(0, 4));
        int ser = NewUserID + 1;
        System.out.println("enter username");
        String userName = input.next();
        System.out.println("enter password");
        String userPW = input.next();
        int userBalanc = 1000;
        String userID = Integer.toString(ser);
        String userBal = Integer.toString(userBalanc);
        userBuild newUser = new userBuild(userID, userName, userPW, userBal);
        System.out.println("new user created:\n" + "ID   name  password  balance\n" + newUser);
        masterList.add(newUser);

        FileWriter myWriter = new FileWriter(file);
        for (int i = 0; i < masterList.size(); i++) {
            String toBeWritten = (masterList.get(i)).toString();
            //System.out.println(toBeWritten);
            myWriter.write(toBeWritten);
            if (i < masterList.size() - 1) {
                myWriter.write("\n");
            }
        }
        myWriter.close();
        int userBalance = 1000;
        gamesMenu(userBalance);
    }

    private static void signOut(int userBalance) throws FileNotFoundException, IOException {
        String folderDirectory = System.getProperty("user.dir") + "\\users.txt";
        File file = new File(folderDirectory);
        ArrayList<userBuild> masterList = new ArrayList<>();
        Scanner scanFile = new Scanner(file);
        int count = 0;
        while (scanFile.hasNextLine()) {
            String userID = scanFile.next();
            String userName = scanFile.next();
            String userPW = scanFile.next();
            String userBal = scanFile.next();
            userBuild newUser = new userBuild(userID, userName, userPW, userBal);
            masterList.add(newUser);
            count++;
        }
        String newBalance = Integer.toString(userBalance);
        masterList.get(count - 1).setUserBal(newBalance);
        System.out.println(masterList.get(count - 1));

        FileWriter myWriter = new FileWriter(file);
        for (int i = 0; i < masterList.size(); i++) {
            String toBeWritten = (masterList.get(i)).toString();
            //System.out.println(toBeWritten);
            myWriter.write(toBeWritten);
            if (i < masterList.size() - 1) {
                myWriter.write("\n");
            }
        }
        myWriter.close();
    }

    private static void roulette(int userBalance) throws FileNotFoundException, IOException {

        int rouletteBux;
        System.out.println("select your number (odds are black, evens are red, 0 is green)");
        System.out.print("\u001b[32m" + "0" + "\u001B[30m" + ", ");
        for (int i = 1; i < 37; i++) {
            System.out.print(i + ", ");
            i++;
            System.out.print("\u001b[31m" + i + "\u001B[30m" + ", ");

        }
        System.out.println("");
        int userNum = input.nextInt();
        System.out.println("you have selected number " + userNum + ", how much would you like to bet? (max 100 bux)");

        rouletteBux = input.nextInt();
        userBalance -= rouletteBux;
        int rouletteWinNumber = rand.nextInt(36);
        if ((rouletteWinNumber % 2 == 1 && userNum % 2 == 1) || (rouletteWinNumber % 2 == 0 && userNum % 2 == 0)) {
            if ((rouletteWinNumber == userNum)) {
                if (rouletteWinNumber == 0) {
                    System.out.println("wow! the ball landed on zero!");
                    rouletteBux = rouletteBux * 15;
                    System.out.println("you won " + rouletteBux + " bux");
                } else {
                    System.out.println("the ball landed on your number!");
                    rouletteBux = rouletteBux * 5;
                    System.out.println("you won " + rouletteBux + " bux");
                }

            } else {
                System.out.println("the ball landed on your colour!");
                rouletteBux = rouletteBux + 100;
                System.out.println("you won " + rouletteBux + " bux");

            }
            userBalance += rouletteBux;
        } else {
            System.out.println("you lost!");
        }

        gamesMenu(userBalance);
    }

    public static void higherLower(int userBalance) throws IOException {
        String[] cardFace = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",};
        String[] cardSuit = {"Clubs", "Hearts", "Spades", "Diamonds"};
        boolean winning = true;
        int winnings = 0;
        while (winning == true) {
            userBalance-=25;
            int cardRandomFace = rand.nextInt(13);
            int cardRandomSuit = rand.nextInt(4);
            System.out.println("the current card is the " + cardFace[cardRandomFace] + " of " + cardSuit[cardRandomSuit]);
            System.out.println("higher or lower?ACE=low(h/l)");
            String highLow = input.next().toLowerCase();
            int numberStore = cardRandomFace;
            cardRandomFace = rand.nextInt(13);
            cardRandomSuit = rand.nextInt(4);
            System.out.println("the next card is the " + cardFace[cardRandomFace] + " of " + cardSuit[cardRandomSuit]);
            if (((highLow.equals("high")) && (cardRandomFace > numberStore)) || ((highLow.equals("low")) && (cardRandomFace < numberStore))) {
                System.out.println("you win! play again?(Y/N)");
                String PlayerContinue = input.next().toLowerCase();
                if (PlayerContinue.equals("n")) {
                    winning = false;
                } else {
                    winnings += 50;
                }
            } else {
                System.out.println("you lose!");
                winnings=0;
            }
        }userBalance+=winnings;
        gamesMenu(userBalance);
    }

    private static void blackjack(int userBalance) {
        boolean stay = false;
        Player me = new Player("you");
        Player dealer = new Player("Dealer");

        System.out.println("Would you like to start a new game?  'Yes/No' :");
        String pAnswer = input.next();

        if (pAnswer.equalsIgnoreCase("Yes")) {
            System.out.println("how much money would you like to bet?(max 100)");
            int blackjackBux = input.nextInt();
            userBalance -= blackjackBux;
            DeckOfCards deck1 = new DeckOfCards();
            Card card1 = new Card(Face.ACE, Suit.CLUBS);
            deck1.shuffleDeck();

            me.addCard(deck1.dealNextCard());
            me.addCard(deck1.dealNextCard());
            me.getPlayerHand(true);
            System.out.println(" ");
            dealer.addCard(deck1.dealNextCard());
            dealer.addCard(deck1.dealNextCard());
            dealer.getPlayerHand(false);

            do {
                System.out.println("Would " + me.getNickName() + " like to bust or stick? 'Bust/Stick'");
                pAnswer = input.next();
                if (pAnswer.equalsIgnoreCase("Bust")) {
                    me.addCard(deck1.dealNextCard());
                    System.out.println(me.getHandSum());
                    if (me.getHandSum() > 21) {
                        System.out.println("You busted and got a total of " + me.getHandSum() + ". Dealer wins this time! ");
                        System.exit(0);
                    }
                }
                if (pAnswer.equalsIgnoreCase("stick")) {
                    System.out.println("You have chosen to stick. Your hand: " + me.getHandSum());
                }

            } while (pAnswer.equalsIgnoreCase("Bust"));
            stay = false;

            do {
                System.out.println("\n- Dealers turn -");
                if (dealer.getHandSum() <= 15) {
                    dealer.addCard(deck1.dealNextCard());
                    if (dealer.getHandSum() == 15) {
                        System.out.println("Blackjack! Dealer won.");
                        System.exit(0);
                    }
                    if (dealer.getHandSum() > 21) {
                        System.out.println("Dealer busted and got a total of " + dealer.getHandSum() + ". " + me.getNickName() + " win this time!");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Dealer has chosen to stay!");
                    int totalDealerSum = dealer.getHandSum();
                    int totalPlayerSum = me.getHandSum();

                    if (totalDealerSum > totalPlayerSum) {
                        System.out.println("Both players has decided to stay. The winner is " + dealer.getNickName() + " with a total of " + totalDealerSum + ".");
                    } else {
                        System.out.println("Both players has decided to stay. The winner is " + me.getNickName() + " with a total of " + totalPlayerSum + ".");
                        userBalance += blackjackBux * 1.5;
                    }
                    stay = false;
                }

            } while (stay);
        }
    }
}
