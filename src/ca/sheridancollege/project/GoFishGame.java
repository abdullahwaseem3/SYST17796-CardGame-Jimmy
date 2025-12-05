/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author muhammadarham
 */
public class GoFishGame extends Game {
    private Deck deck;
    private int currentPlayerIndex;
    private boolean gameOver;
    private Scanner scanner;
    
    /**
     * Constructor to create a Go Fish game
     * @param name the name of the game
     */
    public GoFishGame(String name) {
        super(name);
        this.deck = new Deck();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Initializes the game by setting up the deck and players
     */
    public void initializeGame() {
        System.out.println("===============================");
        System.out.println("Welcome to " + getName() + "!");
        System.out.println("===============================");
        
        // Get number of players
        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.print("Enter number of players (2-4): ");
            try {
                numPlayers = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (numPlayers < 2 || numPlayers > 4) {
                    System.out.println("Please select players between 2 AND 4!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
        
        // Get player names
        ArrayList<Player> playerList = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            while (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty!");
                System.out.print("Enter name for Player " + i + ": ");
                name = scanner.nextLine();
            }
            playerList.add(new GoFishPlayer(name));
        }
        setPlayers(playerList);
        
        System.out.println("\nAll players registered!");
        System.out.println("Shuffling deck...\n");
        deck.shuffle();
    }
    
    /**
     * Deals initial cards to all players
     */
    public void dealInitialCards() {
        int cardsPerPlayer;
        
        // identify cards per player based on number of players
        if (getPlayers().size() == 4) {
            cardsPerPlayer = 5;
        } else if (getPlayers().size() == 3) {
            cardsPerPlayer = 6;
        } else {
            cardsPerPlayer = 7;
        }
        
        // Deal cards to each player
        for (Player p : getPlayers()) {
            GoFishPlayer player = (GoFishPlayer) p;
            for (int i = 0; i < cardsPerPlayer; i++) {
                Card card = deck.drawCard();
                player.getHand().addCard(card);
            }
        }
        
        System.out.println("Cards distributed! Each player has " + cardsPerPlayer + " cards.");
        System.out.println("Remaining cards in deck: " + deck.getRemainingCards());
    }
    
    /**
     * Main game loop
     */
    @Override
    public void play() {
        System.out.println("\nStarting Go Fish game!");
        System.out.println("============================");
        
        while (!isGameOver()) {
            GoFishPlayer currentPlayer = getCurrentPlayer();
            playTurn(currentPlayer);
            
            // Check if game is over
            if (!isGameOver()) {
                nextPlayer();
            }
        }
        
        // Game ended
        declareWinner();
    }
    
    /**
     * Handles a single player's turn
     * @param player the player whose turn it is
     */
    public void playTurn(GoFishPlayer player) {
        System.out.println("\n-------------------------------------");
        System.out.println(player.getName() + "'s turn");
        System.out.println("-------------------------------------");
        System.out.println("Your hand: " + player.getHand().showHand());
        System.out.println("Your books: " + player.getBooksCount());
        
        // Check if player has any cards
        if (player.getHand().isEmpty()) {
            System.out.println("Your hand is empty. Drawing a card...");
            Card drawnCard = player.goFish(deck);
            if (drawnCard != null) {
                System.out.println("You drew: " + drawnCard);
            } else {
                System.out.println("Deck is empty!");
            }
            player.checkAndFormBooks();
            return;
        }
        
        // Select opponent
        GoFishPlayer opponent = selectOpponent(player);
        if (opponent == null) {
            return; // No valid opponents
        }
        
        // Select rank to ask for
        StandardCard.Rank requestedRank = selectRank(player);
        if (requestedRank == null) {
            return; // Invalid rank
        }
        
        System.out.println(player.getName() + " asks " + opponent.getName() + ": Do you have any " + requestedRank + "s?");
        
        // Ask for cards
        ArrayList<Card> receivedCards = player.askForCard(opponent, requestedRank);
        
        if (!receivedCards.isEmpty()) {
            System.out.println("Yes! " + opponent.getName() + " gave you " + receivedCards.size() + " cards.");
            player.checkAndFormBooks();
            
            // Player continues turn
            System.out.println(player.getName() + " gets one more turn!");
            playTurn(player);
        } else {
            System.out.println(opponent.getName() + " says: Go Fish!");
            Card drawnCard = player.goFish(deck);
            
            if (drawnCard != null) {
                System.out.println("You drew: " + drawnCard);
                player.checkAndFormBooks();
            } else {
                System.out.println("Deck is empty!");
            }
        }
    }
    
    private GoFishPlayer selectOpponent(GoFishPlayer currentPlayer) {
        // Show list of opponents
        System.out.println("\nSelect an opponent:");
        ArrayList<GoFishPlayer> opponents = new ArrayList<>();
        int index = 1;
        
        for (Player p : getPlayers()) {
            if (p == currentPlayer) {
                continue;
            }
    
            GoFishPlayer opp = (GoFishPlayer) p;
            System.out.println(index + ". " + opp.getName() + " (Cards: " + opp.getHand().getSize() + ", Books: " + opp.getBooksCount() + ")");
            opponents.add(opp);
            index++;
        }
        
        // If only one opponent
        if (opponents.size() == 1) {
            GoFishPlayer selected = opponents.get(0);
            System.out.println("Automatically selected: " + selected.getName());
            
            return selected;
        }
        
        // If multiple opponents
        int choice = 0;
        while (choice < 1 || choice > opponents.size()) {
            System.out.print("Enter number between (1-" + opponents.size() + "): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > opponents.size()) {
                    System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        
        return opponents.get(choice - 1);
    }
    
    private StandardCard.Rank selectRank(GoFishPlayer player) {
        System.out.println("\nAvailable ranks in your hand:");
        ArrayList<StandardCard.Rank> availableRanks = new ArrayList<>();
        
        for (Card card : player.getHand().getCards()) {
            StandardCard stdCard = (StandardCard) card;
            if (!availableRanks.contains(stdCard.getRank())) {
                availableRanks.add(stdCard.getRank());
            }
        }
        
        for (int i = 0; i < availableRanks.size(); i++) {
            System.out.println((i + 1) + ". " + availableRanks.get(i));
        }
        
        int choice = 0;
        while (choice < 1 || choice > availableRanks.size()) {
            System.out.print("Select rank between (1-" + availableRanks.size() + "): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > availableRanks.size()) {
                    System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        
        return availableRanks.get(choice - 1);
    }
    
    /**
     * Checks if the game is over
     * @return true if game is over
     */
    public boolean isGameOver() {
        // Check if all 13 books have been formed
        int totalBooks = 0;
        for (Player p : getPlayers()) {
            GoFishPlayer player = (GoFishPlayer) p;
            totalBooks += player.getBooksCount();
        }
        
        if (totalBooks == 13) {
            return true;
        }
        
        // Check if deck is empty and no player can make moves
        if (deck.isEmpty()) {
            boolean anyPlayerHasCards = false;
            for (Player p : getPlayers()) {
                GoFishPlayer player = (GoFishPlayer) p;
                if (!player.getHand().isEmpty()) {
                    anyPlayerHasCards = true;
                    break;
                }
            }
            if (!anyPlayerHasCards) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Declares the winner(s) of the game
     */
    @Override
    public void declareWinner() {
        System.out.println("\n===============================");
        System.out.println("GAME OVER!");
        System.out.println("===============================");
        
        // Display final scores
        System.out.println("\n======= Final Scores =======");
        int maxBooks = 0;
        ArrayList<GoFishPlayer> winners = new ArrayList<>();
        
        for (Player p : getPlayers()) {
            GoFishPlayer player = (GoFishPlayer) p;
            System.out.println(player.getName() + ": " + player.getBooksCount() + " books");
            
            if (player.getBooksCount() > maxBooks) {
                maxBooks = player.getBooksCount();
                winners.clear();
                winners.add(player);
            } else if (player.getBooksCount() == maxBooks) {
                winners.add(player);
            }
        }
        
        // Declare winner(s)
        System.out.println("\n----------------------------");
        if (winners.size() == 1) {
            System.out.println("Congratulations " + winners.get(0).getName() + "!");
            System.out.println("You won with " + maxBooks + " books!");
        } else {
            System.out.print("Game is tie between: ");
            for (int i = 0; i < winners.size(); i++) {
                System.out.print(winners.get(i).getName());
                if (i < winners.size() - 1) {
                    System.out.print(" and ");
                }
            }
            System.out.println(" with " + maxBooks + " books each!");
        }
        System.out.println("\n----------------------------");

    }
    
    /**
     * Gets the player whose turn it currently is
     * @return the current GoFishPlayer
     */
    public GoFishPlayer getCurrentPlayer() {
        return (GoFishPlayer) getPlayers().get(currentPlayerIndex);
    }
    
    /**
     * Moves to the next player's turn
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % getPlayers().size();
    }
    
    /**
     * Displays the current game status
     */
    public void displayGameStatus() {
        System.out.println("\n--- Game Status ---");
        for (Player p : getPlayers()) {
            GoFishPlayer player = (GoFishPlayer) p;
            System.out.println(player.showStatus());
        }
        System.out.println("Cards remaining in deck: " + deck.getRemainingCards());
    }
}
