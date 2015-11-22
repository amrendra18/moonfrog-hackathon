package in.titans.funwithdots;

import in.titans.funwithdots.gameutils.Grid;
import in.titans.funwithdots.gameutils.GridFactory;
import in.titans.funwithdots.gameutils.Point;
import in.titans.funwithdots.gameutils.State;
import in.titans.funwithdots.utils.Debug;

public class Game {

    private int col;
    int gridLength;
    int turn = 0;
    int player1Score = 0;
    int player2Score = 0;


    GridFactory factory = new GridFactory();
    Grid grid = factory.getEasyGrid();
    private int maxScore;

    public Game(int row, int col){
        this.gridLength = row;
        this.col = col;
        this.maxScore = row*col;
        Debug.i("row: "+gridLength+" col: "+gridLength,false);
    }

    public void startGame() {
        turn = 0;
        player1Score = 0;
        player2Score = 0;
    }

    boolean lastDeleted = false;

    public State move(Point start, Point end) {
        State state = grid.playTurn(start, end, lastDeleted);
        lastDeleted = state.getDelete() == true || lastDeleted &&  state.getValidMove() == false;
        if(state.getValidMove() == false || (state.getBox1() != -1 || state.getBox2() != -1)) {

        } else {
            turn = turn ^ 1;
        }
        return state;
    }

    public void updateScore() {
        if(turn == 0 ) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
