package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class Pregunta4 extends AppCompatActivity implements Form {

    private final static int NUMEROPREGUNTA = 4;
    private final static int VALORPREGUNTA = 2;
    private int puntuacion;
    private int puntuacionMax;
    private boolean modo10;

    private int valorPregunta;
    private boolean preguntaRespondida;
    private RadioButton resp1, resp2, resp3, resp4;
    private Button btnSiguiente;
    private String nombre;
    private ArrayList<String> respuestasHistorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta4);
        ActivityManager.addActivity(this);


        resp1 = findViewById(R.id.rbRespuesta1);
        resp2 = findViewById(R.id.rbRespuesta2);
        resp3 = findViewById(R.id.rbRespuesta3);
        resp4 = findViewById(R.id.rbRespuesta4);
        puntuacion = getIntent().getExtras().getInt("puntuacion");
        modo10 = getIntent().getExtras().getBoolean("modo10");
        nombre = getIntent().getExtras().getString("nombre");
        respuestasHistorial=getIntent().getExtras().getStringArrayList("respuestasHistorial");
        preguntaRespondida = false;
        puntuacionMax = setMaxScore(modo10);
        valorPregunta = setCorrectAnswer(modo10);
        btnSiguiente = findViewById(R.id.btnSiguiente);




        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    actualizarUI();
                }

            }
        });



        hilo.start();

        btnSiguiente.setOnClickListener(view -> {


            if (!resp1.isChecked() && !resp2.isChecked() && !resp3.isChecked() && !resp4.isChecked()) {
                Toast.makeText(this, "ESCOGE UNA OPCION", Toast.LENGTH_SHORT).show();
            } else {
                if (resp3.isChecked() && !preguntaRespondida) {

                    puntuacion+=setCorrectAnswer(modo10);
                    resp1.setEnabled(false);
                    resp2.setEnabled(false);
                    resp3.setEnabled(false);
                    resp4.setEnabled(false);
                    preguntaRespondida=true;
                    respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" correcta: "+valorPregunta);
                } else {

                    resp1.setEnabled(false);
                    resp2.setEnabled(false);
                    resp3.setEnabled(false);
                    resp4.setEnabled(false);
                    preguntaRespondida=true;
                    respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" incorrecta: "+0);
                }
            }
            Intent intent = new Intent(this,Pregunta5.class);
            intent.putExtra("puntuacion",puntuacion);
            intent.putExtra("modo10",modo10);
            intent.putExtra("nombre",nombre);
            intent.putExtra("respuestasHistorial",respuestasHistorial);
            startActivity(intent);
        });


        initFragment();
    }


    private void actualizarUI() {


        if (resp3.isChecked()) {
            resp3.setScaleX(1.2f);
            resp2.setChecked(false);
            resp1.setChecked(false);
            resp4.setChecked(false);
        } else if (!resp3.isChecked()) {
            resp3.setScaleX(1);
        }

        if (resp4.isChecked()) {
            resp4.setScaleX(1.2f);
            resp2.setChecked(false);
            resp3.setChecked(false);
            resp1.setChecked(false);
        } else if (!resp4.isChecked()) {
            resp4.setScaleX(1);
        }

        if (resp1.isChecked()) {
            resp1.setScaleX(1.2f);
            resp2.setChecked(false);
            resp3.setChecked(false);
            resp4.setChecked(false);
        } else if (!resp1.isChecked()) {
            resp1.setScaleX(1);
        }

        if (resp2.isChecked()) {
            resp2.setScaleX(1.2f);
            resp1.setChecked(false);
            resp3.setChecked(false);
            resp4.setChecked(false);
        } else if (!resp2.isChecked()) {
            resp2.setScaleX(1);
        }
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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress4, fragmentProgreso).commit();
    }
}
