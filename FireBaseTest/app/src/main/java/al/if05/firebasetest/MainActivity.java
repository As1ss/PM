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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTest = findViewById(R.id.btnTest);
        Button btnTest2 = findViewById(R.id.btnTest2);
        FirebaseApp.initializeApp(this);


        btnTest.setOnClickListener(v -> {
            //CREAR USUARIO
            Task<AuthResult> FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance().createUserWithEmailAndPassword("Alexis@gmail.com", "123456").addOnCompleteListener(command -> {

                if (command.isSuccessful()) {
                    Toast.makeText(this, "REGISTRO COMPLETADOFL", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "REGISTRO FAILEADO", Toast.LENGTH_SHORT).show();
                }

            });

            //LOGUEAR USUARIO
            Task<AuthResult> Fire = com.google.firebase.auth.FirebaseAuth.getInstance().signInWithEmailAndPassword("Alexis@gmail.com", "123456").addOnCompleteListener(command -> {
                if (command.isSuccessful()) {
                    Toast.makeText(this, "LOGUEADO", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "NO LOGUEADO", Toast.LENGTH_SHORT).show();
                }
            });

            //GUARDAR DATOS
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> data = new HashMap<>();
            List<Map> users = new ArrayList<>();

            data.put("name", "Alexis");
            data.put("age", 20);
            data.put("email", "alexis@hotmail.com");

            data.put("name", "Pedro");
            data.put("age", 30);
            data.put("email", "pedro@hotmail.com");

            data.put("name", "Juan");
            data.put("age", 40);
            data.put("email", "juan@hotmail.com");

            data.put("name", "Maria");
            data.put("age", 50);
            data.put("email", "maria@hotmail.com");

            data.put("name", "Luis");
            data.put("age", 60);
            data.put("email", "luis@hotmail.com");

            users.add(data);

            //GUARDAR DATOS de la lista en varios documentos 1 por usuario
            for (Map user : users) {
                db.collection("users").document(user.get("name").toString()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "GUARDADO", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "NO GUARDADO", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


            btnTest2.setOnClickListener(vi -> {
                FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                db2.collection("users").document("alexis").get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        int age = documentSnapshot.getLong("age").intValue();
                        String email = documentSnapshot.getString("email");
                        Toast.makeText(this, "Nombre: " + name + " Edad: " + age + " Email: " + email, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "NO EXISTE", Toast.LENGTH_SHORT).show();
                    }
                });

            });


        });
    }
}