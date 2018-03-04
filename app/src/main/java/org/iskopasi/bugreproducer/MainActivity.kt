package org.iskopasi.bugreproducer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onBackPressed()

        val notifyID = 2
        val channelId = "bugger_channel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round) //<-- Using ic_launcher_round.xml causes Trebuchet to crash.
                .setContentText("Hello World!")
                .setContentTitle("Please, long-press the app icon now.")
                .setDefaults(Notification.DEFAULT_ALL)

        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        //need to create the notification channel before it can be used.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelId,
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notifyID, notificationBuilder.build())
    }
}
