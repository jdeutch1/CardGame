/**
 * Jackson Deutch
 * Card Game Project
 * December 7
 * Block F, APCSA
 */

public class Card
{
    /** Instance Variables **/
    private String rank;
    private String suit;
    private int point;

    public Card(String rank, String suit, int point)
    {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
    }

    // Returns rank of card
    public String getRank()
    {
        return rank;
    }

    // Sets rank of card
    public void setRank(String rank)
    {
        this.rank = rank;
    }

    // Returns suit of card
    public String getSuit()
    {
        return suit;
    }

    // Sets suit of card
    public void setSuit(String suit)
    {
        this.suit = suit;
    }

    // Returns the point value of the card
    public int getPoint()
    {
        return point;
    }

    // Sets the point value of card to new value
    public void setPoint(int point)
    {
        this.point = point;
    }

    // Prints the card
    public String toString()
    {
        return rank + " of " + suit;
    }
}
