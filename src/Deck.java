/**
 * Jackson Deutch
 * Card Game Project
 * December 7
 * Block F, APCSA
 */

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    /** Instance Variables **/
    private ArrayList<Card> deck;
    private int cardsLeft;
    public Deck(String ranks[], String suits[], int values[])
    {
        // Construct the deck
        // Each rank corresponds to a value, and each rank has its own suit
        deck = new ArrayList<>();
        for(int i = 0; i < ranks.length; i++)
        {
            for(int j = 0; j < suits.length; j++)
            {
                    deck.add(new Card(ranks[i], suits[j], values[i]));

            }
        }
        cardsLeft = deck.size();
    }

    // Returns true if deck is empty
    public boolean isEmpty()
    {
        return cardsLeft == 0;
    }

    // Returns the number of cards left in the deck
    public int getCardsLeft()
    {
        return cardsLeft;
    }

    // Returns card at index cardsLeft and decrements cardsLeft
    public Card deal()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            cardsLeft--;
            return deck.get(cardsLeft);
        }
    }

    // Shuffles the deck
    public void shuffle()
    {
        cardsLeft = deck.size();
        /* Picks random int between i and cardsLeft, then swaps
        the selected card with the randomly selected index*/
        for (int i = cardsLeft - 1; i > -1; i--)
        {
            int r = (int)(Math.random() * (i+1));
            Collections.swap(deck, r, i);
        }
    }
}
