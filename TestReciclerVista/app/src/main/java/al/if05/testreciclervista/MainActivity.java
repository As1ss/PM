package al.if05.testreciclervista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvLista);

        Piloto p1 = new Piloto("Mario",2);
        Piloto p2 = new Piloto("Luigi",1);
        Piloto p3 = new Piloto("Wario",4);
        Piloto p4 = new Piloto("Daisy",3);
        Piloto p5 = new Piloto("Bowser",5);

        List<Piloto> pilotos = new ArrayList<>();
        pilotos.add(p1);
        pilotos.add(p2);
        pilotos.add(p3);
        pilotos.add(p4);
        pilotos.add(p5);

        adapter= new Adapter(pilotos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



    }
}