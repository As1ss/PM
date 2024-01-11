package al.if05.rereciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder> {

    private List<Pelicula> peliculaList;

    public PeliculaAdapter(List<Pelicula> newPeliculaList) {

        this.peliculaList = newPeliculaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_pelicula, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitulo.setText("Titulo: " + peliculaList.get(position).getTitulo());
        holder.tvAutor.setText("Autor: " + peliculaList.get(position).getAutor());
        holder.rbPuntuacion.setProgress(peliculaList.get(position).getPuntuacion());


    }

    @Override
    public int getItemCount() {
        return peliculaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitulo;
        private TextView tvAutor;
        private RatingBar rbPuntuacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvAutor = itemView.findViewById(R.id.tvAutor);
            rbPuntuacion = itemView.findViewById(R.id.rbPuntuacion);


        }
    }
}
