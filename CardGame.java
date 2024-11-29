//Pablo Mendoza 
//CPSC-39
//11/29/2024

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;


public class CardGame {

    public static void main(String[] args) {
        LinkedList<Card> mainDeck =  new LinkedList<>();
        makeLinkedList(mainDeck);



    }//end main()

    public static void blackJack(LinkedList<Card> mainDeck){
        
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

        // initialize deck (modified to create Card class objects, and to then add them to LinkedList)
        int n = SUITS.length * RANKS.length;
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                Card card = new Card(i + 1, SUITS[j], RANKS[i]);
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

        /* //prints shuffled deck (modified to work on LinkedList)
        for (int i = 0; i < n; i++) {
            System.out.println(mainDeck.get(i));
        }
        */

	}//end makeArray()

}//end class
