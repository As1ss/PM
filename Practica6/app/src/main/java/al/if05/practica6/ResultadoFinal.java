package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultadoFinal extends AppCompatActivity implements Form{
    private int puntuacion;
    private TextView tvResultado;
    private LinearLayout layout;
    private String nombre;
    private boolean modo10;
    private int puntuacionMax;
    private ArrayList<String> respuestasHistorial;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);
        puntuacion= getIntent().getExtras().getInt("puntuacion");
        nombre = getIntent().getExtras().getString("nombre");
        modo10= getIntent().getExtras().getBoolean("modo10");
        respuestasHistorial=getIntent().getExtras().getStringArrayList("respuestasHistorial");
        puntuacionMax = setMaxScore(modo10);
        tvResultado = findViewById(R.id.tvResultado);
        layout= findViewById(R.id.lyFinalActivity);
        createButton();


        for(int i = 0;i<respuestasHistorial.size();i++){
            TextView tvRespuesta = new TextView(this);
            tvRespuesta.setText(respuestasHistorial.get(i));
            tvRespuesta.setTextSize(25f);
            tvRespuesta.setGravity(Gravity.CENTER);


            layout.addView(tvRespuesta);

        }

        layout.addView(btnContinuar);



        tvResultado.setText(mostrarMensaje(puntuacion));

        btnContinuar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }

    private void createButton() {
        btnContinuar =new Button(this);
        btnContinuar.setTextSize(25f);
        // Asegúrate de tener un método setCornerRadius definido o utiliza otras técnicas para redondear las esquinas del botón
        btnContinuar.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
        btnContinuar.setText("Continuar");
        btnContinuar.setTextColor(Color.WHITE);
        //Creamos un objeto LayoutParams para agregar margenes y ancho alto
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,  // Ancho
                ViewGroup.LayoutParams.WRAP_CONTENT   // Alto
        );
        layoutParams.setMargins(0, 180, 0, 0);
        btnContinuar.setLayoutParams(layoutParams);
    }

    private String mostrarMensaje(int puntuacion){
       switch (puntuacion){
           case 5:
           case 50:
               return "¡Enhorabuena "+nombre+" has aprobado! Nota: "+puntuacion+"/"+puntuacionMax;
           case 6:
           case 60:
           case 7:
           case 70:
               return "!Enhorabuena "+nombre+" has sacado un notable! Nota: "+puntuacion+"/"+puntuacionMax;
           case 8:
           case 80:
           case 9:
           case 90:
               return "!Enhorabuena "+nombre+" has sacado un sobresaliente! Nota: "+puntuacion+"/"+puntuacionMax;
           case 10:
           case 100:
               return "!Enhorabuena "+nombre+" has sacado matrícula de honor! Nota: "+puntuacion+"/"+puntuacionMax;
           default:
               return "Lo lamento "+nombre+" has suspendido. Nota: "+puntuacion+"/"+puntuacionMax;



       }
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
            return puntuacion;
        }
        else{
            return puntuacion*10;
        }
    }

    @Override
    public void initFragment() {

    }
}