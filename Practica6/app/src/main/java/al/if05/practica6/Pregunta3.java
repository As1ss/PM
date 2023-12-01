package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Pregunta3 extends AppCompatActivity implements Form{

    private final static int NUMEROPREGUNTA =3;
    private final static int VALORPREGUNTA = 1;
    private int maxPuntuacion;
    private int puntuacion;
    private boolean modo10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta3);


        puntuacion=getIntent().getExtras().getInt("puntuacion");
        modo10= getIntent().getExtras().getBoolean("modo10");
        maxPuntuacion=setMaxScore(modo10);









        initFragment();
    }

    @Override
    public int setMaxScore(boolean mode) {
        return 0;
    }

    @Override
    public int setCorrectAnswer(boolean mode) {
        return 0;
    }

    @Override
    public void initFragment() {
        FragmentProgreso fragmentProgreso = new FragmentProgreso();
        Bundle bundle = new Bundle();
        bundle.putInt("puntuacion",puntuacion);
        bundle.putInt("maxPuntuacion",maxPuntuacion);
        bundle.putInt("NUMEROPREGUNTA",NUMEROPREGUNTA);
        fragmentProgreso.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress3,fragmentProgreso).commit();
    }
}