package al.if05.practica9;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


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
    private List<Pelicula> auxBackup;
    private SQLHelper sqlHelper;
    private PeliculasDAO peliculasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, DetallesActivity.class);
        Bundle bundle = new Bundle();
        cargarComponentes();
        sqlHelper = new SQLHelper(this);
        peliculasDAO = new PeliculasDAO(sqlHelper);
        peliculas = peliculasDAO.readAll();
        auxBackup = peliculas;
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

            String opcion = spFiltrar.getSelectedItem().toString();


            if (opcion.equalsIgnoreCase("Vistas")) {
                for (int i = 0; i < peliculas.size(); i++) {
                    if (peliculas.get(i).getVista()) {
                        auxPeliculas.add(peliculas.get(i));


                    }
                }
                peliculas = auxPeliculas;

            } else {

                peliculas = auxBackup;


            }

            cargarAdapter(recyclerView);
            adatperEvent(intent, bundle);

        });

    }

    public void cargarPeliculas(){
        peliculas =peliculasDAO.readAll();
        peliculaAdapter.notifyDataSetChanged();
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
                    FragmentPelicula fragmentPelicula = new FragmentPelicula(peliculasDAO);
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
            Boolean vista = data.getBooleanExtra("vistaFragment",false);
            actualizarPelicula(titulo, puntuacion,vista);
            peliculaAdapter.notifyDataSetChanged();
        }
    }

    //Método implementado con la interfaz del fragment
    private void actualizarPelicula(String titulo, int puntuacion,boolean vista) {




         for (int i = 0; i < peliculas.size(); i++) {
            if (titulo.equalsIgnoreCase(peliculas.get(i).getTitulo())) {
                peliculas.get(i).setPuntuacion(puntuacion);
                peliculas.get(i).setVista(vista);

            }
        }


        peliculaAdapter.notifyDataSetChanged();

    }





    @Override
    public void actualizarPuntuaciones(String titulo, int puntuacion,boolean vista) {
        actualizarPelicula(titulo, puntuacion,vista);
        peliculaAdapter.notifyDataSetChanged();

    }
}