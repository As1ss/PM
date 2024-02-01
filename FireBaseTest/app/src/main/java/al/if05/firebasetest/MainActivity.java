package al.if05.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTest = findViewById(R.id.btnTest);
        FirebaseApp.initializeApp(this);


        btnTest.setOnClickListener(v -> {
            Task<AuthResult> FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance().createUserWithEmailAndPassword("Alexis@gmail.com", "123456").addOnCompleteListener(command -> {

                if (command.isSuccessful()) {
                    Toast.makeText(this, "REGISTRO COMPLETADOFL", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "REGISTRO FAILEADO", Toast.LENGTH_SHORT).show();
                }
            });

        });

    }
}