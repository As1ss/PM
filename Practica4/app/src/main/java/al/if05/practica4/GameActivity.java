package al.if05.practica4;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends AppCompatActivity {
    static RadioButton opc4dados;
    static RadioButton opc6dados;
    static RadioButton opc8dados;
    static TextView ventanaDados;
    static TextView tvFraseResultado;
    static TextView tvDadoValor;
    static int sumaTiradas;
    static int tiradas4[];
    static int tiradas6[];
    static int tiradas8[];
    static int sumaDado4;
    static int sumaDado6;
    static int sumaDado8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        opc4dados = findViewById(R.id.opcDado4);
        opc6dados = findViewById(R.id.opcDado6);
        opc8dados = findViewById(R.id.opcDado8);
        ventanaDados = findViewById(R.id.tvVentanaDados);
        tvFraseResultado = findViewById(R.id.tvFraseResultado);
        tvDadoValor = findViewById(R.id.tvDadoValor);
        Button btnTirarDado = findViewById(R.id.btnTirarDado);
        String userName = getIntent().getExtras().get("userName").toString();












        btnTirarDado.setOnClickListener(view -> {
            if (sumaTiradas>=24){
                Intent intent = new Intent(this, GameOverActivity.class);
                startActivity(intent);
            }
            if (opc4dados.isChecked()) {
                int numRandom = getRandomNumber(1, 4);
                sumaDado4 += numRandom;
                sumaTiradas += numRandom;
                ventanaDados.setText(String.valueOf(sumaTiradas));
                tvFraseResultado.setText("Dado de 4 caras saca un ");
                tvDadoValor.setText(String.valueOf(numRandom));
            }
            if (opc6dados.isChecked()) {
                int numRandom = getRandomNumber(1, 6);
                sumaDado6 += numRandom;
                sumaTiradas += numRandom;
                ventanaDados.setText(String.valueOf(sumaTiradas));
                tvFraseResultado.setText("Dado de 6 caras saca un ");
                tvDadoValor.setText(String.valueOf(numRandom));
            }
            if (opc8dados.isChecked()) {
                int numRandom = getRandomNumber(1,8);
                sumaDado8+=numRandom;
                sumaTiradas += numRandom;
                ventanaDados.setText(String.valueOf(sumaTiradas));
                tvFraseResultado.setText("Dado de 8 caras saca un ");
                tvDadoValor.setText(String.valueOf(numRandom));
            }
        });





        Toast.makeText(this, "HOLAS" + userName, Toast.LENGTH_SHORT).show();
        comprobarDadosRadio();


    }


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min + 1)) + min);
    }

    private void comprobarDadosRadio() {
        opc4dados.setOnClickListener(view -> {
            opc6dados.setChecked(false);
            opc8dados.setChecked(false);
        });

        opc6dados.setOnClickListener(view -> {
            opc4dados.setChecked(false);
            opc8dados.setChecked(false);
        });

        opc8dados.setOnClickListener(view -> {
            opc4dados.setChecked(false);
            opc6dados.setChecked(false);
        });
    }

    public void comprobarDadosImagen(View view) {
        if (view.getId() == R.id.ivDado4) {
            opc4dados.setChecked(true);
            opc6dados.setChecked(false);
            opc8dados.setChecked(false);

        }
        if (view.getId() == R.id.ivDado6) {
            opc6dados.setChecked(true);
            opc4dados.setChecked(false);
            opc8dados.setChecked(false);
        }
        if (view.getId() == R.id.ivDado8) {
            opc8dados.setChecked(true);
            opc4dados.setChecked(false);
            opc6dados.setChecked(false);
        }
    }
}