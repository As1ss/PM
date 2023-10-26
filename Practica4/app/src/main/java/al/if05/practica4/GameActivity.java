package al.if05.practica4;

import androidx.annotation.NonNull;
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
    static Button btnTirarDado;
    static Button btnReinicio;
    static RadioButton opc6dados;
    static RadioButton opc8dados;
    static TextView ventanaDados;
    static TextView tvFraseResultado;
    static TextView tvDadoValor;
    static int sumaTiradas;

    static int numTiradas;
    static int sumaDado4;
    static int numTiradas4;
    static int numTiradas6;
    static int numTiradas8;
    static int sumaDado6;
    static int sumaDado8;
    static String userName;
    static int ultimaTirada;
    static int ultimoDado;


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
        btnTirarDado = findViewById(R.id.btnTirarDado);
        btnReinicio = findViewById(R.id.btnReinicio);
        userName = getIntent().getExtras().get("userName").toString();


        Runnable checkCondition = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (sumaTiradas >= 24) {
                        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                        intent.putExtra("sumaTiradas", sumaTiradas);
                        intent.putExtra("numTiradas", numTiradas);
                        intent.putExtra("numTiradas4", numTiradas4);
                        intent.putExtra("numTiradas6", numTiradas6);
                        intent.putExtra("numTiradas8", numTiradas8);
                        intent.putExtra("sumaDado4", sumaDado4);
                        intent.putExtra("sumaDado6", sumaDado6);
                        intent.putExtra("sumaDado8", sumaDado8);
                        intent.putExtra("userName", userName);
                        startActivity(intent);
                        finish();
                        break;
                    }
                    try {
                        Thread.sleep(1000); // Espera 1 segundo antes de la próxima comprobación
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread hilo = new Thread(checkCondition);
        hilo.start();



        comprobarDadosRadio();


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sumaTiradas",sumaTiradas);
        outState.putInt("numTiradas", numTiradas);
        outState.putInt("numTiradas4", numTiradas4);
        outState.putInt("numTiradas6", numTiradas6);
        outState.putInt("numTiradas8", numTiradas8);
        outState.putInt("sumaDado4", sumaDado4);
        outState.putInt("sumaDado6", sumaDado6);
        outState.putInt("sumaDado8", sumaDado8);
        outState.putInt("ultimaTirada",ultimaTirada);
        outState.putInt("ultimoDado",ultimoDado);
        outState.putString("userName", userName);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userName = savedInstanceState.getString("userName");
        sumaTiradas = savedInstanceState.getInt("sumaTiradas");
        numTiradas = savedInstanceState.getInt("numTiradas");
        numTiradas4 = savedInstanceState.getInt("numTiradas4");
        numTiradas6 = savedInstanceState.getInt("numTiradas6");
        numTiradas8 = savedInstanceState.getInt("numTiradas8");
        sumaDado4 = savedInstanceState.getInt("sumaDado4");
        sumaDado6 = savedInstanceState.getInt("sumaDado6");
        sumaDado8 = savedInstanceState.getInt("sumaDado8");
        ultimaTirada = savedInstanceState.getInt("ultimaTirada");
        ultimoDado = savedInstanceState.getInt("ultimoDado");
        ventanaDados.setText(String.valueOf(sumaTiradas));
        tvFraseResultado.setText("Dado de "+ultimoDado+" de caras saca un ");
        tvDadoValor.setText(String.valueOf(ultimaTirada));



    }

    public static int getRandomNumber(int min, int max) {
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



    public void tirarDado(View view) {
        numTiradas++;

        if (opc4dados.isChecked()) {
            numTiradas4++;
            int numRandom = getRandomNumber(1, 4);
            sumaDado4 += numRandom;
            sumaTiradas += numRandom;
            ventanaDados.setText(String.valueOf(sumaTiradas));
            tvFraseResultado.setText("Dado de 4 caras saca un ");
            tvDadoValor.setText(String.valueOf(numRandom));
            ultimoDado=4;
            ultimaTirada=numRandom;
        }
        if (opc6dados.isChecked()) {
            numTiradas6++;
            int numRandom = getRandomNumber(1, 6);
            sumaDado6 += numRandom;
            sumaTiradas += numRandom;
            ventanaDados.setText(String.valueOf(sumaTiradas));
            tvFraseResultado.setText("Dado de 6 caras saca un ");
            tvDadoValor.setText(String.valueOf(numRandom));
            ultimoDado=6;
            ultimaTirada=numRandom;
        }
        if (opc8dados.isChecked()) {
            numTiradas8++;
            int numRandom = getRandomNumber(1, 8);
            sumaDado8 += numRandom;
            sumaTiradas += numRandom;
            ventanaDados.setText(String.valueOf(sumaTiradas));
            tvFraseResultado.setText("Dado de 8 caras saca un ");
            tvDadoValor.setText(String.valueOf(numRandom));
            ultimoDado=8;
            ultimaTirada=numRandom;
        }
    }



}