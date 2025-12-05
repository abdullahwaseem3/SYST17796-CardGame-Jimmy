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
public class Deck extends GroupOfCards {
    
    public Deck() {
        super(52);
        initializeDeck();
    }
    
    private void initializeDeck() {
        // create all 52 cards -> 4 suits X 13 ranks = 52 cards
        for (StandardCard.Suit suit : StandardCard.Suit.values()) {
            for (StandardCard.Rank rank : StandardCard.Rank.values()) {
                getCards().add(new StandardCard(suit, rank));
            }
        }
    }
    
    @Override
    public void shuffle() {
        super.shuffle();
    }
    
        /**
     * Draws a card from the top of the deck
     * @return the top card from the deck, or null if deck is empty
     */
    public Card drawCard() {
        if (isEmpty()) {
            return null;
        }
        // Remove and return the card from the deck
        return getCards().remove(0);
    }
    
    /**
     * Checks if the deck is empty
     * @return true if no cards remain, false otherwise
     */
    public boolean isEmpty() {
        return getCards().isEmpty();
    }
    
    /**
     * Gets the number of cards remaining in the deck
     * @return the number of cards left
     */
    public int getRemainingCards() {
        return getCards().size();
    }

}
