package al.if05.testreciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Piloto> pilotoList;

    public Adapter(List<Piloto> pilotos) {
        this.pilotoList = pilotos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pilotos_list_view,parent,false);

        return new ViewHolder(view);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvPiloto.setText("Piloto: "+pilotoList.get(position).getNombre());
        holder.tvPosicion.setText("Posicion: "+pilotoList.get(position).getPosicion());

    }

    @Override
    public int getItemCount() {
        return pilotoList.size();
    }
}
