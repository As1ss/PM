package al.if05.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSiguiente = findViewById(R.id.btnEntrar);
        EditText etUsuario = findViewById(R.id.etUsuario);

        btnSiguiente.setOnClickListener(view -> {
            if (etUsuario.length()>2){
                Toast.makeText(this,"LOGEO CORERCTO",Toast.LENGTH_SHORT).show();
                this.finish();
            }
            else {

            }
        });

    }
}