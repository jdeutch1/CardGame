/**
 * Jackson Deutch
 * Card Game Project
 * December 7
 * Block F, APCSA
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    /** Instance Variables **/
    private static final int maxNumTurns = 75;
    private Player p1;
    private ArrayList<Card> hand1;
    private Player p2;
    private ArrayList<Card> hand2;
    private boolean isAdvantageForP1;
    private int turns;
    private static String ranks[] = {"2", "3", "4", "5",
            "6", "7", "8", "9",
            "10", "J", "Q", "K", "A"};
    private static String suits[] = {"Hearts", "Clubs",
            "Spades", "Diamonds"};
    private static int values[] = {1, 2, 3, 4, 5, 6, 7,
            8, 9, 10, 11, 12, 13};
    private Deck gameDeck;
    public static void main(String[] args)
    {
        // Get username
        Scanner s = new Scanner(System.in);
        System.out.println("Type user name below");
        String name = s.nextLine();

        // Create game object and play
        Game g = new Game(name, ranks, suits, values);
        g.playGame();

    }
    public Game(String userName, String ranks[],
                String suits[], int values[])
    {
        gameDeck = new Deck(ranks, suits, values);
        gameDeck.shuffle();
        hand1 = new ArrayList<Card>();
        hand2 = new ArrayList<Card>();
        while(!gameDeck.isEmpty())
        {
            hand1.add(gameDeck.deal());
            hand2.add(gameDeck.deal());
        }
        p1 = new Player(userName, hand1, 26);
        p2 = new Player("CPU", hand2, 26);

        isAdvantageForP1 = true;
        turns = 0;
    }

    public void playGame()
    {
        Scanner s = new Scanner(System.in);

        // Print instructions
        printInstructions();

        //Loop rounds until someone wins or turn limit reached
        while (!checkWin() && !maxTurnsReached())
        {
            /* Prints how many cards
            each player has and who has tiebreak advantage */
            System.out.println("\n" + p1 + "\n" + p2);
            if (isAdvantageForP1)
            {
                System.out.println("Advantage is for " + p1.getName());
            }
            else {
                System.out.println("Advantage is for CPU");
            }

            // Get user input to play card
            do {
                System.out.println("\nType 'p' to place your card");
            } while (!s.nextLine().equals("p"));

            System.out.println("");

            // Get each played card
            Card p1PlayedCard = p1.playCard();
            Card p2PlayedCard = p2.playCard();
            addTurn();

            /* Get value of cards and move both cards
            to user whose played card had higher value*/
            if (p1PlayedCard.getPoint() > p2PlayedCard.getPoint())
            {
                p1.addCards(p1PlayedCard, p2PlayedCard);
            }
            else if (p1PlayedCard.getPoint() < p2PlayedCard.getPoint())
            {
                p2.addCards(p1PlayedCard, p2PlayedCard);
            }
            /* If war, take war cards and determine which player
            wins using same logic as above*/
            else
            {
                System.out.println("\nWAR!!\n");
                Card p1WarCard = p1.playCard();
                Card p2WarCard = p2.playCard();
                if (p1WarCard.getPoint() > p2WarCard.getPoint())
                {
                    System.out.println("\n" + p1.getName() + " wins the war!" +
                            " They get all cards below!\n");
                    // The winner of war gets all cards played, and 3 more cards from user
                    p1.addCards(p1PlayedCard, p2PlayedCard);
                    p1.addCards(p1WarCard, p2WarCard);
                    for (int i = 0; i < 3; i++){
                        p1.addCards(p1.playCard(), p2.playCard());
                    }
                }
                else if (p2WarCard.getPoint() > p1WarCard.getPoint()){
                    {
                        System.out.println("\nCPU wins the war!" +
                                " They get all cards below!\n");
                        p2.addCards(p1PlayedCard, p2PlayedCard);
                        p2.addCards(p1WarCard, p2WarCard);
                        for (int i = 0; i < 3; i++){
                            p2.addCards(p1.playCard(), p2.playCard());
                        }
                    }
                }
                // If there is another war, the advantage mechanism is used
                else {
                    System.out.println("ANOTHER WAR!!!???");
                    if (isAdvantageForP1)
                    {
                        System.out.print(p1.getName() + " wins the big sha-bang!");
                        p1.addCards(p1PlayedCard, p2PlayedCard);
                        p1.addCards(p1WarCard, p2WarCard);
                        for (int i = 0; i < 3; i++){
                            p1.addCards(p1.playCard(), p2.playCard());
                        }
                    }
                    else
                    {
                        System.out.print("CPU wins the big sha-bang!");
                        p2.addCards(p1PlayedCard, p2PlayedCard);
                        p2.addCards(p1WarCard, p2WarCard);
                        for (int i = 0; i < 3; i++){
                            p2.addCards(p1.playCard(), p2.playCard());
                        }
                    }
                    isAdvantageForP1 = !isAdvantageForP1;
                }
            }
        }
        /* Checks to see whether game has been ended
        because of a win or maximum turns reached */
        if (checkWin())
        {
            System.out.println("\nGame Over!");
        }
        else
        {
            System.out.println("\nGame Over! Maximum number of turns reached.");
        }
        // Winner of game is identified by having more cards than opponent
        if (p1.getNumCards() > p2.getNumCards())
        {
            System.out.println("Congratulations " + p1.getName());
        }
        else if (p1.getNumCards() < p2.getNumCards())
        {
            System.out.println("\nCongratulations " + p2.getName());
        }
        else
        {
            System.out.println("IT'S A TIE!");
        }
        System.out.println("FINAL SCORE\n" + p1.getName() + ": "
                + p1.getNumCards() + "\nCPU: " + p2.getNumCards());
    }

    // Increments turns
    public void addTurn()
    {
        turns++;
        System.out.println("Turns taken: " + turns);
    }

    // Returns true if the max turns has been reached
    public boolean maxTurnsReached()
    {
        return turns > maxNumTurns - 1;
    }

    // Checks if one of the players has won the game
    // Returns true if one player has won all the cards
    public boolean checkWin()
    {
        return hand1.size() == 52 || hand2.size() == 52;
    }

    // Prints instructions of the game
    public static void printInstructions()
    {
        System.out.println("\nWelcome to War!");
        System.out.println("The goal of the game is simple:" +
                " win all of your opponent's cards");
        System.out.println("The higher card wins! Ace is the highest," +
                        " 2 is the lowest.");
        System.out.println("If you and your opponent play the same rank, it is WAR!");
        System.out.println("If you get back-to-back wars, the player with the" +
                        " 'advantage' auto-wins.");
        System.out.println("If there is no winner by " + maxNumTurns + " turns, the winner" +
                        " is the one with the most cards.");
        System.out.println("Good luck.");
    }
}
