package al.if05.practica7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentPelicula.onFragmentInteractListener {

    private static final int REQUEST_CODE_DETALLES_ACTIVITY = 1;
    private List<Pelicula> peliculas;
    private PeliculaAdapter peliculaAdapter;
    private RecyclerView recyclerView;
    private Button btnSalir;
    private Button btnFiltrar;
    private Spinner spFiltrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, DetallesActivity.class);
        Bundle bundle = new Bundle();
        cargarComponentes();
        peliculas = cargarPeliculas();
        cargarAdapter(recyclerView);


        if (isTablet()) {
            // Si es una tablet, establece la orientación en landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        adatperEvent(intent, bundle);


        //Finalizar la app cuando pulsemos sobre cerrar app
        btnSalir.setOnClickListener(view -> {
            finishAffinity();
        });

        btnFiltrar.setOnClickListener(view -> {
            List<Pelicula> auxPeliculas = new ArrayList<Pelicula>();
            List<Integer> puntuaciones = new ArrayList<Integer>();
            String opcion = spFiltrar.getSelectedItem().toString();


            if (opcion.equalsIgnoreCase("Vistas")) {
                for (int i = 0; i < peliculas.size(); i++) {
                    if (peliculas.get(i).getPuntuacion() > 0) {
                        auxPeliculas.add(peliculas.get(i));



                    }
                }
                peliculas= auxPeliculas;

            } else {

                peliculas=cargarPeliculas();
               

            }

            cargarAdapter(recyclerView);
            adatperEvent(intent, bundle);

        });

    }

    private void cargarComponentes() {
        recyclerView = findViewById(R.id.rvPeliculas);
        spFiltrar = findViewById(R.id.spinnerOpciones);
        btnSalir = findViewById(R.id.btnCerrar);
        btnFiltrar = findViewById(R.id.btnFiltrar);
    }

    private void adatperEvent(Intent intent, Bundle bundle) {
        peliculaAdapter.setOnItemClickListener((view, position) -> {
            LinearLayout lyFilm = view.findViewById(R.id.lyFilmLista);
            Pelicula peliculaIntent = peliculas.get(position);

            lyFilm.setOnClickListener(v -> {
                //Si es en tablet landscape
                if (isTablet()) {
                    FragmentPelicula fragmentPelicula = new FragmentPelicula();
                    bundle.putSerializable("pelicula", peliculaIntent);
                    fragmentPelicula.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerMain, fragmentPelicula).commit();



                } else {
                    //Si es portrait movil
                    intent.putExtra("pelicula", peliculaIntent);
                    startActivityForResult(intent, REQUEST_CODE_DETALLES_ACTIVITY);

                }
            });

        });
    }

    private void cargarAdapter(RecyclerView recyclerView) {
        peliculaAdapter = new PeliculaAdapter(peliculas);
        recyclerView.setAdapter(peliculaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
            actualizarPelicula(titulo, puntuacion);
            peliculaAdapter.notifyDataSetChanged();
        }
    }

    //Método implementado con la interfaz del fragment
    private void actualizarPelicula(String titulo, int puntuacion) {
        for (int i = 0; i < peliculas.size(); i++) {
            if (titulo.equalsIgnoreCase(peliculas.get(i).getTitulo())) {
                peliculas.get(i).setPuntuacion(puntuacion);
            }
        }
    }


    private List<Pelicula> cargarPeliculas() {

        List<Pelicula> peliculas = new ArrayList<Pelicula>();

        Pelicula p0 = new Pelicula();
        p0.setTitulo(getString(R.string.tituloP0));
        p0.setDirector(getResources().getString(R.string.directorP0));
        p0.setAno(getResources().getString(R.string.anoP0));
        p0.setActores(getResources().getStringArray(R.array.actoresP0));
        p0.setSinopsis(getResources().getString(R.string.sinopsisP0));
        p0.setImagenFondo("abierto_hasta_el_amanecer");
        p0.setPuntuacion(0);


        Pelicula p1 = new Pelicula();
        p1.setTitulo(getResources().getString(R.string.tituloP1));
        p1.setDirector(getResources().getString(R.string.directorP1));
        p1.setAno(getResources().getString(R.string.anoP1));
        p1.setActores(getResources().getStringArray(R.array.actoresP1));
        p1.setSinopsis(getResources().getString(R.string.sinopsisP1));
        p1.setImagenFondo("eldiadelabestia_width");
        p1.setPuntuacion(0);


        Pelicula p2 = new Pelicula();
        p2.setTitulo(getResources().getString(R.string.tituloP2));
        p2.setDirector(getResources().getString(R.string.directorP2));
        p2.setAno(getResources().getString(R.string.anoP2));
        p2.setActores(getResources().getStringArray(R.array.actoresP2));
        p2.setSinopsis(getResources().getString(R.string.sinopsisP2));
        p2.setImagenFondo("isidisiwidth");
        p2.setPuntuacion(0);


        Pelicula p3 = new Pelicula();
        p3.setTitulo(getResources().getString(R.string.tituloP3));
        p3.setDirector(getResources().getString(R.string.directorP3));
        p3.setAno(getResources().getString(R.string.anoP3));
        p3.setActores(getResources().getStringArray(R.array.actoresP3));
        p3.setSinopsis(getResources().getString(R.string.sinopsisP3));
        p3.setImagenFondo("snowpiercer_width");
        p3.setPuntuacion(0);

        Pelicula p4 = new Pelicula();
        p4.setTitulo(getResources().getString(R.string.tituloP4));
        p4.setDirector(getResources().getString(R.string.directorP4));
        p4.setAno(getResources().getString(R.string.anoP4));
        p4.setActores(getResources().getStringArray(R.array.actoresP4));
        p4.setSinopsis(getResources().getString(R.string.sinopsisP4));
        p4.setImagenFondo("inception");
        p4.setPuntuacion(0);


        Pelicula p5 = new Pelicula();
        p5.setTitulo(getResources().getString(R.string.tituloP5));
        p5.setDirector(getResources().getString(R.string.directorP5));
        p5.setAno(getResources().getString(R.string.anoP5));
        p5.setActores(getResources().getStringArray(R.array.actoresP5));
        p5.setSinopsis(getResources().getString(R.string.sinopsisP5));
        p5.setImagenFondo("pulp_fiction");
        p5.setPuntuacion(0);


        Pelicula p6 = new Pelicula();
        p6.setTitulo(getResources().getString(R.string.tituloP6));
        p6.setDirector(getResources().getString(R.string.directorP6));
        p6.setAno(getResources().getString(R.string.anoP6));
        p6.setActores(getResources().getStringArray(R.array.actoresP6));
        p6.setSinopsis(getResources().getString(R.string.sinopsisP6));
        p6.setImagenFondo("shawshank");
        p6.setPuntuacion(0);

        Pelicula p7 = new Pelicula();
        p7.setTitulo(getResources().getString(R.string.tituloP7));
        p7.setDirector(getResources().getString(R.string.directorP7));
        p7.setAno(getResources().getString(R.string.anoP7));
        p7.setActores(getResources().getStringArray(R.array.actoresP7));
        p7.setSinopsis(getResources().getString(R.string.sinopsisP7));
        p7.setImagenFondo("matrix");
        p7.setPuntuacion(0);

        Pelicula p8 = new Pelicula();
        p8.setTitulo(getResources().getString(R.string.tituloP8));
        p8.setDirector(getResources().getString(R.string.directorP8));
        p8.setAno(getResources().getString(R.string.anoP8));
        p8.setActores(getResources().getStringArray(R.array.actoresP8));
        p8.setSinopsis(getResources().getString(R.string.sinopsisP8));
        p8.setImagenFondo("eternal_sunshine");
        p8.setPuntuacion(0);


        Pelicula p9 = new Pelicula();
        p9.setTitulo(getResources().getString(R.string.tituloP9));
        p9.setDirector(getResources().getString(R.string.directorP9));
        p9.setAno(getResources().getString(R.string.anoP9));
        p9.setActores(getResources().getStringArray(R.array.actoresP9));
        p9.setSinopsis(getResources().getString(R.string.sinopsisP9));
        p9.setImagenFondo("lalaland");
        p9.setPuntuacion(0);


        peliculas.add(p0);
        peliculas.add(p1);
        peliculas.add(p2);
        peliculas.add(p3);
        peliculas.add(p4);
        peliculas.add(p5);
        peliculas.add(p6);
        peliculas.add(p7);
        peliculas.add(p8);
        peliculas.add(p9);


        return peliculas;
    }


    @Override
    public void actualizarPuntuaciones(String titulo, int puntuacion) {
        actualizarPelicula(titulo, puntuacion);
        peliculaAdapter.notifyDataSetChanged();

    }
}