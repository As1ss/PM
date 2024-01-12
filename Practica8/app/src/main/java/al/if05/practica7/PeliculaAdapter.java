package al.if05.practica7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder>{
    private List<Pelicula> peliculaList;

    public PeliculaAdapter(List<Pelicula> newPeliculaList){
        this.peliculaList=newPeliculaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula_vista_lista,parent,false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitulo.setText(peliculaList.get(position).getTitulo());
        holder.rbPuntuacion.setProgress(peliculaList.get(position).getPuntuacion());




    }

    @Override
    public int getItemCount() {
        return peliculaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitulo;
        private RatingBar rbPuntuacion;
        private LinearLayout lyFilm;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTituloLista);
            rbPuntuacion = itemView.findViewById(R.id.rbPuntuacionLista);
            lyFilm = itemView.findViewById(R.id.lyFilmLista);

        }
    }
}
