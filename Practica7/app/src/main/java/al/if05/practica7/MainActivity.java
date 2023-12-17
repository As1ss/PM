package al.if05.practica7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements  FragmentPelicula.onFragmentInteractListener{

    private static final int REQUEST_CODE_DETALLES_ACTIVITY = 1;
    Pelicula[] peliculas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, DetallesActivity.class);
        LinearLayout lyGeneral = findViewById(R.id.lyGeneral);
        FragmentContainerView fragmentContainer = findViewById(R.id.fragmentContainerMain);
        Bundle bundle = new Bundle();
        Button btnSalir = findViewById(R.id.btnCerrar);
        peliculas = cargarPeliculas();
        cargarTitulosPuntuacion(peliculas);

        if (isTablet()) {
            // Si es una tablet, establece la orientación en landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


        for (int i = 0; i < lyGeneral.getChildCount(); i++) {
            LinearLayout layout = (LinearLayout) lyGeneral.getChildAt(i);
            Pelicula peliculaIntent = peliculas[i];

            layout.setOnClickListener(view -> {
                //Si es en tablet landscape
                if (isTablet()){
                    FragmentPelicula fragmentPelicula = new FragmentPelicula();

                    bundle.putSerializable("pelicula",peliculaIntent);
                    fragmentPelicula.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerMain,fragmentPelicula).commit();

                }

                else{
                    //Si es portrait movil
                      intent.putExtra("pelicula", peliculaIntent);
                    startActivityForResult(intent, REQUEST_CODE_DETALLES_ACTIVITY);
                }
            });
        }



        //Finalizar la app cuando pulsemos sobre cerrar app
        btnSalir.setOnClickListener(view -> {
            finishAffinity();
        });
    }

    //Este metodo es para saber con que tipo de dispositivo estamos operando y poder actuar en consecuencia con los fragments
    private boolean isTablet() {
        Configuration configuration = getResources().getConfiguration();
        return configuration.smallestScreenWidthDp >= 600;
    }

    // Método para recibir el resultado de DetallesActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_DETALLES_ACTIVITY && resultCode == RESULT_OK) {
            // Obtener la puntuación desde DetallesActivity
            int puntuacion = data.getIntExtra("puntuacionDesdeFragmento", 0);
            String titulo = data.getStringExtra("tituloFragment");
            actualizarPelicula(titulo,puntuacion);
            cargarTitulosPuntuacion(peliculas);
        }
    }
    private void actualizarPelicula(String titulo,int puntuacion){
        for (int i =0;i<peliculas.length;i++){
            if (titulo.equalsIgnoreCase(peliculas[i].getTitulo())){
                peliculas[i].setPuntuacion(puntuacion);
            }
        }
    }
    private void cargarTitulosPuntuacion(Pelicula[] peliculas) {

        TextView tvP0 = findViewById(R.id.tvTituloP0);
        RatingBar rbP0 = findViewById(R.id.rbP0);
        tvP0.setText(peliculas[0].getTitulo());
        rbP0.setProgress(peliculas[0].getPuntuacion());

        TextView tvP1 = findViewById(R.id.tvTituloP1);
        RatingBar rbP1 = findViewById(R.id.rbP1);
        tvP1.setText(peliculas[1].getTitulo());
        rbP1.setProgress(peliculas[1].getPuntuacion());

        TextView tvP2 = findViewById(R.id.tvTituloP2);
        RatingBar rbP2 = findViewById(R.id.rbP2);
        tvP2.setText(peliculas[2].getTitulo());
        rbP2.setProgress(peliculas[2].getPuntuacion());

        TextView tvP3 = findViewById(R.id.tvTituloP3);
        RatingBar rbP3 = findViewById(R.id.rbP3);
        tvP3.setText(peliculas[3].getTitulo());
        rbP3.setProgress(peliculas[3].getPuntuacion());
    }



    private Pelicula[] cargarPeliculas() {

        Pelicula[] peliculas = new Pelicula[4];

        Pelicula p0 = new Pelicula();
        p0.setTitulo(getString(R.string.tituloP0));
        p0.setDirector(getResources().getString(R.string.directorP0));
        p0.setAno(getResources().getString(R.string.anoP0));
        p0.setActores(getResources().getStringArray(R.array.actoresP0));
        p0.setSinopsis(getResources().getString(R.string.sinopsisP0));
        p0.setImagenFondo(R.drawable.abierto_hasta_el_amanecer);
        p0.setPuntuacion(2);


        Pelicula p1 = new Pelicula();
        p1.setTitulo(getResources().getString(R.string.tituloP1));
        p1.setDirector(getResources().getString(R.string.directorP1));
        p1.setAno(getResources().getString(R.string.anoP1));
        p1.setActores(getResources().getStringArray(R.array.actoresP1));
        p1.setSinopsis(getResources().getString(R.string.sinopsisP1));
        p1.setImagenFondo(R.drawable.eldiadelabestia_width);
        p1.setPuntuacion(3);


        Pelicula p2 = new Pelicula();
        p2.setTitulo(getResources().getString(R.string.tituloP2));
        p2.setDirector(getResources().getString(R.string.directorP2));
        p2.setAno(getResources().getString(R.string.anoP2));
        p2.setActores(getResources().getStringArray(R.array.actoresP2));
        p2.setSinopsis(getResources().getString(R.string.sinopsisP2));
        p2.setImagenFondo(R.drawable.isidisiwidth);
        p2.setPuntuacion(5);


        Pelicula p3 = new Pelicula();
        p3.setTitulo(getResources().getString(R.string.tituloP3));
        p3.setDirector(getResources().getString(R.string.directorP3));
        p3.setAno(getResources().getString(R.string.anoP3));
        p3.setActores(getResources().getStringArray(R.array.actoresP3));
        p3.setSinopsis(getResources().getString(R.string.sinopsisP3));
        p3.setImagenFondo(R.drawable.snowpiercer_width);
        p3.setPuntuacion(1);

        peliculas[0] = p0;
        peliculas[1] = p1;
        peliculas[2] = p2;
        peliculas[3] = p3;

        return peliculas;
    }


    @Override
    public void actualizarPuntuaciones(String titulo, int puntuacion) {
        actualizarPelicula(titulo,puntuacion);
        cargarTitulosPuntuacion(peliculas);
    }
}