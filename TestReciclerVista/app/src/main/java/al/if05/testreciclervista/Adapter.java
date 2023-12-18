package al.if05.testreciclervista;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Piloto> pilotoList;


    public Adapter() {
        pilotoList = new ArrayList<>();


    }

    public Adapter(List<Piloto> pilotos) {
        this.pilotoList = pilotos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.piloto_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Piloto piloto = pilotoList.get(position);
        holder.tvPiloto.setText("Nombre: " + piloto.getNombre());
        holder.tvPosicion.setText("Posicion: " + piloto.getPosicion());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPiloto;
        private TextView tvPosicion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPiloto = itemView.findViewById(R.id.tvPiloto);
            tvPosicion = itemView.findViewById(R.id.tvPosicion);

        }
    }


    @Override
    public int getItemCount() {
        return pilotoList.size();
    }
}
