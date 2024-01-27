package al.if05.practica9;

import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;



public class FragmentPelicula extends Fragment {
    private Bundle bundle;

    private onFragmentInteractListener fListener;
    private PeliculasDAO peliculasDAO;


    public FragmentPelicula() {

    }
    public FragmentPelicula(PeliculasDAO newPeliculasDAO){
        this.peliculasDAO=newPeliculasDAO;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle =this.getArguments();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pelicula,container,false );

        ImageView ivPelicula = view.findViewById(R.id.ivPelicula);
        TextView tvTitulo = view.findViewById(R.id.tvTitulo);
        TextView tvDirector = view.findViewById(R.id.tvDirector);
        TextView tvAno = view.findViewById(R.id.tvAno);
        TextView tvActores = view.findViewById(R.id.tvActores);
        TextView tvSinopsis = view.findViewById(R.id.tvSinopsis);
        RatingBar rbPuntuacion = view.findViewById(R.id.rbPuntuacion);
        CheckBox cbVista = view.findViewById(R.id.cbViewed);

        Pelicula pelicula =(Pelicula) bundle.getSerializable("pelicula");

        ivPelicula.setBackgroundResource(getResources().getIdentifier(pelicula.getImagenFondo(),"drawable", getActivity().getPackageName()));
        tvTitulo.setText("Titulo: "+pelicula.getTitulo());
        tvDirector.setText("Director: "+pelicula.getDirector());
        tvAno.setText("Año: "+pelicula.getAno());
        tvActores.setText("Actores: "+pelicula.getActoresString(pelicula.getActores()));
        tvSinopsis.setText(pelicula.getSinopsis());
        rbPuntuacion.setProgress(pelicula.getPuntuacion());
        cbVista.setChecked(pelicula.getVista());

        cbVista.setOnClickListener(view1 -> {
            pelicula.setVista(cbVista.isChecked()==true?true:false);
            pasarData(pelicula.getTitulo(),(int)pelicula.getPuntuacion(),pelicula.getVista());
            peliculasDAO.update(pelicula);
        });



        rbPuntuacion.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            // Solo pasa la data si la calificación cambió debido a la interacción del usuario
            if (fromUser) {
                pasarData(pelicula.getTitulo(),(int) rating,pelicula.getVista());
                //Actualizamos la pelicula y guardamos en la base de datos
                  pelicula.setPuntuacion((int)rating);
                  peliculasDAO.update(pelicula);





            }
        });









        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentInteractListener){
            fListener= (onFragmentInteractListener) context;
        }


    }

    public interface onFragmentInteractListener{
        void actualizarPuntuaciones (String titulo,int puntuacion,boolean vista);

    }
    public void pasarData(String titulo,int puntuacion,boolean vista){
        if(fListener!=null){
            fListener.actualizarPuntuaciones(titulo,puntuacion,vista);
        }

    }

}