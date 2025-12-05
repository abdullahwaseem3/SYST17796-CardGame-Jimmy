/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author muhammadarham,muhammadabdullahwaseem
 *  Date: Dec 04, 2025
 */
public class Book {
    private final StandardCard.Rank rank;
    private final ArrayList<Card> cards;
    private final String completedBy;
    
    /**
     * Constructor to create a book
     * @param rank the rank of the cards in this book
     * @param cards the 4 cards that make up this book
     * @param playerName the name of the player who completed this book
     */
    public Book(StandardCard.Rank rank, ArrayList<Card> cards, String playerName) {
        this.rank = rank;
        this.cards = cards;
        this.completedBy = playerName;
    }
    
    /**
     * Get the rank of this book
     * @return the rank
     */
    public StandardCard.Rank getRank() {
        return rank;
    }
    
    /**
     * Get the cards in this book
     * @return ArrayList of 4 cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    /**
     * Get the name of the player who completed this book
     * @return the player's name
     */
    public String getCompletedBy() {
        return completedBy;
    }
    
    /**
     * Checks if this book is complete (has exactly 4 cards)
     * @return true if book has 4 cards
     */
    public boolean isComplete() {
        return cards.size() == 4;
    }
    
    /**
     * Returns a string representation of the book
     * @return String describing the book
     */
    @Override
    public String toString() {
        return "Book of " + rank + "s (4 cards)";
    }
}
