package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class Pregunta5 extends AppCompatActivity implements Form {
    private final static int NUMEROPREGUNTA = 5;
    private final static int VALORPREGUNTA = 2;
    private int valorPregunta;
    private int puntuacion;
    private int puntuacionMax;
    private boolean modo10;
    private boolean preguntaRespondida;
    private Button btnFinalizar;
    private CheckBox cbResp1,cbResp2,cbResp3,cbResp4;
    private String nombre;
    private ArrayList<String> respuestasHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);


        setContentView(R.layout.activity_pregunta5);
        puntuacion = getIntent().getExtras().getInt("puntuacion");
        modo10 = getIntent().getExtras().getBoolean("modo10");
        nombre = getIntent().getExtras().getString("nombre");
        respuestasHistorial=getIntent().getExtras().getStringArrayList("respuestasHistorial");

        puntuacionMax=setMaxScore(modo10);
        valorPregunta = setCorrectAnswer(modo10);
        preguntaRespondida=false;
        btnFinalizar= findViewById(R.id.btnFinalizarForm);
        cbResp1 = findViewById(R.id.cbRespuesta1);
        cbResp2 = findViewById(R.id.cbRespuesta2);
        cbResp3 = findViewById(R.id.cbRespuesta3);
        cbResp4 = findViewById(R.id.cbRespuesta4);




        btnFinalizar.setOnClickListener(view -> {
            if (!cbResp1.isChecked() && !cbResp2.isChecked() && !cbResp3.isChecked() && !cbResp4.isChecked()){
                Toast.makeText(this, "POR FAVOR SELECCIONA UNA RESPUESTA", Toast.LENGTH_SHORT).show();
            }
            else{
                if (cbResp1.isChecked() && cbResp3.isChecked() && !preguntaRespondida){
                    puntuacion+=setCorrectAnswer(modo10);
                    preguntaRespondida=true;
                    respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" correcta: "+valorPregunta);
                }
                else{
                    preguntaRespondida=true;
                    respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" incorrecta: "+0);
                }
            }
            Intent intent = new Intent(this,ResultadoFinal.class);
            intent.putExtra("puntuacion",puntuacion);
            intent.putExtra("modo10",modo10);
            intent.putExtra("nombre",nombre);
            intent.putExtra("respuestasHistorial",respuestasHistorial);
            startActivity(intent);
            ActivityManager.finishActivities();


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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress5, fragmentProgreso).commit();
    }
}