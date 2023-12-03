package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Pregunta5 extends AppCompatActivity implements Form {
    private final static int NUMEROPREGUNTA = 5;
    private final static int VALORPREGUNTA = 2;
    private int puntuacion;
    private int puntuacionMax;
    private boolean modo10;
    private boolean preguntaRespondida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta5);
        puntuacion = getIntent().getExtras().getInt("puntuacion");
        modo10 = getIntent().getExtras().getBoolean("modo10");
        puntuacionMax=setMaxScore(modo10);
        preguntaRespondida=false;
        initFragment();

    }

    @Override
    public int setMaxScore(boolean mode) {
        if (mode) {
            return 10;
        } else {
            return 100;
        }
    }

    @Override
    public int setCorrectAnswer(boolean mode) {
        if (mode) {
            return VALORPREGUNTA;
        } else {
            return VALORPREGUNTA * 10;
        }
    }

    @Override
    public void initFragment() {
        FragmentProgreso fragmentProgreso = new FragmentProgreso();
        Bundle bundle = new Bundle();
        bundle.putInt("puntuacion", puntuacion);
        bundle.putInt("NUMEROPREGUNTA", NUMEROPREGUNTA);
        bundle.putInt("puntuacionMax", puntuacionMax);
        fragmentProgreso.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress5, fragmentProgreso).commit();
    }
}