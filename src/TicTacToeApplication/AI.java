package TicTacToeApplication;

import java.util.ArrayList;
import java.util.Random;



public class AI {

    public int fc = 0;


    public ArrayList<Integer> returnAvailableSpots(TicTacToe game){
        ArrayList<Integer> choices = new ArrayList<>();


        for (int i = 0; i < game.board.length ; i++){
            // Add all available spots to our AI program
            if(game.board[i] == '-'){
                choices.add(i+1);
            }
        }
        return choices;
    }


    public int minmax(TicTacToe game, char player){

        fc += 1;

        if(game.isThereAWinner()){
            return player == game.aiMarker ? 10 : -10;
        } else if (game.isBoardFilled()){
            return 0;
        }

        ArrayList<Integer> availableSpots = returnAvailableSpots(game);
        ArrayList<PositionAndScore> positionAndScores = new ArrayList<>();

        for (int i: availableSpots){
            PositionAndScore positionAndScore = new PositionAndScore();
            game.board[i-1] = player;
            positionAndScore.index = i;
            positionAndScore.score = player == game.userMarker ? minmax(game, game.aiMarker) : minmax(game, game.userMarker);
            game.board[i-1] = '-';
            positionAndScores.add(positionAndScore);
        }

        int bestScore =  player == game.userMarker ? Integer.MAX_VALUE :Integer.MIN_VALUE;
        int bestMove = 0;
        for(PositionAndScore positionAndScore: positionAndScores){
            if(positionAndScore.score > bestScore && player == game.aiMarker){
                bestScore = positionAndScore.score;
                bestMove = positionAndScore.index ;
            } else if(positionAndScore.score < bestScore && player == game.userMarker){
                bestScore = positionAndScore.score;
                bestMove = positionAndScore.index ;
            }
        }

        return bestMove;

    }

    public int pickSpot(TicTacToe game){
        int score = minmax(game, game.currentMarker);
        System.out.println("Function called: " + fc);
        return score;
    }
}
