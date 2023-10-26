package al.if05.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class GameActivity extends AppCompatActivity {
    static RadioButton opc4dados;
    static RadioButton opc6dados;
    static RadioButton opc8dados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
         opc4dados = findViewById(R.id.opcDado4);
         opc6dados = findViewById(R.id.opcDado6);
         opc8dados = findViewById(R.id.opcDado8);


         comprobarDadosRadio();



    }



    private void comprobarDadosRadio(){
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
        if (view.getId()==R.id.ivDado4){
            opc4dados.setChecked(true);
            opc6dados.setChecked(false);
            opc8dados.setChecked(false);

        }
        if (view.getId()==R.id.ivDado6){
            opc6dados.setChecked(true);
            opc4dados.setChecked(false);
            opc8dados.setChecked(false);
        }
        if(view.getId()==R.id.ivDado8){
            opc8dados.setChecked(true);
            opc4dados.setChecked(false);
            opc6dados.setChecked(false);
        }
    }
}