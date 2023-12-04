package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Pregunta3 extends AppCompatActivity implements Form {

    private final static int NUMEROPREGUNTA = 3;
    private final static int VALORPREGUNTA = 1;
    private int valorPregunta;
    private int puntuacionMax;
    private int puntuacion;
    private boolean modo10;
    private boolean preguntaRespondida;
    private String[] respuestas;
    private ArrayAdapter<String> arrayAdapter;
    private Button btnSiguiente;
    private ImageButton btnBack;
    private Spinner spRespuesta;
    private String nombre;
    private ArrayList<String> respuestasHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta3);
        ActivityManager.addActivity(this);

        spRespuesta = findViewById(R.id.spRespuestas);
        spRespuesta.setScrollBarSize(5);

        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnBack = findViewById(R.id.btnBackPregunta3);
        respuestas = getResources().getStringArray(R.array.respuestasPregunta3);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, respuestas);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spRespuesta.setAdapter(arrayAdapter);
        puntuacion = getIntent().getExtras().getInt("puntuacion");
        modo10 = getIntent().getExtras().getBoolean("modo10");
        nombre = getIntent().getExtras().getString("nombre");
        respuestasHistorial = getIntent().getExtras().getStringArrayList("respuestasHistorial");
        puntuacionMax = setMaxScore(modo10);
        valorPregunta = setCorrectAnswer(modo10);

        preguntaRespondida = false;


        btnSiguiente.setOnClickListener(view -> {
            String itemSelected = spRespuesta.getSelectedItem().toString();
            String[] respuestasPregunta3 = getResources().getStringArray(R.array.respuestasPregunta3);
            String respuestaCorrecta = respuestasPregunta3[4];

            if (itemSelected.equalsIgnoreCase("")) {
                Toast.makeText(this, "SELECCIONA UNA RESPUESTA", Toast.LENGTH_SHORT).show();

            } else {
                if (itemSelected.equalsIgnoreCase(respuestaCorrecta) && !preguntaRespondida) {
                    puntuacion += setCorrectAnswer(modo10);
                    preguntaRespondida = true;

                    spRespuesta.setEnabled(false);
                    respuestasHistorial.add("Pregunta " + NUMEROPREGUNTA + " correcta: " + valorPregunta);
                } else {
                    if (!preguntaRespondida) {
                        preguntaRespondida = true;
                        spRespuesta.setEnabled(false);
                        respuestasHistorial.add("Pregunta " + NUMEROPREGUNTA + " incorrecta: " + 0+"\nLa respuesta correcta era: "+respuestaCorrecta);
                    }


                }

                Intent intent = new Intent(this, Pregunta4.class);
                intent.putExtra("puntuacion", puntuacion);
                intent.putExtra("modo10", modo10);
                intent.putExtra("nombre", nombre);
                intent.putExtra("respuestasHistorial", respuestasHistorial);


                startActivity(intent);
            }


        });


        btnBack.setOnClickListener(view -> {
            this.onBackPressed();
        });


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
        bundle.putInt("valorPregunta", valorPregunta);
        bundle.putInt("NUMEROPREGUNTA", NUMEROPREGUNTA);
        bundle.putInt("puntuacionMax", puntuacionMax);
        fragmentProgreso.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress3, fragmentProgreso).commit();
    }
}