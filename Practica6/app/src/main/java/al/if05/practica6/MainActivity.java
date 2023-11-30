package al.if05.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup rgOpciones = findViewById(R.id.rgOpciones);
        EditText etNombre = findViewById(R.id.etNombre);
        Button btnEmpezar  = findViewById(R.id.btnEmpezar);
        String nombre = etNombre.toString();
        Boolean modo10;


        btnEmpezar.setOnClickListener(view -> {
          int selectedID = rgOpciones.getCheckedRadioButtonId();
          if (selectedID!=-1){
              RadioButton selectedRadioButton = findViewById(selectedID);
              String selectedText = selectedRadioButton.getText().toString();

              Toast.makeText(this,"Has selecionado: "+selectedText,Toast.LENGTH_SHORT).show();
          }
          else{
              Toast.makeText(this,"Por favor selecciona un modo de puntuación",Toast.LENGTH_SHORT).show();
          }
        });



    }
}