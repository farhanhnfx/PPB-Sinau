package com.example.ppb

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val channelId = "TEST_NOTIF"
    private val notifId = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val notifManager = getSystemService((Context.NOTIFICATION_SERVICE)) as NotificationManager

        binding.btnNotif.setOnClickListener {
            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                PendingIntent.FLAG_IMMUTABLE
//                PendingIntent.FLAG_UPDATE_CURRENT
                PendingIntent.FLAG_MUTABLE
            }
            else {
                0
            }

//            val intent = Intent(this, NotifReceiver::class.java).putExtra("MESSAGE", "cobacoba")
            val intent = Intent(this, MainActivity::class.java)
//            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, flag)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, flag)

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("Notifku")
                .setContentText("Tess notif haloo")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
//                .addAction(0, "Baca Notif", pendingIntent)

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(channelId, "Notifku", NotificationManager.IMPORTANCE_DEFAULT)
                with(notifManager) {
                    createNotificationChannel(notifChannel)
                    notify(notifId, builder.build())
                }
            }
            else {
                notifManager.notify(notifId, builder.build())
            }
        }

        binding.btnUpdate.setOnClickListener {
            val notifImg = BitmapFactory.decodeResource(resources, R.drawable.lasem)
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("Notifnya diupdate!")
                .setContentText("berubah tambah gambar.....")
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(notifImg))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            notifManager.notify(notifId, builder.build())
        }
    }
}