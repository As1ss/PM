package if05.tresenraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText etUsuario = findViewById(R.id.etUsuario);
        Button btnJugar = findViewById(R.id.btnJugar);







        btnJugar.setOnClickListener(view -> {
            userName = etUsuario.getText().toString();
            if (userName.length()>2){
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
                finish();

            }
            else{
                Toast.makeText(this,"Por favor introduzca un usuario con almenos 2 caractéres",Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("userName",userName);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.get("userName");
    }
}