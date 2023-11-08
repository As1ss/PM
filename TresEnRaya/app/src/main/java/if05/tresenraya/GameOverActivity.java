package if05.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    String userName;
    boolean winGame;
    boolean loseGame;
    boolean tablas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        TextView tvTest = findViewById(R.id.tvTest);
        userName = getIntent().getExtras().get("userName").toString();
        winGame = getIntent().getBooleanExtra("winGame",false);
        loseGame= getIntent().getBooleanExtra("loseGame",false);
        tablas = getIntent().getBooleanExtra("tablas",false);


        if (winGame){
            tvTest.setText("HAS GANADO PAYICO"+userName);
        }
        else if (loseGame){
            tvTest.setText("HAS PERDIDO PAYICO"+userName);
        }
        else if (tablas){
            tvTest.setText("HAS EMPATADO PAYICO"+userName);
        }

    }
}