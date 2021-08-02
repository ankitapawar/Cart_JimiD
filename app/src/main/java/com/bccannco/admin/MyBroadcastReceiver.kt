package com.bccannco.admin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bccannco.admin.chats.ChatListFragment


private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver()
{
    var myUnreadCount: Int = 0

    override fun onReceive(context: Context, intent: Intent)
    {
        if(intent != null && intent.extras != null && intent.extras!!.get("action")!!.equals("show_notification"))
        {
            showNotification(context)
        }
    }

    fun showNotification( con : Context)
    {
        val notificationManager: NotificationManager =
            con.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = con.getString(R.string.notification_channel_name)
//            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(con.getString(R.string.notification_channel_id), name, importance).apply{
//                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }

        val intent1 = Intent(con , ChatListFragment::class.java)
        intent1.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent: PendingIntent = PendingIntent.getActivity(con, 0, intent1, 0)

        var builder = NotificationCompat.Builder(con, con.getString(R.string.notification_channel_id))
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(con.getString(R.string.app_name))
            .setContentText(con.getString(R.string.notification_content))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(1, builder.build())
    }

}