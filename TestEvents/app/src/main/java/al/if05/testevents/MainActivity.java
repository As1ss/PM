package al.if05.testevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.button);
        TextView text = findViewById(R.id.texto);


        //Método listener con expresion lambda
        boton.setOnClickListener(view -> {
            Toast.makeText(this, "HOLA BOTBERTO", Toast.LENGTH_SHORT).show();
            text.setText("AHORA HA CAMBIADOLF");
        });
/*
  //Método listener instanciando una clase anónima
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
 */


    }
}

/*
 //Método OnClick declarado en el documento XML
    public void salute(View view) {
        if (view instanceof Button) {
            Toast.makeText(this, "HAS PULSADO BOTON", Toast.LENGTH_SHORT).show();
        }
        if (view instanceof TextView) {
            Toast.makeText(this, "HAS PULSADO EL TEXTO", Toast.LENGTH_SHORT).show();
        }
    }
}
 */
