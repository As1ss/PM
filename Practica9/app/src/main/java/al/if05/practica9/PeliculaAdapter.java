package al.if05.practica9;

import android.content.Context;
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
    private OnItemClickListener itemListener;

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

        // Obtener el contexto desde el ViewHolder
        Context context = holder.itemView.getContext();

        int puntuacion = peliculaList.get(position).getPuntuacion();
        if(puntuacion>0){
            holder.rbPuntuacion.setProgress(puntuacion);
            holder.rbPuntuacion.setVisibility(View.VISIBLE);
        }
        else{
            holder.rbPuntuacion.setVisibility(View.INVISIBLE);
        }


        holder.tvTitulo.setText(peliculaList.get(position).getTitulo());
        holder.lyFilm.setBackgroundResource(context.getResources().getIdentifier(peliculaList.get(position).getImagenFondo(),"drawable",context.getPackageName()));
        holder.tvAutor.setText(peliculaList.get(position).getDirector());
        holder.lyFilm.setOnClickListener(view -> {
            if(itemListener!=null){
                itemListener.onItemClick(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return peliculaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitulo;
        private RatingBar rbPuntuacion;
        private LinearLayout lyFilm;
        private TextView tvAutor;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTituloLista);
            rbPuntuacion = itemView.findViewById(R.id.rbPuntuacionLista);
            lyFilm = itemView.findViewById(R.id.lyFilmLista);
            tvAutor = itemView.findViewById(R.id.tvAutorLista);

        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }



    public void setOnItemClickListener(OnItemClickListener listener){
        this.itemListener=listener;
    }

}
