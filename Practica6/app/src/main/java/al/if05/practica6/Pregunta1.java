package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Pregunta1 extends AppCompatActivity implements Form {

    private final static int NUMEROPREGUNTA = 1;
    private final static int VALORPREGUNTA = 3;
    private int puntuacion;
    private int puntuacionMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);





        Button btnTest = findViewById(R.id.btnPULSA);
        EditText etRespuesta = findViewById(R.id.etRespuesta);

        String nombre = getIntent().getExtras().getString("nombre");
        boolean modo10 = getIntent().getExtras().getBoolean("modo10");

        puntuacion = 0;
        puntuacionMax = setMaxScore(modo10);


        btnTest.setOnClickListener(view -> {
            String respuesta = etRespuesta.getText().toString();
            if (respuesta.equalsIgnoreCase("hola")) {
                puntuacion += setCorrectAnswer(modo10);
            }
            else{
                puntuacion=0;
            }
            Intent intent = new Intent(this, Pregunta2.class);
            intent.putExtra("puntuacion", puntuacion);
            intent.putExtra("modo10",modo10);

            startActivity(intent);
        });


        initFragment();

    }


    @Override
    public int setMaxScore(boolean modo10) {
        if (modo10) {
            return 10;

        } else {
            return 100;

        }
    }

    @Override
    public int setCorrectAnswer(boolean modo10) {
        if (modo10) {
            return VALORPREGUNTA;
        } else {
            return VALORPREGUNTA * 10;
        }
    }

    @Override
    public void initFragment() {
        FragmentProgreso fragmentProgress = new FragmentProgreso();
        Bundle bundle = new Bundle();
        bundle.putInt("puntuacionMax", puntuacionMax);
        bundle.putInt("NUMEROPREGUNTA", NUMEROPREGUNTA);
        bundle.putInt("puntuacion", puntuacion);
        fragmentProgress.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress, fragmentProgress).commit();
    }
}