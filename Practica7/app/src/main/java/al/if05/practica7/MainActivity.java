package al.if05.practica7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layoutP0 = findViewById(R.id.lyP0);
        LinearLayout layoutP1 = findViewById(R.id.lyP1);
        LinearLayout layoutP2 = findViewById(R.id.lyP2);
        LinearLayout layoutP3 = findViewById(R.id.lyP3);

        TextView tvP0 = findViewById(R.id.tvTituloP0);
        RatingBar rbP0 = findViewById(R.id.rbP0);

        TextView tvP1 = findViewById(R.id.tvTituloP1);
        RatingBar rbP1 = findViewById(R.id.rbP1);

        TextView tvP2 = findViewById(R.id.tvTituloP2);
        RatingBar rbP2 = findViewById(R.id.rbP2);

        TextView tvP3 = findViewById(R.id.tvTituloP3);
        RatingBar rbP3 = findViewById(R.id.rbP3);


        Pelicula p0 = new Pelicula();
        p0.setTitulo(getString(R.string.tituloP0));
        p0.setDirector(getResources().getString(R.string.directorP0));
        p0.setAno(getResources().getString(R.string.anoP0));
        p0.setActores(getResources().getStringArray(R.array.actoresP0));
        p0.setSinopsis(getResources().getString(R.string.sinopsisP0));
        p0.setImagenFondo(R.drawable.abierto_hasta_el_amanecer);
        p0.setPuntuacion(2);
        tvP0.setText(p0.getTitulo());
        rbP0.setProgress(p0.getPuntuacion());



        Pelicula p1 = new Pelicula();
        p1.setTitulo(getResources().getString(R.string.tituloP1));
        p1.setDirector(getResources().getString(R.string.directorP1));
        p1.setAno(getResources().getString(R.string.anoP1));
        p1.setActores(getResources().getStringArray(R.array.actoresP1));
        p1.setSinopsis(getResources().getString(R.string.sinopsisP1));
        p1.setImagenFondo(R.drawable.eldiadelabestia_width);
        p1.setPuntuacion(3);

        tvP1.setText(p1.getTitulo());
        rbP1.setProgress(p1.getPuntuacion());

        Pelicula p2 = new Pelicula();
        p2.setTitulo(getResources().getString(R.string.tituloP2));
        p2.setDirector(getResources().getString(R.string.directorP2));
        p2.setAno(getResources().getString(R.string.anoP2));
        p2.setActores(getResources().getStringArray(R.array.actoresP2));
        p2.setSinopsis(getResources().getString(R.string.sinopsisP2));
        p2.setImagenFondo(R.drawable.isidisiwidth);
        p2.setPuntuacion(5);

        tvP2.setText(p2.getTitulo());
        rbP2.setProgress(p2.getPuntuacion());

        Pelicula p3 = new Pelicula();
        p3.setTitulo(getResources().getString(R.string.tituloP3));
        p3.setDirector(getResources().getString(R.string.directorP3));
        p3.setAno(getResources().getString(R.string.anoP3));
        p3.setActores(getResources().getStringArray(R.array.actoresP3));
        p3.setSinopsis(getResources().getString(R.string.sinopsisP3));
        p3.setImagenFondo(R.drawable.snowpiercer_width);
        p3.setPuntuacion(1);

        tvP3.setText(p3.getTitulo());
        rbP3.setProgress(p3.getPuntuacion());

        Intent intent = new Intent(this,DetallesActivity.class);
        Bundle bundle = new Bundle();


        layoutP0.setOnClickListener(view -> {
            intent.putExtra("pelicula",p0);
            startActivity(intent);
        });
        layoutP1.setOnClickListener(view -> {
            intent.putExtra("pelicula",p1);
            startActivity(intent);
        });
        layoutP2.setOnClickListener(view -> {
            intent.putExtra("pelicula",p2);
            startActivity(intent);
        });
        layoutP3.setOnClickListener(view -> {
            intent.putExtra("pelicula",p3);
            startActivity(intent);
        });






    }
}