package al.if05.practica3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {

    static int contador;
    static int iterador;
    static int numMax;
    static int numMin;
    static String contadorString;
    static TextView tvContador;
    static TextView tvMaxValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContador = findViewById(R.id.tvContador);
        tvMaxValor = findViewById(R.id.tvMaxValor);
        EditText valorMax = findViewById(R.id.etMaxValor);
        Button buttonPlus = findViewById(R.id.btnSuma);
        Button buttonMinus = findViewById(R.id.btnRestar);
        ImageButton buttonSaveMaxValue = findViewById(R.id.btnGuardarValorMax);
        Switch swNegativos = findViewById(R.id.swNegativos);
        Switch swSumarDos = findViewById(R.id.swDosenDos);
        Chip chipTema1 = findViewById(R.id.cpTema1);
        Chip chipTema2 = findViewById(R.id.cpTema2);
        Chip chipTema3 = findViewById(R.id.cpTema3);
        Button resetValues = findViewById(R.id.btnReset);



        numMin = 0;
        iterador=1;
        contador=0;
        contadorString = String.valueOf(contador);

        resetValues.setOnClickListener(view -> {
            contador=0;
            numMax=0;
            tvMaxValor.setText("Max valor: "+numMax);
            valorMax.setText(String.valueOf(numMax));
            contadorString = String.valueOf(contador); // Actualiza contadorString
            tvContador.setText(contadorString);

            chipTema1.setChecked(true);
            swNegativos.setChecked(false);
            swSumarDos.setChecked(false);


        });

        chipTema1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                tvContador.setBackground(getDrawable(R.color.white));
                tvContador.setTextColor(getResources().getColor(R.color.black));
            }
        });

        chipTema2.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                tvContador.setBackground(getDrawable(R.color.black));
                tvContador.setTextColor(getResources().getColor(R.color.white));
            }
        });

        chipTema3.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                tvContador.setBackground(getDrawable(R.color.yellow));
                tvContador.setTextColor(getResources().getColor(R.color.pink));
            }
        });

        swSumarDos.setOnCheckedChangeListener((buttonView, isChecked)->{
            if (isChecked){
                iterador=2;
            }
            else{
                iterador=1;
            }

        });

        swNegativos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                numMin = -999;
            } else {
                numMin = 0;
            }
        });




        buttonSaveMaxValue.setOnClickListener(view -> {
            numMax = Integer.parseInt(String.valueOf(valorMax.getText()));
            Toast.makeText(this,"Valor máximo: "+ String.valueOf(numMax), Toast.LENGTH_SHORT).show();
            tvMaxValor.setText("Max valor: "+numMax);
        });


        buttonPlus.setOnClickListener(view -> {
            if (numMax > contador) {
                contador+=iterador;
                contadorString = String.valueOf(contador); // Actualiza contadorString
                tvContador.setText(contadorString);
            }
            if(numMax==contador){

                Intent intent = new Intent(getApplicationContext(), GanarActivity.class);

                startActivity(intent);
            }
        });
        buttonMinus.setOnClickListener(view -> {
            if (numMin < contador ) {
                contador-=iterador;
                contadorString = String.valueOf(contador); // Actualiza contadorString
                tvContador.setText(contadorString);
            }
        });




    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contador", contador);
        outState.putInt("numMax",numMax);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contador = savedInstanceState.getInt("contador");
        numMax = savedInstanceState.getInt("numMax");



        tvContador.setText(String.valueOf(contador));
        tvMaxValor.setText(String.valueOf(numMax));
    }


}