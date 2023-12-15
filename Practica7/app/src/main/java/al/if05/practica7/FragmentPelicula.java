package al.if05.practica7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class FragmentPelicula extends Fragment {



    public FragmentPelicula() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle =this.getArguments();
        View view = inflater.inflate(R.layout.fragment_pelicula,container,false );

        ImageView ivPelicula = view.findViewById(R.id.ivPelicula);
        TextView tvTitulo = view.findViewById(R.id.tvTitulo);
        TextView tvDirector = view.findViewById(R.id.tvDirector);
        TextView tvAno = view.findViewById(R.id.tvAno);
        TextView tvActores = view.findViewById(R.id.tvActores);
        TextView tvSinopsis = view.findViewById(R.id.tvSinopsis);

        Pelicula pelicula =(Pelicula) bundle.getSerializable("pelicula");

        ivPelicula.setBackground(getResources().getDrawable(pelicula.getImagenFondo()));
        tvTitulo.setText("Titulo: "+pelicula.getTitulo());
        tvDirector.setText("Director: "+pelicula.getDirector());
        tvAno.setText("Año: "+pelicula.getAno());
        tvActores.setText("Actores: "+pelicula.getActoresString(pelicula.getActores()));
        tvSinopsis.setText(pelicula.getSinopsis());



        return view;
    }
}