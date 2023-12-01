package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Pregunta2 extends AppCompatActivity {
    private final static int NUMEROPREGUNTA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);
        int puntuacion = getIntent().getExtras().getInt("puntuacion");
        int puntuacionMax = getIntent().getExtras().getInt("puntuacionMax");
        FragmentProgreso fragmentProgreso = new FragmentProgreso();
        Bundle bundle = new Bundle();

        bundle.putInt("puntuacion", puntuacion);
        bundle.putInt("NUMEROPREGUNTA", NUMEROPREGUNTA);
        bundle.putInt("puntuacionMax",puntuacionMax);
        fragmentProgreso.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress2, fragmentProgreso).commit();


    }
}