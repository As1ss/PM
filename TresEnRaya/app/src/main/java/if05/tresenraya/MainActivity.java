package if05.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText etUsuario = findViewById(R.id.etUsuario);
        Button btnJugar = findViewById(R.id.btnJugar);



        btnJugar.setOnClickListener(view -> {
            if (etUsuario.length()<=2){
                Toast.makeText(this,"Por favor introduzca un usuario con almenos 2 caractéres",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Bienvenido "+etUsuario.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
            }

        });

    }
}