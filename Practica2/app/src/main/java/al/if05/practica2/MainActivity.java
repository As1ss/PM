package al.if05.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] cities = getResources().getStringArray(R.array.cityList);
        String[] hobbies = getResources().getStringArray(R.array.hobbiesList);

        Spinner spCity = findViewById(R.id.spinnerCity);
        ArrayAdapter<String> adapterArrayCities = new ArrayAdapter<>(this, R.layout.spinner_item, cities);
        adapterArrayCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(adapterArrayCities);

        Spinner spHobbies = findViewById(R.id.spinerInterest);
        ArrayAdapter<String> adapterArrayHobbies = new ArrayAdapter<>(this,  R.layout.spinner_item, hobbies);
        adapterArrayHobbies.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHobbies.setPrompt("Hobbies");
        spHobbies.setAdapter(adapterArrayHobbies);
        loadImgOnSpinner();


    }
    private void loadImgOnSpinner() {
        Spinner spinner = findViewById(R.id.spinnerAvatar);
        List<String> values = Arrays.asList("Op1", "Op2", "Op3","Op4");
        List<Integer> imageResIds = Arrays.asList(
                R.drawable.captainamerica,
                R.drawable.hulk,
                R.drawable.ironman,
                R.drawable.thor
        );

        ImageSpinnerAdapter adapter = new ImageSpinnerAdapter(this, values, imageResIds);
        spinner.setAdapter(adapter);
    }
}