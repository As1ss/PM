package al.if05.practica4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        String userName = getIntent().getExtras().get("userName").toString();
        int sumaTiradas = (int) getIntent().getExtras().get("sumaTiradas");
        int numTiradas = (int) getIntent().getExtras().get("numTiradas");
        int numTiradas4 = (int) getIntent().getExtras().get("numTiradas4");
        int numTiradas6 = (int) getIntent().getExtras().get("numTiradas6");
        int numTiradas8 = (int) getIntent().getExtras().get("numTiradas8");
        int sumaDado4 = (int) getIntent().getExtras().get("sumaDado4");
        int sumaDado6 = (int) getIntent().getExtras().get("sumaDado6");
        int sumaDado8 = (int) getIntent().getExtras().get("sumaDado8");
        TextView tvMensajeFinal = findViewById(R.id.tvMensajeFinal);
        Button btnContinuar = findViewById(R.id.btnContinuarGameOver);
        String mensajeFInal = userName + " ha logrado un total de " + sumaTiradas + " de puntos en " + numTiradas + " tiradas\n\n"
                + numTiradas4 + " tiradas con el dado de 4 caras para lograr " + sumaDado4 + " puntos.\n\n" + numTiradas6 + " tiradas con el dado de 6 caras para lograr " + sumaDado6 + " puntos." +
                "\n\n" + numTiradas8 + " tiradas con el dado de 8 caras para lograr " + sumaDado8 + " puntos.";
        tvMensajeFinal.setText(mensajeFInal);


        ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(tvMensajeFinal, "backgroundColor",
                new ArgbEvaluator(), getResources().getColor(R.color.white), getResources().getColor(R.color.purple));
        backgroundColorAnimator.setDuration(1000); // 1000 ms = 1 segundo
        backgroundColorAnimator.start();

        ObjectAnimator foregroundColorAnimator = ObjectAnimator.ofObject(tvMensajeFinal, "textColor",
                new ArgbEvaluator(), getResources().getColor(R.color.black), getResources().getColor(R.color.white));
        foregroundColorAnimator.setDuration(1000);
        foregroundColorAnimator.start();


        btnContinuar.setOnClickListener(view->{
            onBackPressed();
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}