package TicTacToeApplication;

public class TicTacToe {



    protected char[] board;
    protected char userMarker;
    protected char aiMarker;
    protected char winner;
    protected char currentMarker;


    public TicTacToe(char userMarker, char aiMarker) {
        this.board = setBoard();
        this.userMarker = userMarker;
        this.aiMarker = aiMarker;
        this.winner = '-';
        this.currentMarker = userMarker;
    }

    private static char[] setBoard() {
        char[] board = new char[9];
        for(int i=0;i < board.length; i++){
            board[i] = '-';
        }
        return board;
    }

    public boolean playTurn(int spot){
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);
        if (isValid){
            board[spot-1] = currentMarker;
            currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
        }
        printIndexBoard(board);
        return isValid;
    }

    private boolean withinRange(int number) {
        return number > 0 && number < board.length + 1;
    }

    private boolean isSpotTaken(int spot) {
        return board[spot-1] != '-';
    }

    public static void printIndexBoard(char[] board){
        System.out.println();
        for(int i = 0; i < board.length; i++){
            if(i % 3 == 0 && i != 0){
                System.out.println();
                System.out.println("-----");
            }
            System.out.print("|" + board[i]);
        }
        System.out.println();
    }


    public boolean isThereAWinner(){
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        boolean diagonalsAndMiddles = (leftDi() || rightDi() || middleRow() || secondCol()) && board[4] != '-';
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';
        if (diagonalsAndMiddles){
            this.winner = board[4];
        }else if(topAndFirst){
            this.winner = board[0];
        }else if (bottomAndThird){
            this.winner = board[8];
        }
        return diagonalsAndMiddles || topAndFirst || bottomAndThird;
    }

    private boolean bottomRow() {
        return board[7]  == board[6] && board[7] == board[8];
    }

    private boolean thirdCol() {
        return board[2]  == board[5] && board[2] == board[8];
    }

    private boolean firstCol() {
        return board[3]  == board[0] && board[0] == board[6];
    }

    private boolean topRow() {
        return board[0]  == board[1] && board[0] == board[2];
    }

    private boolean leftDi() {
        return board[4]  == board[8] && board[4] == board[0];
    }

    private boolean rightDi() {
        return board[4]  == board[2] && board[4] == board[6];
    }

    private boolean middleRow() {
        return board[3]  == board[4] && board[3] == board[5];
    }

    private boolean secondCol() {
        return board[1]  == board[4] && board[1] == board[7];
    }

    public boolean isBoardFilled(){
        for(char i: board){
            if (i == '-') return false;
        }
        return true;
    }

    public String gameOver(){
        boolean didSomeoneWin = isThereAWinner();
        if(didSomeoneWin){
            return "We have a winner! The winner is " + this.winner;
        } else if(isBoardFilled()){
            return "Draw: Game Over";
        }
        return "There is no current winner: notOver";
    }
}
