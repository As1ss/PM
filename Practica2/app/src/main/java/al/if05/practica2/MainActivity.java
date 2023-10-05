package al.if05.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] cities = getResources().getStringArray(R.array.cityList);
        String[] hobbies = getResources().getStringArray(R.array.hobbiesList);

        Spinner spCity = findViewById(R.id.spinnerCity);
        ArrayAdapter<String> adapterArrayCities = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapterArrayCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(adapterArrayCities);

        Spinner spHobbies = findViewById(R.id.spinerInterest);
        ArrayAdapter<String> adapterArrayHobbies = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hobbies);
        spHobbies.setPrompt("Hobbies");
        spHobbies.setAdapter(adapterArrayHobbies);


    }
}