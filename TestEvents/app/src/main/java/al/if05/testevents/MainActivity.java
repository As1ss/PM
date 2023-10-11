package al.if05.testevents;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.button);
        TextView text = findViewById(R.id.texto);


        //Método listener con expresion lambda
        boton.setOnClickListener(view -> {
            final float velocidad = 500f;
            Toast.makeText(this, "HOLA BOTBERTO", Toast.LENGTH_SHORT).show();
            text.setText("AHORA HA CAMBIADOLF");
            ObjectAnimator animator = ObjectAnimator.ofFloat(text, "translationY",  velocidad);
            animator.setDuration(3500);
            animator.start();

            ObjectAnimator animatorButton = ObjectAnimator.ofFloat(boton,"scaleX",2f);
            animatorButton.setDuration(2500);
            animatorButton.start();

            if(boton.getScaleX()==2f){
                ObjectAnimator animatorButton2 = ObjectAnimator.ofFloat(boton,"scaleX",1f);
                animatorButton2.setDuration(2500);
                animatorButton2.start();
            }




            Log.d("DEBUG","TEST");

        });
/*
  //Método listener instanciando una clase anónima
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
 */


    }
}

/*
 //Método OnClick declarado en el documento XML
    public void salute(View view) {
        if (view instanceof Button) {
            Toast.makeText(this, "HAS PULSADO BOTON", Toast.LENGTH_SHORT).show();
        }
        if (view instanceof TextView) {
            Toast.makeText(this, "HAS PULSADO EL TEXTO", Toast.LENGTH_SHORT).show();
        }
    }
}
 */
