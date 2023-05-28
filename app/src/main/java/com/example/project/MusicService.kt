import android.app.Service
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.IBinder
import android.provider.Settings

class MusicService : Service() {
    private var ringtone: Ringtone? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        ringtone = RingtoneManager.getRingtone(this, Settings.System.DEFAULT_RINGTONE_URI)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        ringtone?.play()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        ringtone?.stop()
    }
}
