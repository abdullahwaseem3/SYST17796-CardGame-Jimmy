/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author muhammadarham,muhammadabdullahwaseem
 * Date: Dec 04, 2025
 */
public class StandardCard extends Card{
    
    // Enumeration representing the suit of a playing card
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    
    // Enumeration representing the rank of a playing card
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, 
        EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    
    private final Suit suit;
    private final Rank rank;
    
    public StandardCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    @Override
    public String toString() {
        // returning rank
        return rank + " of " + suit;
    }
    
    
}
