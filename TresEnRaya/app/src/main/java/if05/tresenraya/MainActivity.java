package if05.tresenraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MusicService musicService;
    private boolean isBound = false;
    String userName;
    EditText etUsuario;
    Button btnJugar;
    MediaPlayer btnSound;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MusicService.LocalBinder localBinder = (MusicService.LocalBinder) binder;
            musicService = localBinder.getService();
            isBound = true;
            // Controla la reproducción de la música aquí
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsuario = findViewById(R.id.etUsuario);
        btnJugar = findViewById(R.id.btnJugar);
        btnSound = MediaPlayer.create(this, R.raw.coin_1);

        // Inicia el servicio de música
        Intent musicIntent = new Intent(this, MusicService.class);
        startForegroundService(musicIntent);


        // Vincúlate al servicio
        bindService(musicIntent, serviceConnection, Context.BIND_AUTO_CREATE);


        btnJugar.setOnClickListener(view -> {
            btnSound.start();
            userName = etUsuario.getText().toString();
            if (userName.length() > 2) {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, getText(R.string.errorUsuarioTexto), Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Desvincúlate del servicio cuando la actividad se destruya
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("userName", userName);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.get("userName");
    }
}