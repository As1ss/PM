package al.if05.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int contador;
    static int iterador;
    static int numMax;
    static int numMin;
    static String contadorString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvContador = findViewById(R.id.tvContador);
        EditText valorMax = findViewById(R.id.etMaxValor);
        Button buttonPlus = findViewById(R.id.btnSuma);
        Button buttonMinus = findViewById(R.id.btnRestar);
        ImageButton buttonSaveMaxValue = findViewById(R.id.btnGuardarValorMax);
        Switch swNegativos = findViewById(R.id.swNegativos);
        Switch swSumarDos = findViewById(R.id.swDosenDos);



        numMin = 0;
        iterador=1;
        contador=0;
        contadorString = String.valueOf(contador);


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
            Toast.makeText(this, String.valueOf(numMax), Toast.LENGTH_SHORT).show();
        });


        buttonPlus.setOnClickListener(view -> {
            if (numMax > contador) {
                contador+=iterador;
                contadorString = String.valueOf(contador); // Actualiza contadorString
                tvContador.setText(contadorString);
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
}