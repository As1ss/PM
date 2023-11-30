package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pregunta1 extends AppCompatActivity {

    final static int NUMEROPREGUNTA=1;
    int valorpregunta=2;
    int puntuacionMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);

        FragmentProgreso fragmentProgress = new FragmentProgreso();
        Bundle bundle = new Bundle();
        Button btnTest = findViewById(R.id.btnPULSA);


        String nombre = getIntent().getExtras().getString("nombre");
        boolean modo10 = getIntent().getExtras().getBoolean("modo10");

        if(modo10){
            puntuacionMax=10;
            valorpregunta=valorpregunta;
        }
        else{
            puntuacionMax=100;
            valorpregunta=valorpregunta*10;
        }

        bundle.putInt("puntuacionMax",puntuacionMax);
        bundle.putInt("NUMEROPREGUNTA",NUMEROPREGUNTA);
        bundle.putInt("valorpregunta",valorpregunta);
        fragmentProgress.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress,fragmentProgress).commit();





    }
}