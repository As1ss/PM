package al.if05.practica6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProgreso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProgreso extends Fragment {

    private int puntuacionMax;
    private int numPregunta;
    private int puntuacion;

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
        puntuacionMax = args.getInt("puntuacionMax");
        numPregunta = args.getInt("NUMEROPREGUNTA");
        puntuacion = args.getInt("puntuacion");
        View view = inflater.inflate(R.layout.fragment_progreso, container, false);
        TextView tvTest = view.findViewById(R.id.tvTest);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        tvTest.setText("Pregunta " + numPregunta + "/5" + " Puntuacion " + puntuacion + "/" + puntuacionMax);
        progressBar.setProgress(numPregunta);


        // Inflate the layout for this fragment
        return view;
    }
}