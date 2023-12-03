package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Pregunta2 extends AppCompatActivity implements Form{
    private final static int NUMEROPREGUNTA = 2;
    private final static int VALORPREGUNTA = 2;
    private int puntuacion;
    private int puntuacionMax;
    private boolean modo10;

    private boolean preguntaRespondida;
    private int valorPregunta;

    private Button btnSiguiente;
    private ImageButton btnBack;
    private RadioGroup rgPregunta2;

    private String nombre;
    private ArrayList<String> respuestasHistorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);
        ActivityManager.addActivity(this);

        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnBack = findViewById(R.id.btnBackPregunta2);
        rgPregunta2 = findViewById(R.id.rgPregunta2);
        puntuacion = getIntent().getExtras().getInt("puntuacion");
        modo10= getIntent().getExtras().getBoolean("modo10");
        nombre = getIntent().getExtras().getString("nombre");
        respuestasHistorial =getIntent().getExtras().getStringArrayList("respuestasHistorial");
        puntuacionMax = setMaxScore(modo10);
        valorPregunta = setCorrectAnswer(modo10);
        preguntaRespondida=false;



        btnSiguiente.setOnClickListener(v -> {
            int idSelected = rgPregunta2.getCheckedRadioButtonId();

            if (idSelected!=-1){
                RadioButton rbSelected= findViewById(idSelected);
                RadioButton rbCorrect = findViewById(R.id.rbRespuesta2);



                if (rbSelected.getId()==rbCorrect.getId() && !preguntaRespondida){

                    puntuacion+=setCorrectAnswer(modo10);
                    deshabilitarRadioGroup(rgPregunta2);
                    preguntaRespondida=true;
                    respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" correcta: "+valorPregunta);



                }
                else{
                    if(!preguntaRespondida){

                        puntuacion-=(setCorrectAnswer(modo10)/2);

                        if(puntuacion<0){
                            puntuacion=0;
                        }
                        deshabilitarRadioGroup(rgPregunta2);
                        preguntaRespondida=true;
                        respuestasHistorial.add("Pregunta "+NUMEROPREGUNTA+" incorrecta: -"+valorPregunta/2);

                    }

                }

            }
            else{
                Toast.makeText(this, "POR FAVOR SELECCIONE UNA RESPUESTA", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, Pregunta3.class);
            intent.putExtra("puntuacion", puntuacion);
            intent.putExtra("modo10",modo10);
            intent.putExtra("nombre",nombre);
            intent.putExtra("respuestasHistorial",respuestasHistorial);


            startActivity(intent);

        });
        btnBack.setOnClickListener(view -> {
            this.onBackPressed();
        });

        initFragment();

    }

    @Override
    public int setMaxScore(boolean mode) {
        if (mode){
            return 10;
        }
        else{
            return 100;
        }
    }

    @Override
    public int setCorrectAnswer(boolean mode) {
        if(mode){
            return VALORPREGUNTA;
        }
        else{
            return VALORPREGUNTA*10;
        }
    }

    @Override
    public void initFragment() {
        FragmentProgreso fragmentProgreso = new FragmentProgreso();
        Bundle bundle = new Bundle();
        bundle.putInt("valorPregunta", valorPregunta);
        bundle.putInt("NUMEROPREGUNTA", NUMEROPREGUNTA);
        bundle.putInt("puntuacionMax",puntuacionMax);
        fragmentProgreso.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress2, fragmentProgreso).commit();
    }
    private void deshabilitarRadioGroup(RadioGroup rgPregunta2) {
        for (int i = 0; i < rgPregunta2.getChildCount(); i++) {
            rgPregunta2.getChildAt(i).setEnabled(false);
        }
    }
}