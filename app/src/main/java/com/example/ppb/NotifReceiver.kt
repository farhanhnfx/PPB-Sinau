package com.example.ppb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class NotifReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val msg = p1?.getStringExtra("MESSAGE")
        Log.d("Notif", msg.toString())
        if (msg != null) {
            Toast.makeText(p0, msg, Toast.LENGTH_SHORT).show()
        }
    }
}