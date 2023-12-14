package al.if05.practica7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layoutP1 = findViewById(R.id.lyGeneral);

        String[] actoresAbiertoAlAmanecer = {"George Clooney", "Quentin Tarantino", "Harvey Keitel", "Juliette Lewis"};
        Pelicula p1 = new Pelicula();
        p1.setTitulo(getString(R.string.tvTitlePelicula0));
        p1.setDirector("Robert Rodriguez");
        p1.setAno("1996");
        p1.setActores(actoresAbiertoAlAmanecer);
        p1.setSinopsis("Sinospis de Abierto al amanecer");




        String[] actoresElDiaDeLaBestia = {"George Clooney", "Quentin Tarantino", "Harvey Keitel", "Juliette Lewis"};
        Pelicula p2 = new Pelicula();
        p2.setTitulo("El Día de la Bestia");
        p2.setDirector("Alex de la Iglesia");
        Pelicula p3 = new Pelicula();
        Pelicula p4 = new Pelicula();

        Intent intent = new Intent();
        Bundle bunlde = new Bundle();



        LinearLayout lyPelicula = (LinearLayout) layoutP1.getChildAt(0);

       lyPelicula.setOnClickListener(v -> {


       });


    }
}