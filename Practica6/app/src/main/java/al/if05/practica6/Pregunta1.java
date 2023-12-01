package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Pregunta1 extends AppCompatActivity implements Form {

    private final static int NUMEROPREGUNTA = 1;
    private final static int VALORPREGUNTA = 2;
    private int puntuacion;
    private int puntuacionMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);


        FragmentProgreso fragmentProgress = new FragmentProgreso();
        Bundle bundle = new Bundle();
        Button btnTest = findViewById(R.id.btnPULSA);
        EditText etRespuesta = findViewById(R.id.etRespuesta);

        String nombre = getIntent().getExtras().getString("nombre");
        boolean modo10 = getIntent().getExtras().getBoolean("modo10");

        puntuacion = 0;
        puntuacionMax = setMaxScore(modo10);


        btnTest.setOnClickListener(view -> {
            String respuesta = etRespuesta.getText().toString();
            if (respuesta.equalsIgnoreCase("hola")) {
                puntuacion = setCorrectAnswer(modo10);
            }
            Intent intent = new Intent(this, Pregunta2.class);
            intent.putExtra("puntuacion", puntuacion);
            intent.putExtra("puntuacionMax", puntuacionMax);
            startActivity(intent);
        });


        bundle.putInt("puntuacionMax", puntuacionMax);
        bundle.putInt("NUMEROPREGUNTA", NUMEROPREGUNTA);
        bundle.putInt("puntuacion", puntuacion);
        fragmentProgress.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress, fragmentProgress).commit();


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
}