/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author muhammadarham,muhammadabdullahwaseem
 * Date: Dec 04, 2025
 */
public class Hand extends GroupOfCards {
    
    /**
     * Constructor creates an empty hand
     */
    public Hand() {
        super(0); // Initialize cards list
    }
    
        /**
     * Adds a card to the hand
     * @param card the card to add
     */
    public void addCard(Card card) {
        if (card != null) {
            getCards().add(card);
        }
    }
    
    /**
     * Removes a specific card from the hand
     * @param card the card to remove
     */
    public void removeCard(Card card) {
        getCards().remove(card);
    }
    
    /**
     * Removes all cards of a specific rank from the hand
     * @param rank the rank of cards to remove
     * @return ArrayList of removed cards
     */
    public ArrayList<Card> removeCardsByRank(StandardCard.Rank rank) {
        ArrayList<Card> removedCards = new ArrayList<>();
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        
        // Find all cards with matching rank
        for (Card card : getCards()) {
            if (card instanceof StandardCard) {
                StandardCard stdCard = (StandardCard) card;
                if (stdCard.getRank() == rank) {
                    cardsToRemove.add(card);
                }
            }
        }
        
        // Remove them and add to the return list
        for (Card card : cardsToRemove) {
            getCards().remove(card);
            removedCards.add(card);
        }
        
        return removedCards;
    }
    
    /**
     * Checks if the hand contains any card of the specified rank
     * @param rank the rank to check for
     * @return true if hand has at least one card of that rank
     */
    public boolean hasRank(StandardCard.Rank rank) {
        for (Card card : getCards()) {
            if (card instanceof StandardCard) {
                StandardCard stdCard = (StandardCard) card;
                if (stdCard.getRank() == rank) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Gets all cards of a specific rank from the hand
     * @param rank the rank to get
     * @return ArrayList of cards with matching rank
     */
    public ArrayList<Card> getCardsByRank(StandardCard.Rank rank) {
        ArrayList<Card> matchingCards = new ArrayList<>();
        for (Card card : getCards()) {
            if (card instanceof StandardCard) {
                StandardCard stdCard = (StandardCard) card;
                if (stdCard.getRank() == rank) {
                    matchingCards.add(card);
                }
            }
        }
        return matchingCards;
    }
    
    /**
     * Checks for complete books (4 cards of same rank) in the hand
     * @return ArrayList of Book objects for any complete books found
     */
    public ArrayList<Book> checkForBooks() {
        ArrayList<Book> completedBooks = new ArrayList<>();
        
        // Check each rank to see if we have 4 cards
        for (StandardCard.Rank rank : StandardCard.Rank.values()) {
            ArrayList<Card> cardsOfRank = getCardsByRank(rank);
            if (cardsOfRank.size() == 4) {
                // We have a book!
                completedBooks.add(new Book(rank, cardsOfRank, ""));
            }
        }
        
        return completedBooks;
    }
    
    /**
     * Checks if the hand is empty
     * @return true if no cards in hand
     */
    public boolean isEmpty() {
        return getCards().isEmpty();
    }
    
    /**
     * Gets the current size of the hand
     * @return number of cards in hand
     */
    @Override
    public int getSize() {
        return getCards().size();
    }
    
    /**
     * Returns a string showing all cards in the hand
     * @return String representation of the hand
     */
    public String showHand() {
        if (isEmpty()) {
            return "Empty hand";
        }
        
        StringBuilder handString = new StringBuilder();
        for (int i = 0; i < getCards().size(); i++) {
            handString.append(getCards().get(i).toString());
            if (i < getCards().size() - 1) {
                handString.append(", ");
            }
        }
        return handString.toString();
    }
    
}
