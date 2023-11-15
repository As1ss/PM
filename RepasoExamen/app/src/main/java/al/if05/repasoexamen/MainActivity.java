package al.if05.repasoexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etUsuario = findViewById(R.id.editTextText);
        Button btnEntrar = findViewById(R.id.btEntrar);

        btnEntrar.setOnClickListener(view -> {
            String userName = etUsuario.getText().toString();
            Intent intent = new Intent(this,GameActivity.class);
            intent.putExtra("userName",userName);

            startActivity(intent);

        });
    }
}