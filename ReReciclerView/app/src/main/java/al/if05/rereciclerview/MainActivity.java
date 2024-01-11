package al.if05.rereciclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class MainActivity extends AppCompatActivity {

    List<Pelicula> peliculas;
    int i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rvLista);
        Pelicula p1 = new Pelicula("Pulp Fiction","Quentin Tarantino",5);
        Pelicula p2 = new Pelicula("The Shawshank Redemption","Frank Darabont",2);
        peliculas = new ArrayList<Pelicula>();
        Button btnAñadir = findViewById(R.id.btnAñadir);
        i = 1;

        peliculas.add(p1);
        peliculas.add(p2);



        PeliculaAdapter adapter = new PeliculaAdapter(peliculas);




        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        btnAñadir.setOnClickListener(v -> {

            Pelicula pelicula = new Pelicula("Pelicula"+i,"Autor"+i,i);
            peliculas.add(pelicula);
            adapter.notifyItemInserted(i);

            i++;
        });




    }
}