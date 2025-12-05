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
public class GoFishPlayer extends Player {
    private Hand hand;
    private ArrayList<Book> books;
    private int booksCount;
    
    /**
     * Constructor to create a Go Fish player
     * @param name the name of the player
     */
    public GoFishPlayer(String name) {
        super(name);
        this.hand = new Hand();
        this.books = new ArrayList<>();
        this.booksCount = 0;
    }
    
    /**
     * Get the player's hand
     * @return the Hand object
     */
    public Hand getHand() {
        return hand;
    }
    
    /**
     * Get the list of books this player has completed
     * @return ArrayList of Book objects
     */
    public ArrayList<Book> getBooks() {
        return books;
    }
    
    /**
     * Get the number of books this player has completed
     * @return the count of books
     */
    public int getBooksCount() {
        return booksCount;
    }
    
    /**
     * Adds a completed book to this player's collection
     * @param book the Book to add
     */
    public void addBook(Book book) {
        books.add(book);
        booksCount++;
    }
    
    /**
     * Increments the book count when a book is completed
     */
    public void incrementBooks() {
        booksCount++;
    }
    
    /**
     * Asks another player for cards of a specific rank
     * @param opponent the player being asked
     * @param rank the rank being requested
     * @return ArrayList of cards received
     */
    public ArrayList<Card> askForCard(GoFishPlayer opponent, StandardCard.Rank rank) {
        // Get all cards of the requested rank from opponent
        ArrayList<Card> receivedCards = opponent.getHand().removeCardsByRank(rank);
        
        // Add them to this player's hand
        for (Card card : receivedCards) {
            hand.addCard(card);
        }
        
        return receivedCards;
    }
    
    /**
     * Draws a card from the deck (when told to "Go Fish")
     * @param deck the deck to draw from
     * @return the card drawn, or null if deck is empty
     */
    public Card goFish(Deck deck) {
        Card drawnCard = deck.drawCard();
        if (drawnCard != null) {
            hand.addCard(drawnCard);
        }
        return drawnCard;
    }
    
    /**
     * Checks the player's hand for any complete books and removes them
     */
    public void checkAndFormBooks() {
        ArrayList<Book> newBooks = hand.checkForBooks();
        
        for (Book book : newBooks) {
            // Remove the 4 cards from hand
            hand.removeCardsByRank(book.getRank());
            // Add book to player's collection
            addBook(new Book(book.getRank(), book.getCards(), getName()));
            System.out.println(getName() + " completed a BOOK of " + book.getRank() + "s! (Books: " + booksCount + ")");
        }

    }
    
    /**
     * The player's turn logic
     */
    @Override
    public void play() {
        // The main game logic handles this
    }
    
    /**
     * Displays the current status of the player
     * @return String showing player name, cards in hand, and books completed
     */
    public String showStatus() {
        String status = getName() + " - Cards in hand: " + hand.getSize() + " | Books: " + booksCount;
        return status;
    }
}
