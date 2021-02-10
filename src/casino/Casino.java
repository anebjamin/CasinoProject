package casino;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.Random;
import java.util.ArrayList;

public class Casino {

    public static Scanner input = new Scanner(System.in);
    public static Random rand = new Random();
    //public static FileOutputStream fout=new FileOutputStream(System.getProperty("user.dir")+"\\users.txt");

    public static void main(String[] args) throws FileNotFoundException {
        int userBalance = 1000;
        //logInMenu();
        //gamesMenu(userBalance);
        //textColour();
        signUp();
        //roulette(userBalance);
    }

    public static void logInMenu() throws FileNotFoundException {
        System.out.println("Please enter what you would like to do\n  1) sign up\n  2) log in\n  3) exit\n");
        int menuSelect = input.nextInt();
        switch (menuSelect) {
            case 1:
                signUp();
                break;
        }
    }

    public static void gamesMenu(int userBalance) {
        userBalance = userBalance;
        System.out.println("what game would you like to play?\n 1: roulette\n 2: blackjack\n 3: higher or lower\n 4: exit");
        int menuSelect = input.nextInt();
        switch (menuSelect) {
            case 1:
                roulette(userBalance);
                break;
            case 2:
                //blackjack();
                break;
            case 3:
                //highOrLow();
                break;
            case 4:
                break;

        }
    }

    public static void signUp() throws FileNotFoundException {
        String folderDirectory = System.getProperty("user.dir") + "\\users.txt";
        File file = new File(folderDirectory);
        ArrayList<userBuild> masterList = new ArrayList<>();
        Scanner scanFile = new Scanner(file);
        int count =0;
        while (scanFile.hasNextLine()) {
            String userID = scanFile.next();
            String userName = scanFile.next();
            String userPW = scanFile.next();
            String userBal = scanFile.next();
            userBuild newUser = new userBuild(userID, userName, userPW, userBal);
            masterList.add(newUser);
            count++;
        }
        String lastUser=(masterList.get(count-1)).toString();
        int NewUserID=Integer.parseInt(lastUser.substring(0,4));
        int ser=NewUserID+1;
        System.out.println("enter username");
        String userName=input.next();
        System.out.println("enter password");
        String userPW=input.next();
        int userBalanc = 1000;
        String userID=Integer.toString(ser);
        String userBal=Integer.toString(userBalanc);
        userBuild newUser=new userBuild(userID, userName, userPW, userBal);
        masterList.add(newUser);
    }

    private static void roulette(int userBalance) {

        int rouletteBux;
        System.out.println("select your number (odds are black, evens are red, 0 is green)");
        System.out.print("\u001b[32m"+"0" + "\u001B[30m"+", ");
        for (int i = 1; i < 37; i++) {
            System.out.print(i+", " );
            i++;
            System.out.print("\u001b[31m"+i + "\u001B[30m"+", ");
            
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

    private static void blackjack(int userBalance) {
        boolean stay = false;
        Player me = new Player("you");
        Player dealer = new Player("Dealer");

        System.out.println("Would you like to start a new game?  'Yes/No' :");
        String pAnswer = input.next();

        if (pAnswer.equalsIgnoreCase("Yes")) {
            System.out.println("how much money would you like to bet?(max 100)");
            int blackjackBux=input.nextInt();
            userBalance-=blackjackBux;
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
                System.out.println("Would " + me.getNickName() + " like to bust or stay? 'Bust/Stay'");
                pAnswer = input.next();
                if (pAnswer.equalsIgnoreCase("Bust")) {
                    me.addCard(deck1.dealNextCard());
                    System.out.println(me.getHandSum());
                    if (me.getHandSum() > 21) {
                        System.out.println("You busted and got a total of " + me.getHandSum() + ". Dealer wins this time! ");
                        System.exit(0);
                    }
                }
                if (pAnswer.equalsIgnoreCase("stay")) {
                    System.out.println("You have chosen to stay. Your hand: " + me.getHandSum());
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
                        userBalance+=blackjackBux*1.5;
                    }
                    stay = false;
                }

            } while (stay);
        }
    }


private static void textColour() {
        String BLACK = "\033[0;30m";   // BLACK
        String RED = "\033[0;31m";     // RED
        String GREEN = "\033[0;32m";   // GREEN
        System.out.println("hello GREEN");
    }

}
