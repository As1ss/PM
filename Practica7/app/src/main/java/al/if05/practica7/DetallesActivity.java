package al.if05.practica7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

public class DetallesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Button btnVolver = findViewById(R.id.btnVolver);
        FragmentPelicula fragmentPelicula = new FragmentPelicula();
        Bundle bundle = new Bundle();
        Pelicula pelicula = (Pelicula) getIntent().getExtras().getSerializable("pelicula");
        bundle.putSerializable("pelicula",pelicula);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, FragmentPelicula.class,bundle).commit();



       btnVolver.setOnClickListener(view -> {
           onBackPressed();
       });
    }


}