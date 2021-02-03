/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiconnectfour;

/**
 *
 * @author dviri
 */
    public class AI{
    
    
    public static void computer(String [][] board){ //
        
        int score;
        int bm = 0;
        int bm2 = 0;
        String red = "| R ";
        String yellow = "| Y ";
        String empty = "|   ";
        int bestScore = -Integer.MAX_VALUE;
            
                for (int j = 0; j < 6; j++){
                    int count = 6;
                    if (board[0][j] == empty){
                        if (board[6][j] != empty){
                            while(board[count][j] != empty){
                                count--;
                            }
                        }
                        
                        board[count][j] = red;
                        score = minimax(board, 6, false); //Minimax method with a depth of 6
                        board[count][j] = empty;
                        if (score > bestScore){ //Storing the best possible move for the AI
                            bestScore = score;
                            bm = count;
                            bm2 = j;
                        }
                        }
                }
            
            board[bm][bm2] = red; 
        
        System.out.println();
        for (int i = 0; i < board.length; i++){
            System.out.println();
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j]);
            }
        }
        
          
}
    
    
    
    public static int minimax(String [][] board, int depth, boolean isMaximizing){
        
        int score;
        int bestScore;
        String red = "| R ";
        String yellow = "| Y ";
        String empty = "|   ";
        if (depth == 0 || Game.isFour(board)){ //If the game is over or the depth is 0, then the score is returned
            return FutureMove(board);   
        }
        
        if (isMaximizing){ //The AI is the maximizing player- trying to find the highest possible total score to make the best possible next move
            bestScore = -Integer.MAX_VALUE; 
            
            
            //This loop ensures that whenever the disk is dropped, it falls directly on top of the last disk in that column
                for (int j = 0; j < 6; j++){ 
                    int count = 6;
                    if (board[0][j] == empty){
                        if (board[6][j] != empty){
                            while(board[count][j] != empty){
                                count--; 
                            }
                        }
                       
                        board[count][j] = red;
                        score = minimax(board, depth - 1, false); //Using recursion to find all possible future outcomes 
                        board[count][j] = empty;
                    
                    bestScore = Math.max(score, bestScore); 
                }
                }
            
            return bestScore; //returning the maximum score, which is the best next move for the AI to make
            
        }
        
        else{ //Used if it's the minimizing player's turn- yellow player(human)
            bestScore = Integer.MAX_VALUE;
                for (int j = 0; j < 6; j++){
                    int count = 6;
                    if (board[0][j] == "|   "){
                        if (board[6][j] != "|   "){
                            while(board[count][j] != "|   "){
                                count--;
                            }
                        }
                        board[count][j] = "| Y ";
                        score = minimax(board, depth - 1, true);
                        board[count][j] = "|   ";
                    bestScore = Math.min(score, bestScore);
                }
                }
            return bestScore; //returning the minimum score, which is the best mvoe for the human to make
            
            
        }
        
    }
    
    //Evaluation function method - returns the score for each possible future outcome
    public static int FutureMove(String [][] m){
        int total = 0;
        String red = "| R ";
        String yellow = "| Y ";
        String empty = "|   ";
        String belowEmpty = "";
        int redcount;
        int emptycount;
        int yellowcount;
        
        
        
        //checking middle column
        for (int i = 0; i < m.length; i++){
            if (m[i][3] == red){
                total += 4;
               
            }
        }
        
        //checking horizontally left to right
        for (int i = 0; i < m.length; i++){
            
            for (int d = 0; d < 4; d++){
              
                emptycount = 0;
                redcount = 0;
                yellowcount = 0;
           
            for (int j = d; j < d + 4; j++){
                if (m[i][j] == empty){
                    emptycount++;
                    if (i < 6){
                        belowEmpty = m[i + 1][j];
                    }
                 }
                    if (m[i][j] == red){
                    redcount++;
                }
                    if (m[i][j] == yellow){
                        yellowcount++;
                    }
                    
                
                    if (emptycount == 2 && redcount == 2){
                    total += 2;
                }
                
                    if (redcount == 3 && emptycount == 1){
                    total += 3;
                }
                
                    if (redcount == 4){
                    total += 100000000;
                }
                    if (yellowcount == 2 && emptycount == 2){
                    total -= 2;
                }
                    if (yellowcount == 3 && emptycount == 1 && belowEmpty != empty){
                    total -= 100;
                }

                    
            }
        }
        }
        
        
        
        
        
        //checking vertically top to bottom 
        for (int i = 0; i < m.length; i++){
            for (int d = 0; d < 4; d++){
                emptycount = 0;
                redcount = 0;
                yellowcount = 0;
            for (int j = d; j < d + 4; j++){
                if (m[j][i] == empty){
                    emptycount++;
                    if (j < 6){
                        belowEmpty = m[j + 1][i];
                    }
                 }
                    if (m[j][i] == red){
                    redcount++;
                }
                    if (m[j][i] == yellow){
                        yellowcount++;
                    }
                    
                
                    if (emptycount == 2 && redcount == 2){
                    total += 2;
                }
                
                    if (redcount == 3 && emptycount == 1){
                    total += 3;
                }
                
                    if (redcount == 4){
                    total += 100000000;
                }
                    if (yellowcount == 2 && emptycount == 2){
                    total -= 2;
                }
                    if (yellowcount == 3 && emptycount == 1 && belowEmpty != empty){
                    total -= 100;
                }
 
                    
            }
        }
        }
        
        
        
        //checking diagonally from top left to bottom 
        belowEmpty = "";
        for (int row = 0; row < 4; row++){
            int diagonal = -1;
            for (int i = row; i < 4; i++){
                emptycount = 0;
                redcount = 0;
                yellowcount = 0;
                diagonal++;
                int x = i;
                for (int j = diagonal; j < 4 + diagonal; j++){
                   
                    if (m[x][j] == empty){
                    emptycount++;
                    if (x < 6){
                        belowEmpty = m[x + 1][j];
                    }
                 }
                    if (m[x][j] == red){
                    redcount++;
                }
                    if (m[x][j] == yellow){
                        yellowcount++;
                    }
                
                    if (emptycount == 2 && redcount == 2){
                    total += 2;
                }
                
                    if (redcount == 3 && emptycount == 1){
                    total += 3;
                }
                
                    if (redcount == 4){
                    total += 100000000;
                }
                    if (yellowcount == 2 && emptycount == 2){
                    total -= 2;
                }
                    if (yellowcount == 3 && emptycount == 1 && belowEmpty != empty){
                    total -= 100;
                }

                x++;
            }
        }
        }
        
        
        
        
        
        //checking diagonally from top left to right
        belowEmpty = "";
        for (int row = 1; row < 4; row++){
            int diagonal = -1;
            for (int i = row; i < 4; i++){
                emptycount = 0;
                redcount = 0;
                yellowcount = 0;
                diagonal++;
                int x = i;
                for (int j = diagonal; j < 4 + diagonal; j++){
                   
                    if (m[j][x] == empty){
                    emptycount++;
                    if (j < 6){
                        belowEmpty = m[j + 1][x];
                    }
                 }
                    if (m[j][x] == red){
                    redcount++;
                }
                    if (m[j][x] == yellow){
                        yellowcount++;
                    }
                
                    if (emptycount == 2 && redcount == 2){
                    total += 2;
                }
                
                    if (redcount == 3 && emptycount == 1){
                    total += 3;
                }
                
                    if (redcount == 4){
                    total += 100000000;
                }
                    if (yellowcount == 2 && emptycount == 2){
                    total -= 2;
                }
                    if (yellowcount == 3 && emptycount == 1 && belowEmpty != empty){
                    total -= 100;
                }

                x++;
            }
        }
        }
        
        
        
        
        
        
        //checking diagonally from top right to bottom
        for (int i = 4; i > 0; i--){
            int g = 7;
            for (int diagonal = i; diagonal > 0; diagonal--){
                g--;
                yellowcount = 0;
                emptycount = 0;
                redcount = 0;
                int x = g;
                for (int j = 4 - diagonal; j < 8 - diagonal; j++){
                    
                    if (m[j][x] == empty){
                    emptycount++;
                    if (j < 6){
                        belowEmpty = m[j + 1][x];
                    }
                 }
                    if (m[j][x] == red){
                    redcount++;
                }
                    if (m[j][x] == yellow){
                        yellowcount++;
                    }
                
                    if (emptycount == 2 && redcount == 2){
                    total += 2;
                }
                
                    if (redcount == 3 && emptycount == 1){
                    total += 3;
                }
                    
                    if (redcount == 4){
                    total += 100000000;
                }
                    if (yellowcount == 2 && emptycount == 2){
                    total -= 2;
                }
                    if (yellowcount == 3 && emptycount == 1 && belowEmpty != empty){
                    total -= 100;
                }

                x--;
            }
        }
        }
             
        
        
        
        //checking diagonally from top right to left
        for (int i = 5; i > 2; i--){
            int g = -1;
            for (int diagonal = i; diagonal > 2; diagonal--){
                g++;
                yellowcount = 0;
                emptycount = 0;
                redcount = 0;
                int x = g;
                for (int j = diagonal; j > diagonal - 4; j--){
                    
                    if (m[x][j] == empty){
                    emptycount++;
                    if (x < 6){
                        belowEmpty = m[x + 1][j];
                    }
                 }
                    if (m[x][j] == red){
                    redcount++;
                }
                    if (m[x][j] == yellow){
                        yellowcount++;
                    }
                
                    if (emptycount == 2 && redcount == 2){
                    total += 2;
                }
                
                    if (redcount == 3 && emptycount == 1){
                    total += 3;
                }
                    
                    if (redcount == 4){
                    total += 100000000;
                }
                    if (yellowcount == 2 && emptycount == 2){
                    total -= 2;
                }
                    if (yellowcount == 3 && emptycount == 1 && belowEmpty != empty){
                    total -= 100;
                }

                x++;
            }
        }
        }
             
      
return total;
    }
    
  
}
    

