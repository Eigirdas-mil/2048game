package com.example.kursinis2048;

import java.util.Random;


public class Board {

    private int[][] board;
    private int boardSize = 4;
    private int score = 0;
    int globalPointer = 0;

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void printBoard() {
        System.out.println("____________________");
        System.out.println("Jūsų taškai: " + score);
        System.out.println("____________________");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
                if (j + 1 < boardSize)
                    System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public Board() {
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }
        addNewNumber();
    }

    public void addNewNumber() {
        Random ix = new Random();
        Random jy = new Random();
        int i = ix.nextInt(boardSize);
        int j = jy.nextInt(boardSize);
        if(!isFull()) {
            while(board[i][j] != 0) {
                i = ix.nextInt(boardSize);
                j = jy.nextInt(boardSize);
            }
            int v = new Random().nextInt(4);
            if (v%2 == 0) {
                board[i][j] = 2;
            } else {
                board[i][j] = 4;
            }
        }
    }

    public boolean isFull() {
        for(int i=0 ; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                if(board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean isWinner() {
        for(int i=0 ; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                if(board[i][j] == 2048)
                    return true;
            }
        }
        return false;
    }

    public boolean canMove(){
        if(!isFull()){
            return true;
        }
        for(int i=0 ; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                if( ( (i+1<boardSize) && (board[i][j] == board[i+1][j]) ) || ((j+1<boardSize) && (board[i][j] == board[i][j+1]) )) {
                    return true;
                }
            }
        }
        return false;
    }

    public void moveUp() {
        for (int col = 0; col < boardSize; col++) {
            globalPointer = 0;
            for (int row = 0; row < boardSize; row++) {
                shiftRows(col, row, globalPointer <= row, false);
            }
        }
    }

    public void moveDown() {
        for (int col = 0; col < boardSize; col++) {
            globalPointer = boardSize - 1;
            for (int row = boardSize -1; row >= 0; row--) {
                shiftRows(col, row, globalPointer >= row, true);
            }
        }
    }

    private void shiftRows(int col, int row, boolean canShift, boolean b2) {
        if (board[row][col] != 0) {
            if (canShift) {
                shiftRowTiles(row, col, b2);
            }
        }
    }

    public void moveLeft() {
        for (int row = 0; row < boardSize; row++) {
            globalPointer = 0;
            for (int col = 0; col < boardSize; col++) {
                shiftCol(row, col, globalPointer <= col, false);
            }
        }
    }

    public void moveRight() {
        for (int row = 0; row < boardSize; row++) {
            globalPointer = boardSize - 1;
            for (int col = boardSize - 1; col >= 0; col--) {
                shiftCol(row, col, globalPointer >= col, true);
            }
        }
    }

    private void shiftCol(int row, int col, boolean canShift, boolean b2) {
        if (board[row][col] != 0) {
            if (canShift) {
                shiftColTiles(row, col, b2);
            }
        }
    }

    private void shiftRowTiles(int currentRow, int currentCol, boolean reverse) {
        if (board[globalPointer][currentCol] == 0 || board[globalPointer][currentCol] == board[currentRow][currentCol]) {
            if (currentRow > globalPointer || (reverse && (globalPointer > currentRow))) {
                sumTiles(currentCol, globalPointer, board[currentRow][currentCol]);
                board[currentRow][currentCol] = 0;
            }
        } else {
            updateGlobalPointer(reverse);
            shiftRowTiles(currentRow, currentCol, reverse);
        }
    }

    private void sumTiles(int currentCol, int globalPointer, int i) {
        board[globalPointer][currentCol] += i;
        this.score += board[globalPointer][currentCol];
    }


    private void shiftColTiles(int currentRow, int currentCol, boolean reverse) {
        if (board[currentRow][globalPointer] == 0 || board[currentRow][globalPointer] == board[currentRow][currentCol]) {
            if (currentCol > globalPointer || (reverse && (globalPointer > currentCol))) {
                sumTiles(globalPointer, currentRow, board[currentRow][currentCol]);
                board[currentRow][currentCol] = 0;
            }
        } else {
            updateGlobalPointer(reverse);
            shiftColTiles(currentRow, currentCol, reverse);
        }
    }

    private void updateGlobalPointer(boolean reverse) {
        if (reverse) {
            globalPointer--;
        } else {
            globalPointer++;
        }
    }

}
