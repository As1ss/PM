package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class Pregunta1 extends AppCompatActivity implements Form {

    private final static int NUMEROPREGUNTA = 1;
    private final static int VALORPREGUNTA = 3;

    private int puntuacion;
    private int puntuacionMax;
    private int valorPregunta;
    private boolean preguntaRespondida;
    private String nombre;
    private boolean modo10;
    private Button btnTest;
    private EditText etRespuesta;
    private ArrayList<String> respuestasHistorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);
        ActivityManager.addActivity(this);
        respuestasHistorial= new ArrayList<String>();


        preguntaRespondida = false;


        btnTest = findViewById(R.id.btnPULSA);
        etRespuesta = findViewById(R.id.etRespuesta);

        nombre = getIntent().getExtras().getString("nombre");
        modo10 = getIntent().getExtras().getBoolean("modo10");

        puntuacion = 0;
        puntuacionMax = setMaxScore(modo10);
        valorPregunta = setCorrectAnswer(modo10);


        btnTest.setOnClickListener(view -> {
            String respuesta = etRespuesta.getText().toString();
            String respuestaCorrecta = "Filtrar el trafico de red y prevenir accesos no autorizados";
            if (respuesta.equals("")) {
                Toast.makeText(this, "INTRODUCE UNA RESPUESTA", Toast.LENGTH_SHORT).show();
            } else {
                if (respuesta.equalsIgnoreCase(respuestaCorrecta) && !preguntaRespondida) {
                    puntuacion += setCorrectAnswer(modo10);
                    respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" correcta: "+valorPregunta);
                    etRespuesta.setEnabled(false);
                    preguntaRespondida = true;
                } else {
                    if (!preguntaRespondida) {
                        etRespuesta.setEnabled(false);
                        preguntaRespondida = true;
                        respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" incorrecta: "+0);
                    }
                }
                Intent intent = new Intent(this, Pregunta2.class);
                intent.putExtra("puntuacion", puntuacion);
                intent.putExtra("modo10", modo10);
                intent.putExtra("nombre", nombre);
                intent.putExtra("respuestasHistorial",respuestasHistorial);
                startActivity(intent);
            }


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
        bundle.putInt("valorPregunta", valorPregunta);
        fragmentProgress.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress, fragmentProgress).commit();
    }

}