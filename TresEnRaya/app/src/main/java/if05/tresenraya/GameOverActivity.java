package if05.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    String userName;
    boolean winGame;
    boolean loseGame;
    boolean tablas;
    TextView tvTest;
    ImageView ivScreenshot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ivScreenshot = findViewById(R.id.ivScreenshot);
        Button btnContinue = findViewById(R.id.btnContinue);

        loadComponents();
        showWinner();
        showAnimationWinner();


        btnContinue.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });



    }

    private void showAnimationWinner() {
        // Crea un ValueAnimator para cambiar la opacidad (alfa) del TextView
        final ValueAnimator alphaAnimator = ValueAnimator.ofFloat(1f, 0.3f); // Valores de opacidad de 1 a 0.3
        alphaAnimator.setDuration(1000); // Duración de la animación en milisegundos
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE); // Repite la animación infinitamente
        alphaAnimator.setRepeatMode(ValueAnimator.REVERSE); // Invierte la animación al final para que parpadee

        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                float alpha = (float) animator.getAnimatedValue();
                tvTest.setAlpha(alpha);
            }
        });

        // Inicia la animación
        alphaAnimator.start();
    }

    private void loadComponents() {
        String screenshotPath = getIntent().getStringExtra("screenshotPath");
        if (screenshotPath != null && !screenshotPath.isEmpty()) {
            // Cargar la captura de pantalla en el ImageView
            Bitmap screenshotBitmap = BitmapFactory.decodeFile(screenshotPath);
            ivScreenshot.setImageBitmap(screenshotBitmap);
        } else {
            // No se proporcionó una captura de pantalla, puedes ocultar o cambiar el ImageView
            ivScreenshot.setVisibility(View.GONE);
        }
        tvTest = findViewById(R.id.tvTest);
        userName = getIntent().getExtras().get("userName").toString();
        winGame = getIntent().getBooleanExtra("winGame",false);
        loseGame= getIntent().getBooleanExtra("loseGame",false);
        tablas = getIntent().getBooleanExtra("tablas",false);
    }

    private void showWinner() {
        if (winGame){
            tvTest.setTextColor(Color.GREEN);
            tvTest.setText("EL GANADOR ES "+userName);

        }
        else if (loseGame){
            tvTest.setTextColor(Color.GREEN);
            tvTest.setText("EL GANADOR ES EL JUGADOR 2");
        }
        else if (tablas){
            tvTest.setTextColor(Color.YELLOW);
            tvTest.setText(userName+"HA EMPATADO CON JUGADOR 2");
        }
    }
}

