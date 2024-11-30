//Pablo Mendoza 
//CPSC-39
//11/29/2024

import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;


public class CardGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Card> mainDeck =  new LinkedList<>(); //initialize LinkedList
        makeLinkedList(mainDeck); //Create randomized LinkedList of Card objects

        String str = ""; 
        //simple while loop that continues until user chooses an option
        while(!str.equalsIgnoreCase("no")) {
            System.out.println("Would you like to play Blackjack? (Yes/No)");
            str = scanner.nextLine(); 
            if(str.equalsIgnoreCase("yes")){
                blackJack(mainDeck, scanner);
                break;
            }else if(!str.equalsIgnoreCase("no")){
                System.out.println("Invalid Response, please try again");
            }
        }



    }//end main()

    public static void blackJack(LinkedList<Card> mainDeck, Scanner scanner){
        LinkedList<Card> dealerHand = new LinkedList<>(), userHand = new LinkedList<>();
        for (int i = 0; i < 2; i++) { //creates hands for both dealer and player. Follows rules where both pull one at a time
            dealerHand.add(mainDeck.get(0));
            mainDeck.remove(0);
            userHand.add(mainDeck.get(0));
            mainDeck.remove(0);
        }

        printDecks(userHand, dealerHand); //prints user's and dealer's hand
        int dealerValue = handValue(dealerHand), userValue = handValue(userHand);// calculates each hands value

        while(true){ //simulates user's turn. Will keep running until player stands or busts
            if (userValue > 21) {
                System.out.println("Player busts! Dealer wins.");
                return;
            }

            System.out.println("Would you like to hit or stand? (Hit/Stand)");
            String str = scanner.nextLine(); 

            if(str.equalsIgnoreCase("hit")){
                userHand.add(mainDeck.get(0));
                mainDeck.remove(0);
                printDecks(userHand, dealerHand);
                userValue = handValue(userHand);
            }else if(str.equalsIgnoreCase("stand")){
                System.out.println("Your deck is worth " + userValue + ", dealers is " + dealerValue);
                break;
            }else{
                System.out.println("Invalid Response, please try again");
            }
        }

        while(dealerValue < 17) {//dealer's turn. goes until any of the four conditions are met
            System.out.print("Dealer Drew - " + (mainDeck.get(0)).rankAndSuit());
            dealerHand.add(mainDeck.get(0));
            mainDeck.remove(0);
            dealerValue = handValue(dealerHand);
            System.out.println(", their hand is now worth " + dealerValue);

            if (dealerValue > 21) {
                System.out.println("Dealer busts! Player wins!");
                return;
            }else if(dealerValue == userValue){
                System.out.println("Its a tie!");
                return;
            }else if(dealerValue > 17 && dealerValue < userValue){
                System.out.println("Player wins!");
                return;
            }else if(dealerValue > userValue){
                System.out.println("Dealer wins!");
                return;
            }
        }
        System.out.println("Player wins!"); 
        //if no condition was met, then we assume dealer went over 17, but was not higher than user's value
    }//end blackJack()

    //prints out player and dealer hands
    public static void printDecks(LinkedList<Card> userHand, LinkedList<Card> dealerHand){
        System.out.println("Your Hand:");
        for(int i = 0; i < userHand.size(); i++){
            System.out.println((userHand.get(i)).rankAndSuit());
        }
        System.out.println("Dealer Hand:");
        for(int i = 0; i < dealerHand.size(); i++){
            System.out.println((dealerHand.get(i)).rankAndSuit());
        };
    }

    public static int handValue(LinkedList<Card> hand){
        int value = 0;
        boolean aceInPlay = false;
        for(int i = 0; i < hand.size(); i++){ //iterates through the LinkedList Card objects to obtain total value
            value += (hand.get(i)).getValue();
            if((hand.get(i)).getValue() == 11){
                aceInPlay = true;
            }
        }
        if(aceInPlay && value > 21){ //if ace would make the hand bust, then its value is reduced to 1
            value -= 10;
        }
        return value;
    }

    //princeton code
    public static void makeLinkedList(LinkedList<Card> mainDeck){

        //array of suits
        String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"
        };

        //array of ranks
        String[] RANKS = {
            "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King"
        };

        int[] values = {
            11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10
        };

        // initialize deck (modified to create Card class objects, and to then add them to LinkedList)
        int n = SUITS.length * RANKS.length;
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                Card card = new Card(values[i], SUITS[j], RANKS[i]);
                mainDeck.add(card);
            }
        }

        // shuffle (modified to shuffle the LinkedList)
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n-i));
            Card temp = mainDeck.get(r);
            mainDeck.set(r, mainDeck.get(i));
            mainDeck.set(i, temp);
        }

        /*//prints shuffled deck (modified to work on LinkedList)
        for (int i = 0; i < n; i++) {
            System.out.println(mainDeck.get(i));
        }
        */

	}//end makeArray()

}//end class