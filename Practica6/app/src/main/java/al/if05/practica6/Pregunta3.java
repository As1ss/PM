package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Pregunta3 extends AppCompatActivity implements Form{

    private final static int NUMEROPREGUNTA =3;
    private final static int VALORPREGUNTA = 1;
    private int puntuacionMax;
    private int puntuacion;
    private boolean modo10;
    private boolean preguntaRespondida;
    String[] respuestas;
    ArrayAdapter<String> arrayAdapter;
    Button btnSiguiente;
    Spinner spRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta3);

        spRespuesta = findViewById(R.id.spRespuestas);

        btnSiguiente=  findViewById(R.id.btnSiguiente);
        respuestas = getResources().getStringArray(R.array.respuestasPregunta3);
        arrayAdapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, respuestas);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spRespuesta.setAdapter(arrayAdapter);
        puntuacion=getIntent().getExtras().getInt("puntuacion");
        modo10= getIntent().getExtras().getBoolean("modo10");
        puntuacionMax =setMaxScore(modo10);

        preguntaRespondida=false;


        btnSiguiente.setOnClickListener(view -> {
           String itemSelected = spRespuesta.getSelectedItem().toString();

           if (itemSelected.equalsIgnoreCase("")){
               Toast.makeText(this,"SELECCIONA UNA RESPUESTA",Toast.LENGTH_SHORT).show();

           }
           else{
               if (itemSelected.equalsIgnoreCase("Respuesta 4") && !preguntaRespondida){
                   puntuacion+=setCorrectAnswer(modo10);
                   preguntaRespondida=true;
                   Toast.makeText(this,"PUNTUACION: "+puntuacion,Toast.LENGTH_SHORT).show();
                   spRespuesta.setEnabled(false);
               }
               else{
                   if (!preguntaRespondida){
                       preguntaRespondida=true;
                       Toast.makeText(this,"PUNTUACION: "+puntuacion,Toast.LENGTH_SHORT).show();
                       spRespuesta.setEnabled(false);
                   }



               }

               Intent intent  = new Intent(this,Pregunta4.class);
               intent.putExtra("puntuacion",puntuacion);
               intent.putExtra("modo10",modo10);
               startActivity(intent);
           }


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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentProgress3, fragmentProgreso).commit();
    }
}