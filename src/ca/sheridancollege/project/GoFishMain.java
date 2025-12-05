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
public class GoFishMain {
    
    public static void main(String[] args) {
        GoFishGame game = new GoFishGame("Go Fish");
        game.initializeGame();
        game.dealInitialCards();
        game.play();
    }
}
