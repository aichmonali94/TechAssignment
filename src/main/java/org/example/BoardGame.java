package org.example;


import java.util.Scanner;

public class BoardGame {

    /******** GLOBAL VARIABLES ****************/
    public static int[] p1Board;
    public static int[] p2Board;

    public static void main(String[] args) {
        playGame();
    }

    /* Runs the Board Game */
    public static void playGame(){

        setUpBoard();
        boolean pTurn = true;
        while(!gameP1Over() || !gameP2Over()){
            playRound(pTurn);
            pTurn = !pTurn;
        }

        if(p1Board[6] > p2Board[6]){
            int sum=0;
            for(int i=0 ; i< p1Board.length-1; i++){
                sum+=p1Board[i];
            }
            System.out.print("Player 1 WON the match :"+sum);
        }else if(p2Board[6] > p1Board[6]){
            int sum=0;
            for(int i=0 ; i< p2Board.length-1; i++){
                sum+=p2Board[i];
            }
            System.out.print("Player 2 WON the match :"+sum);
        }
    }

    /* Set up the board so the game can be played */
    public static void setUpBoard(){

        createBoard();
        fillBoard();
    }

    /*
    If the GAME is over then the method will be TRUE, else False
    When either or both sides of the board have no stones left in the pits, excluding the big-pits
     */
    public static boolean gameP1Over(){
        boolean gaveOver = false;
        for(int i = 0 ; i< p1Board.length-1; i++){
            if(p1Board[i] == 0){
                gaveOver= true;
            }else{
                break;
            }
        }
        return gaveOver;
    }

    public static boolean gameP2Over(){
        boolean gaveOver = false;
        for(int i = 0 ; i< p2Board.length-1; i++){
            if(p2Board[i] == 0){
                gaveOver= true;
            }else{
                break;
            }
        }
        return gaveOver;
    }


    /* This will run one turn alternating between the player1 & player2
    * p1Turn is true when it's Player1 turn */
    public static void playRound(boolean playersTurn){
        displayBoard();
        if(playersTurn){
            p1Turn();
        }else{
            p2Turn();
        }
    }

    /** Initialize both p1Board & p2Board **/
    public static void createBoard(){

        p1Board = new int [7];
        p2Board = new int[7];
    }

    /** Fill the p1Board & p2Board with stones , 4 per a pit
     * The pits are the first 6 elements of the array
     * 7th element represents the Big Pit and remains empty **/
    public static void fillBoard(){

        for(int i=0 ; i< p1Board.length -1 ; i++){
            p1Board[i] =6;
            p2Board[i] =6;
        }
    }

    public static void displayBoard(){
        System.out.println("BOARD");
        for(int i = p1Board.length -1 ; i >=0 ; i--){
            System.out.print("["+p1Board[i]+"]");
        }
        System.out.print("\n   ");
        for(int i = 0 ; i< p2Board.length ; i++){
            System.out.print("["+p2Board[i]+"]");
        }

        System.out.println();
    }

    /* executes the player 1 TURN */
    public static void p1Turn(){
        boolean endInBigPit = false;
        int pitIndex = selectP1Pit();
        endInBigPit = dropP1Stones(pitIndex);
        if(endInBigPit){
            displayBoard();
            p1Turn();
        }
    }

    /* Allows the player to select the PIT they want to draw from */
    public static int selectP1Pit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select a P1 PIT");

        return sc.nextInt();
    }

    /* Remove all the stones from selected pitIndex and deposit them one
    at a time accordingly to board game until all stones are dropped */
    public static boolean dropP1Stones(int pitIndex){

        int stones = p1Board[pitIndex];
        p1Board[pitIndex]= 0;
        int [] currArray = p1Board;
        int dropPit = pitIndex +1;
        int turnCheck = pitIndex +1;
        while(stones > 0){
            currArray[dropPit]+=1;
            stones -- ;
            dropPit ++;
            turnCheck ++;
            if(dropPit >= p1Board.length){
                currArray = p2Board;
                dropPit = 0;
            }
        }
        if(turnCheck == p1Board.length && stones ==0){
            return true;
        }
        return false;

    }

    public static void p2Turn(){
        boolean endInBigPit = false;
        int pitIndex = selectP2Pit();
        endInBigPit = dropP2Stones(pitIndex);
        if(endInBigPit){
            displayBoard();
            p2Turn();
        }
    }

    /* Allows the player to select the PIT they want to draw from */
    public static int selectP2Pit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select a P2 PIT");

        return sc.nextInt();
    }


    /* Remove all the stones from selected pitIndex and deposit them one
    at a time accordingly to board game until all stones are dropped */
    public static boolean dropP2Stones(int pitIndex){

        int stones = p2Board[pitIndex];
        p2Board[pitIndex]= 0;
        int [] currArray = p2Board;
        int dropPit = pitIndex +1;
        int turnCheck = pitIndex +1;
        while(stones > 0){
            currArray[dropPit]+=1;
            stones -- ;
            dropPit ++;
            turnCheck ++;
            if(dropPit >= p2Board.length){
                currArray = p1Board;
                dropPit = 0;
            }
        }
        if(turnCheck == p2Board.length && stones ==0){
            return true;
        }
        return false;
    }
}
