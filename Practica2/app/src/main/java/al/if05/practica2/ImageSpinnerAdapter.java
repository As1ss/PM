package al.if05.practica2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.List;



public class ImageSpinnerAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> values;
    private final List<Integer> imageResIds;

    public ImageSpinnerAdapter(Context context, List<String> values, List<Integer> imageResIds) {
        super(context, R.layout.image_spinner, values);
        this.context = context;
        this.values = values;
        this.imageResIds = imageResIds;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.image_spinner, parent, false);

        ImageView imageView = rowView.findViewById(R.id.spinner_image);
        imageView.setImageResource(imageResIds.get(position));

        return rowView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}