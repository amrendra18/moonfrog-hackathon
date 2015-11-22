package in.titans.funwithdots;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import in.titans.funwithdots.utils.Debug;
import in.titans.funwithdots.utils.GameConstants;

public class StartActivity extends AppCompatActivity {
    EditText columnEt, rowEt;
    Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        columnEt = (EditText) findViewById(R.id.columns_et);
        rowEt = (EditText) findViewById(R.id.rows_et);
        newGame = (Button) findViewById(R.id.new_game_btn);
//        startNewGame();

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        if (Integer.parseInt(rowEt.getText().toString()) > 6 || Integer.parseInt(columnEt.getText().toString()) > 6) {
            Debug.showShort("Row or Column can't be more than 6", this, false);
            return;
        }
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(GameConstants.COLS, Integer.parseInt(columnEt.getText().toString()));
        bundle.putInt(GameConstants.ROWS, Integer.parseInt(rowEt.getText().toString()));
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
