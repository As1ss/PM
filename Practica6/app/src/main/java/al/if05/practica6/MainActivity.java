package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static boolean modo10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup rgOpciones = findViewById(R.id.rgOpciones);
        EditText etNombre = findViewById(R.id.etNombre);
        Button btnEmpezar = findViewById(R.id.btnEmpezar);





        btnEmpezar.setOnClickListener(view -> {
            String nombre = etNombre.getText().toString();

            int selectedID = rgOpciones.getCheckedRadioButtonId();
            if (selectedID != -1) {
                RadioButton selectedRadioButton = findViewById(selectedID);

                if (nombre.equals("")){
                    Toast.makeText(this, "Introduce un nombre por favor", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(this, Pregunta1.class);
                    if (selectedRadioButton.getId() == R.id.rbSobre10) {
                        modo10 = true;

                    }
                    else{
                        modo10=false;

                    }
                    intent.putExtra("modo10",modo10);
                    intent.putExtra("nombre",nombre);
                    startActivity(intent);
                    this.finish();

                }


            }
            else {
                Toast.makeText(this, "Por favor selecciona un modo de puntuación", Toast.LENGTH_SHORT).show();
            }
        });


    }
}