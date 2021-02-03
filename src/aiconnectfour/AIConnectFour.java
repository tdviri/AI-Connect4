/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiconnectfour;

import java.util.Scanner;
public class AIConnectFour{
    public static void main(String [] args){
        boolean tieGame = false; //boolean value will turn true if there are no open columns left
        String disk;
        boolean redTurn;
        AI ai = new AI();
        
        
        String [][] board = new String [7][8];
        for (int i = 0; i < board.length; i++){
            System.out.println();
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = "|   ";
                System.out.print(board[i][j]);
            }
        }
        
        Scanner stdin = new Scanner(System.in);
        System.out.println("\nWould you like to play against human(0) or CPU(1)?");
        int mode = stdin.nextInt();
        
        
        
        for (int i = 0; i < 21; i++){ //The loop will account for a maximum total of 26 turns for each player
            
            
            if (mode == 0){
        System.out.println("\nDrop red disk into column(0-6): ");
         disk = stdin.next();
        int result = handleInvalidInput(disk, board);//This method will check whether the user input is invalid and return a valid input 
         redTurn = true;
        Game.setBoard(board, result, redTurn); //Updating the board after red player's turn
        
            }
            
            if (mode == 1){
                AI.computer(board);
            }
            
        if (Game.isFour(board)){ 
            System.out.println("\nRed player wins!");
            break;
        }
        
        
        if (i == 25){
            tieGame = true;
            break;
        }
        System.out.println("\nDrop yellow disk into column(0-6): ");
        disk = stdin.next();
        int result = handleInvalidInput(disk, board);
        redTurn = false;
        Game.setBoard(board, result, redTurn);
        if (Game.isFour(board)){
            System.out.println("\nYellow player wins!");
            break;
        }
        } 
        
        if (tieGame){
            System.out.println("Tie");
        }
    }
    
    
    
    
     public static int handleInvalidInput(String ry, String [][] m){ //Method to check for an invalid user input and return a valid input
        
        Scanner stdin = new Scanner(System.in);
        String ry2 = "8";
            String temp;
        
        if (ry.charAt(0) < 48 || ry.charAt(0) > 54 || ry.length() != 1 || m[0][Integer.parseInt(ry)] != "|   "){
                while(ry2.charAt(0) < 48 || ry2.charAt(0) > 54 || ry2.length() != 1 || 
                        m[0][Integer.parseInt(ry2)] != "|   "){
                    System.out.println("Invalid input. Try again: ");
                    ry2 = stdin.next();    
            }
                temp = ry;
                ry = ry2;
                ry2 = temp;
            }
           
            return Integer.parseInt(ry);


}
}
 


class Game{ //This class is used to constantly update the game board after each turn
    
    private static String red = "| R ";
    private static String yellow = "| Y ";
    private static String empty = "|   ";
    
    
    public static void setBoard(String [][] board, int disk, boolean redTurn){
        
        int count = 6;
            for (int x = 0; x < board.length; x++){
                System.out.println();
                for (int j = 0; j < board[x].length; j++){
                    if (j == disk && x == 6){
                        if (board[x][j] == red || board[x][j] == yellow){
                            while (board[count][j] != empty){
                                count--;
                            }
                        }
                        if (redTurn){
                        board[count][j] = red;
                    }
                    else{
                        board[count][j] = yellow;
                    }
                
                }
                        
                        
                    }   
                }
            
            
            for (int i = 0; i < board.length; i++){
                System.out.println();
                for (int j = 0; j < board[i].length; j++){
                    System.out.print(board[i][j]);
                }
            }
           
    }
    

    
    
    public static boolean isFour(String [][] board){ //Returns true if there is a four-in-a-row
        
        int count;
        for (int i = 0; i < board.length; i++){
            count = 0;
            for (int j = 0; j < board[i].length - 1; j++){
                    if (board[i][j] == board[i][j + 1] && board[i][j] != "|   "){
                        count++;
                    }
                    else{
                        count = 0;
                    }
                    if (count == 3){
                            return true;
                        
                    }
                    
            }
        }
        
        
        
        for (int x = 0; x < 7; x++){
        count = 0;
        for (int i = 0; i < board.length - 1; i++){
            if (board[i][x] != "|   " && board[i][x] == board[i + 1][x]){
                count++;
            }
            else{
                count = 0;
            }
            
            if (count == 3){
                    return true;
                
                
            }
        }
        }
        
    int c = 6;
       for (int i = 0; i < 6; i++){
           count = 0;
           int g = i;
           for (int j = 0; j < c; j++){
               if (board[g][j] == board[g + 1][j + 1] && board[g][j] != "|   "){
                   count++;
               }
               else{
                   count = 0;
               }
               if (count == 3){
                       return true;
                   
               }
               g++;
           }
           c--;
       }
       
       
       c = 6;
       for (int i = 0; i < 6; i++){
           count = 0;
           int g = i;
           for (int j = 0; j < c; j++){
               if (board[j][g] == board[j + 1][g + 1] && board[j][g] != "|   "){
                   count++;
               }
               else{
                   count = 0;
               }
               if (count == 3){
                       return true;
                   
               }
               g++;
           }
           c--;
       }
       
       
       
       c = 0;
       for (int i = 0; i < 6; i++){
           count = 0;
           int g = i;
           for (int j = 6; j > c; j--){
               if (board[g][j] == board[g + 1][j - 1] && board[g][j] != "|   "){
                   count++;
               }
               else{
                   count = 0;
               }
               if (count == 3){
                   
                       return true;
                   
               }
               g++;
           }
           c++;
       }
       
       
       c = 6;
       for (int i = 6; i > 0; i--){
           count = 0;
           int g = i;
           for (int j = 0; j < c; j++){
               if (board[g][j] == board[g - 1][j + 1] && board[g][j] != "|   "){
                   count++;
               }
               else{
                   count = 0;
               }
               if (count == 3){
                   
                       return true;
                    
               }
               g--;
           }
           c--;
       }
       
       
        return false;
        
        
    }
    
        
    }


