package al.if05.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int contador;
    static int numMax;
    static int numMin;
    static String contadorString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText valorMax = findViewById(R.id.etMaxValor);




        numMax = Integer.parseInt(String.valueOf(valorMax.getText()));



        contador = 0;
        contadorString = String.valueOf(contador);


        Button buttonPlus = findViewById(R.id.btnSuma);
        Button buttonMinus = findViewById(R.id.btnRestar);
        TextView tvContador = findViewById(R.id.tvContador);



        buttonPlus.setOnClickListener(view -> {


            if(numMax>contador){
                contador++;
                contadorString = String.valueOf(contador); // Actualiza contadorString
                tvContador.setText(contadorString);
            }



                Toast.makeText(this,String.valueOf(numMax),Toast.LENGTH_SHORT).show();
        });



    }
}