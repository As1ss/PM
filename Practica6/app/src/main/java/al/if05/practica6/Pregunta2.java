package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Pregunta2 extends AppCompatActivity implements Form{
    private final static int NUMEROPREGUNTA = 2;
    private final static int VALORPREGUNTA = 2;
    private int puntuacion;
    private int puntuacionMax;
    boolean modo10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);



        Button btnSiguiente = findViewById(R.id.btnSiguiente);
        RadioGroup rgPregunta2 = findViewById(R.id.rgPregunta2);
        puntuacion = getIntent().getExtras().getInt("puntuacion");
        modo10= getIntent().getExtras().getBoolean("modo10");
        puntuacionMax = setMaxScore(modo10);

        rgPregunta2.setFocusable(false);

        btnSiguiente.setOnClickListener(v -> {
            int idSelected = rgPregunta2.getCheckedRadioButtonId();

            if (idSelected!=-1){
                RadioButton rbSelected= findViewById(idSelected);
                RadioButton rbCorrect = findViewById(R.id.rbRespuesta2);



                if (rbSelected.getId()==rbCorrect.getId()){
                    Toast.makeText(this, "RESPUESTA CORRECTA", Toast.LENGTH_SHORT).show();
                    puntuacion+=setCorrectAnswer(modo10);


                }
                else{
                    Toast.makeText(this,"RESPUESTA INCORRECTA",Toast.LENGTH_SHORT).show();
                    puntuacion-=(setCorrectAnswer(modo10)/2);
                }

            }
            else{
                Toast.makeText(this, "POR FAVOR SELECCIONE UNA RESPUESTA", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, Pregunta3.class);
            intent.putExtra("puntuacion", puntuacion);
            intent.putExtra("modo10",modo10);

            startActivity(intent);

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
        bundle.putInt("puntuacion", puntuacion);
        bundle.putInt("NUMEROPREGUNTA", NUMEROPREGUNTA);
        bundle.putInt("puntuacionMax",puntuacionMax);
        fragmentProgreso.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress2, fragmentProgreso).commit();
    }
}