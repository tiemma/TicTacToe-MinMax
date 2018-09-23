package TicTacToeApplication;

import java.util.Scanner;

public class TicTacToeApplication {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean doYouWantToPlay = true;

        while(doYouWantToPlay){

            System.out.println("Time to play? \n Pick a character");
            System.out.println();


            System.out.println("Enter a single character that will represent you on the board");
            char playerToken = sc.next().charAt(0);


            System.out.println("Enter a single character that will represent the opponent on the board");
            char opponentToken = sc.next().charAt(0);

            TicTacToe game = new TicTacToe(playerToken, opponentToken);


            AI ai = new AI();


            //Set up the game
            System.out.println();
            System.out.println("We can start the game, to play enter a token and the numbers shall be put in its place");

            TicTacToe.printIndexBoard(game.board);

            System.out.println();


            //Let's play
            while(game.gameOver().contains("notOver")){
                if(game.currentMarker == game.userMarker){

                    System.out.println("It's your turn, Enter a spot for your token");

                    int spot = sc.nextInt();
                    while(!game.playTurn(spot)){

                        System.out.println("Spot is already taken: " + spot);
                        spot = sc.nextInt();

                    }

                    System.out.println("You picked a correct spot: " + spot);

                } else {
                    // AI's turn

                    System.out.println("It's the computer's turn to play");

                    int aiSpot = ai.pickSpot(game);
                    game.playTurn(aiSpot);

                    System.out.println("AI picked the current spot: " + aiSpot);

                }

                System.out.println();

                TicTacToe.printIndexBoard(game.board);
            }

            System.out.println();
            System.out.println(game.gameOver());
            System.out.println("Do you want to play a new game: Y or N");

            doYouWantToPlay = sc.next().charAt(0) == 'Y';

            System.out.println();


        }

    }
}
