package al.if05.practica3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class GanarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganar);
        Button btnContinuar = findViewById(R.id.buttonContinuar);

        btnContinuar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            startActivity(intent);
        });
        startColorAnimation();
    }

    private void startColorAnimation() {
        int colorAmarillo = ContextCompat.getColor(this, R.color.yellow);
        int colorRosa = ContextCompat.getColor(this, R.color.pink);
        View lyPrincipal = findViewById(R.id.layoutPrincipal);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorAmarillo, colorRosa);
        colorAnimation.setDuration(1000);

        colorAnimation.addUpdateListener(animator -> {
            int animatedColor = (int) animator.getAnimatedValue();
            lyPrincipal.setBackgroundColor(animatedColor);
        });

        colorAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                colorAnimation.reverse();
            }
        });

        colorAnimation.start();
    }
}