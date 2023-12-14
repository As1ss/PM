package al.if05.practica7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetallesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        FragmentPelicula fragmentPelicula = new FragmentPelicula();
        Bundle bundle = new Bundle();
       
    }
}