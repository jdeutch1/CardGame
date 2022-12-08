/**
 * Jackson Deutch
 * Card Game Project
 * December 7
 * Block F, APCSA
 */

import java.util.ArrayList;

public class Player
{
    /** Instance Variables **/
    private ArrayList<Card> hand;
    private String name;
    private int points;
    private int numCards;
    public Player(String name, int points)
    {
        this.name = name;
        this.points = points;
    }

    public Player(String name, ArrayList<Card> hand)
    {
        this.name = name;
        this.hand = hand;
        points = 0;
    }
    public Player(String name, ArrayList<Card> hand, int numCards)
    {
        this.name = name;
        this.hand = hand;
        this.numCards = numCards;
        points = 0;
    }

    // Returns player name
    public String getName()
    {
        return name;
    }

    // Sets player name to new String
    public void setName(String name)
    {
        this.name = name;
    }

    // Returns number of cards in hand
    public int getNumCards() {
        return numCards;
    }

    // Sets points to new value
    public void setPoints(int points) {
        this.points = points;
    }

    // Returns points
    public int getPoints()
    {
        return points;
    }

    public void addPoints(int addedPoints)
    {
        points += addedPoints;
    }

    // Adds 2 cards to hand, and increments numCards accordingly
    public void addCards(Card card1, Card card2)
    {

        hand.add(0, card1);
        hand.add(1, card2);
        numCards +=2;
    }

    // Returns card at highest index of hand
    // Prints the player name and the card that has been played
    public Card playCard()
    {
        numCards--;
        Card removedCard = hand.remove(numCards);
        System.out.println(name + " --> " + removedCard);
        return removedCard;
    }

    // Modified toString method for War game
    public String toString()
    {
        return name + " has " + numCards + " cards in their hand.";
    }
}
