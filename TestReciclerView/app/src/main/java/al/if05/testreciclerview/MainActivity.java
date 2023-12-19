package al.if05.testreciclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Piloto p1, p2, p3, p4, p5;
    private List<Piloto> pilotosList;
    private Adapter adapter;
    private EditText etPiloto;
    private EditText etPosicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvListaPilotos);
        etPiloto = findViewById(R.id.etPiloto);
        etPosicion = findViewById(R.id.etPosicion);
        pilotosList = new ArrayList<>();

        p1 = new Piloto("Mario", 2);
        p2 = new Piloto("Bowser", 3);
        p3 = new Piloto("Luigi", 1);
        p4 = new Piloto("Wario", 4);
        p5 = new Piloto("Daisy", 5);
        pilotosList.add(p1);
        pilotosList.add(p2);
        pilotosList.add(p3);
        pilotosList.add(p4);
        pilotosList.add(p5);

        adapter= new Adapter(pilotosList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



    }

    public void btnEvents(View view) {
        String nombre = etPiloto.getText().toString();
        int posicion = Integer.parseInt(etPosicion.getText().toString());
        Piloto piloto = pilotosList.get(posicion-1);

        if (view.getId()==R.id.btnAgregar){
            pilotosList.add(new Piloto(nombre,posicion));
            adapter.notifyDataSetChanged();



        }
        if (view.getId()==R.id.btnModificar){


        }
        if (view.getId()==R.id.btnModificarPos){

        }
        if (view.getId()==R.id.btnEliminar){
            pilotosList.remove(piloto);
            adapter.notifyDataSetChanged();


        }
    }
}