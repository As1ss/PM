package al.if05.repasoexamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String userName = getIntent().getExtras().get("userName").toString();
        TextView tvContador = findViewById(R.id.tvContador);
        Button btnMas = findViewById(R.id.btnMas);
        Button btnMenos = findViewById(R.id.btnMenos);
        contador = 0;
        tvContador.setText(String.valueOf(contador));

        btnMas.setOnClickListener(v -> {
            contador++;

            tvContador.setText(String.valueOf(contador));
        });
        btnMenos.setOnClickListener(v -> {
            ObjectAnimator anim = ObjectAnimator.ofFloat(btnMenos,"scaleX",20f);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(btnMenos,"scaleX",1);
            anim.setDuration(1000);
            anim2.setDuration(1000);
            anim.start();

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationEnd(@NonNull Animator animation) {
                    anim2.start();
                }

                @Override
                public void onAnimationCancel(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animation) {

                }
            });


        });

        Toast.makeText(this, "HOLA" + userName, Toast.LENGTH_SHORT).show();
    }
}