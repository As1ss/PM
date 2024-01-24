package al.if05.practica7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

public class DetallesActivity extends AppCompatActivity implements FragmentPelicula.onFragmentInteractListener{

    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Button btnVolver = findViewById(R.id.btnVolver);
        FragmentPelicula fragmentPelicula = new FragmentPelicula();
        Bundle bundle = new Bundle();
        Pelicula pelicula = (Pelicula) getIntent().getExtras().getSerializable("pelicula");
        bundle.putSerializable("pelicula",pelicula);
        fragmentPelicula.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragmentPelicula).commit();




       btnVolver.setOnClickListener(view -> {
           onBackPressed();
           finish();

       });
    }


    @Override
    public void actualizarPuntuaciones(String titulo,int puntuacion) {
        intent.putExtra("tituloFragment",titulo);
        intent.putExtra("puntuacionDesdeFragmento", puntuacion);
        setResult(RESULT_OK, intent);

    }
}