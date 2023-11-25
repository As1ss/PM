package al.if05.testfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvHelloWOrld = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);
        String texto = tvHelloWOrld.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("textoHolaMundo",texto);

        button.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                    add(R.id.fragmentContainerView2, Fragmento1.class,bundle).commit();
            Toast.makeText(this,"HOla", Toast.LENGTH_SHORT).show();
        });




    }
}