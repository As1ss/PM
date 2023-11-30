package al.if05.practica6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProgreso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProgreso extends Fragment {



    public FragmentProgreso() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int puntuacionMax = args.getInt("puntuacionMax");
        int numPregunta = args.getInt("NUMEROPREGUNTA");
        int valorpregunta = args.getInt("valorpregunta");
        View view = inflater.inflate(R.layout.fragment_progreso, container, false);
        TextView tvTest = view.findViewById(R.id.tvTest);
        tvTest.setText("Pregunta "+numPregunta+"/5"+" Puntuacion "+valorpregunta+"/"+puntuacionMax);



        // Inflate the layout for this fragment
        return view;
    }
}