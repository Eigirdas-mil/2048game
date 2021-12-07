package com.example.kursinis2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Game
{
    public static void main (String[] args)
    {
        Board game = new Board();
        game.printBoard();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = bufferRead.readLine();
            while (input != null){
                boolean movePossible = false;
                if(input.equalsIgnoreCase("left")){
                    movePossible = true;
                    game.moveLeft();
                }else if(input.equalsIgnoreCase("right")){
                    movePossible = true;
                    game.moveRight();
                }else if(input.equalsIgnoreCase("up")){
                    movePossible = true;
                    game.moveUp();
                }else if(input.equalsIgnoreCase("down")){
                    movePossible = true;
                    game.moveDown();
                }else if(input.equalsIgnoreCase("exit")){
                    System.out.println("Ačiū, kad žaidėte!");
                    break;
                }else {
                    System.out.println("Įvestis bloga!");
                    game.printBoard();
                    input = bufferRead.readLine();
                    continue;
                }
                System.out.println("____________________");
                if(movePossible){
                    game.addNewNumber();
                }
                game.printBoard();
                if(game.isWinner()){
                    System.out.println("Laimėjote!");
                    break;
                }else if(game.isFull() && game.canMove()){
                    System.out.println("Pralaimėjote!");
                    break;
                }
                input = bufferRead.readLine();
            }
            game.printBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
