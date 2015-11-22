package in.titans.funwithdots;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import in.titans.funwithdots.gameutils.Point;
import in.titans.funwithdots.gameutils.State;
import in.titans.funwithdots.utils.Debug;
import in.titans.funwithdots.utils.GameConstants;
import in.titans.funwithdots.utils.PointPx;

public class GameActivity extends AppCompatActivity {
    DotBoard dotBoard;
    TextView p1Score, p2Score;
    Game game;

    int rows, cols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        Debug.check();
        Debug.bundle(bundle);
        rows = bundle.getInt(GameConstants.ROWS);
        cols = bundle.getInt(GameConstants.COLS);
        dotBoard = (DotBoard) findViewById(R.id.dot_board);
        dotBoard.setActivity(this);
        p1Score = (TextView) findViewById(R.id.p1_score_tv);
        p2Score = (TextView) findViewById(R.id.p2_score_tv);
//        p1Score.setBackgroundColor(ContextCompat.getColor(this, R.color.player1_color));
//        p2Score.setBackgroundColor(ContextCompat.getColor(this, R.color.player2_color));
        Debug.i("length: " + dotBoard.getHeight() + " width: " + dotBoard.getWidth(), false);

        game = new Game(rows, cols);
        startGame();
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_restart:
                restartGame();
                break;
        }
        return true;
    }

    private void restartGame() {
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);
        finish();
    }

    public void startGame() {
        dotBoard.drawAllDots(rows, cols);
        refresh();
    }

    public void refresh() {
        Debug.check();
        p1Score.setText("" + game.getPlayer1Score());
        p2Score.setText("" + game.getPlayer2Score());
        if (game.getTurn() == 0) {
            p1Score.setBackgroundResource(R.drawable.rounded_border_player1);
            p2Score.setBackgroundColor(Color.TRANSPARENT);
            Debug.i(""+game.getMaxScore()+game.getPlayer1Score(),false);
            if(game.getPlayer1Score() > game.getMaxScore()/2) {
                showWinDialog(game.getTurn());
            }
        } else {
            p2Score.setBackgroundResource(R.drawable.rounded_border_player2);
            p1Score.setBackgroundColor(Color.TRANSPARENT);
            Debug.i("" + game.getMaxScore() + game.getPlayer2Score(), false);
            if(game.getPlayer2Score() > game.getMaxScore()/2) {
                showWinDialog(game.getTurn());
            }
        }
    }

    private void showWinDialog(int turn) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, AlertDialog.BUTTON_POSITIVE);
        alertDialogBuilder.setMessage("Player " + (turn + 1) + "Won");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
            }
        });
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    public void move(PointPx start, PointPx end) {
        State state = game.move(new Point((int) start.getX() / GameConstants.lineWidth, (int) start.getY() / GameConstants.lineWidth), new Point((int) end.getX() / GameConstants.lineWidth, (int) end.getY() / GameConstants.lineWidth));
        Debug.i("" + state,false);
        if(state.getDelete()) {
            dotBoard.drawTransparentLine(start, end);
            Debug.i("delete",false);
        } else if (state.getValidMove() == true) {
            if (state.getBox1() != -1) {
                PointPx startPoint = new PointPx(state.getStartPointBox1().getX() * GameConstants.lineWidth, state.getStartPointBox1().getY() * GameConstants.lineWidth);
                PointPx endPoint = new PointPx(state.getEndPointBox1().getX() * GameConstants.lineWidth, state.getEndPointBox1().getY() * GameConstants.lineWidth);
                dotBoard.drawRec(startPoint, endPoint, game.getTurn());
                game.updateScore();
            }
            if (state.getBox2() != -1) {
                PointPx startPoint = new PointPx(state.getStartPointBox2().getX() * GameConstants.lineWidth, state.getStartPointBox2().getY() * GameConstants.lineWidth);
                PointPx endPoint = new PointPx(state.getEndPointBox2().getX() * GameConstants.lineWidth, state.getEndPointBox2().getY() * GameConstants.lineWidth);
                dotBoard.drawRec(startPoint, endPoint, game.getTurn());
                game.updateScore();
            }
        }
        refresh();
    }

}
