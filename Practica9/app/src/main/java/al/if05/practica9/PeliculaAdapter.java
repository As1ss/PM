package al.if05.practica9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder>{
    private List<Pelicula> peliculaList;
    private OnItemClickListener itemListener;
    private BtnBorrarListener btnBorrarListener;


    public PeliculaAdapter(List<Pelicula> newPeliculaList){
        this.peliculaList=newPeliculaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula_vista_lista,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Obtener el contexto desde el ViewHolder
        Context context = holder.itemView.getContext();

        int puntuacion = peliculaList.get(position).getPuntuacion();
        boolean vista = peliculaList.get(position).getVista();

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
        holder.tvVista.setVisibility(vista?View.VISIBLE:View.INVISIBLE);
        holder.lyFilm.setOnClickListener(view -> {
            if(itemListener!=null){
                itemListener.onItemClick(view,position);
            }
        });
        holder.btnBorrar.setOnClickListener(view -> {
            if(btnBorrarListener!=null){
                btnBorrarListener.borrarPelicula(peliculaList.get(position));
                this.peliculaList.remove(position);
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

        private  TextView tvVista;
        private Button btnBorrar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTituloLista);
            rbPuntuacion = itemView.findViewById(R.id.rbPuntuacionLista);
            lyFilm = itemView.findViewById(R.id.lyFilmLista);
            tvAutor = itemView.findViewById(R.id.tvAutorLista);
            tvVista =itemView.findViewById(R.id.tvVista);
            btnBorrar =itemView.findViewById(R.id.btnBorrar);

        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }

    public interface BtnBorrarListener{
        public void borrarPelicula(Pelicula pelicula);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.itemListener=listener;
    }
    public void setBtnBorrarListener(BtnBorrarListener listener){
        this.btnBorrarListener=listener;
    }

}
