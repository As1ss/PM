package if05.tresenraya;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    public class LocalBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.lady_of_the_80x27s);
        mediaPlayer.setLooping(true); // Reproduce la canción en bucle
        mediaPlayer.setVolume(0.5f, 0.5f);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return START_STICKY;
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }

    public void resumeMusic() {
        mediaPlayer.start();
    }


    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
