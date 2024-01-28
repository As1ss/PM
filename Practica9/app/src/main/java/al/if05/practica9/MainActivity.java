package al.if05.practica9;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


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
    private SwitchCompat swModoNocturno;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean modoNocturno = false;

    private Button btnAgregar;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, DetallesActivity.class);
         bundle = new Bundle();
        cargarComponentes();
        toolbar = findViewById(R.id.tbOperaciones);
        btnAgregar = findViewById(R.id.btnAgregar);
        setSupportActionBar(toolbar);
        sqlHelper = new SQLHelper(this);
        peliculasDAO = new PeliculasDAO(sqlHelper);

        peliculas = peliculasDAO.readAll();
        auxBackup = peliculas;
        cargarAdapter(recyclerView);

        swModoNocturno = findViewById(R.id.swModoNocturno);
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        modoNocturno = sharedPreferences.getBoolean("modoNocturno", false);
        swModoNocturno.setChecked(sharedPreferences.getBoolean("modoNocturno", false));


        if (modoNocturno) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        btnAgregar.setOnClickListener(view -> {
            showAddMovieDialog();
        });


        swModoNocturno.setOnClickListener(view -> {

            // Verificar si el interruptor está activado
            if (swModoNocturno.isChecked()) {
                // Activar el modo nocturno
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                modoNocturno = true;
                editor.putBoolean("modoNocturno", modoNocturno);
                editor.apply();

            } else {
                // Desactivar el modo nocturno
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                modoNocturno = false;
                editor.putBoolean("modoNocturno", modoNocturno);
                editor.apply();

            }


        });


        if (isTablet()) {
            // Si es una tablet, establece la orientación en landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

    private void showAddMovieDialog() {

        Toast.makeText(this, "DIALGOO PAWNEADO", Toast.LENGTH_SHORT).show();
        // Inflar el diseño del cuadro de diálogo
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_crear_pelicula, null);

        // Obtener referencias a los elementos del diseño del cuadro de diálogo
        EditText editTextTitle = dialogView.findViewById(R.id.etTitle);
        EditText editTextDirector = dialogView.findViewById(R.id.etDirector);
        EditText editTextYear = dialogView.findViewById(R.id.etYear);
        EditText editTextActors = dialogView.findViewById(R.id.etActores);
        EditText editTextSinopsis = dialogView.findViewById(R.id.etSinopsis);

        Button btnAddMovie = dialogView.findViewById(R.id.btnAddMovie);

        // Crear el cuadro de diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Agregar Película");



        // Configurar el botón Agregar Película
        Dialog dialog = builder.create();
        btnAddMovie.setOnClickListener(v -> {
            // Obtener los valores de los campos
            String title = editTextTitle.getText().toString();
            String year = editTextYear.getText().toString();
            String director = editTextDirector.getText().toString();
            String actors = editTextActors.getText().toString();
            String sinopsis = editTextSinopsis.getText().toString();
            String imagenFondo = "movie";
            int puntuacion = 0;
            boolean vista = false;

            Pelicula pelicula = new Pelicula();
            pelicula.setTitulo(title);
            pelicula.setDirector(director);
            pelicula.setAno(year);
            pelicula.setActores(new String[]{actors});
            pelicula.setSinopsis(sinopsis);
            pelicula.setPuntuacion(puntuacion);
            pelicula.setVista(vista);
            pelicula.setImagenFondo(imagenFondo);
            peliculasDAO.create(pelicula);


           cargarPeliculas();

           cargarAdapter(recyclerView);
           adatperEvent(intent, bundle);


            Toast.makeText(this, "Película añadida", Toast.LENGTH_SHORT).show();
            // Cerrar el cuadro de diálogo


           dialog.dismiss();


        });

        // Mostrar el cuadro de diálogo
        dialog.show();

    }


    public void cargarPeliculas() {
        peliculas = peliculasDAO.readAll();
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
        peliculaAdapter.setBtnBorrarListener(pelicula -> {
            peliculasDAO.delete(pelicula);
            cargarPeliculas();
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
            Boolean vista = data.getBooleanExtra("vistaFragment", false);
            actualizarPelicula(titulo, puntuacion, vista);
            peliculaAdapter.notifyDataSetChanged();
        }
    }

    //Método implementado con la interfaz del fragment
    private void actualizarPelicula(String titulo, int puntuacion, boolean vista) {


        for (int i = 0; i < peliculas.size(); i++) {
            if (titulo.equalsIgnoreCase(peliculas.get(i).getTitulo())) {
                peliculas.get(i).setPuntuacion(puntuacion);
                peliculas.get(i).setVista(vista);

            }
        }


        peliculaAdapter.notifyDataSetChanged();

    }


    @Override
    public void actualizarPuntuaciones(String titulo, int puntuacion, boolean vista) {
        actualizarPelicula(titulo, puntuacion, vista);
        peliculaAdapter.notifyDataSetChanged();

    }
}