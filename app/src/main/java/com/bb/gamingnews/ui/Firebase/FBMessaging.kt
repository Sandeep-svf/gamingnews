package com.bizbrolly.wayja.fcmNotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject

class FBMessaging : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.from)
        Log.d("msg", "onMessageReceived: " + remoteMessage.data["message"])

        var mTitle = ""
        var mBody = ""
        var notificationType = ""
        var ReferenceId = 0


        remoteMessage.data.let {
            Log.e("firebaseLoaded", "Message data payload: " + remoteMessage.data)

            if (remoteMessage.data.isNotEmpty()) {
                val param = remoteMessage.data as Map<String, String>
                val json = JSONObject(param)
                notificationType = json.getString("Type")
//                ReferenceId = json.getInt("TagId")


                Log.e(
                    "firebaseLoaded_",
                    "Message data payload: " + ReferenceId + " notificationType" + notificationType
                )


            }


        }
        remoteMessage.notification?.let {
            Log.d("#####", "Message Notification Body: ${it.body}")
            mBody = "${it.body}"
            mTitle = "${it.title}"

        }
        mShowNotification(mTitle, mBody, notificationType, ReferenceId)


    }


    companion object {
//        var updateUiListner: UpdateUi? = null
        fun subscribeForTopic(topic: String) {
            FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener { task: Task<Void?> ->
                    if (!task.isSuccessful) {
                        Log.d(
                            TAG,
                            "Subscription $topic Failed"
                        )
                    } else Log.d(
                        TAG,
                        "Subscription $topic Successful"
                    )
                }
        }

        fun unsubscribeForTopic(topic: String) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                .addOnCompleteListener { task: Task<Void?> ->
                    if (!task.isSuccessful) {
                        Log.d(
                            TAG,
                            "UnSubscription $topic Failed"
                        )
                    } else Log.d(
                        TAG,
                        "UnSubscription $topic Successful"
                    )
                }
        }
    }

    @SuppressLint("ResourceType", "RemoteViewLayout")
    private fun mShowNotification(
        mTitle: String,
        mBody: String,
        type: String,
        refrenceId: Int
    ) {
//        var notificationImage: Int? = null
//        if (type == NotificationTypeId.TOP_UP.value) {
//            notificationImage = R.drawable.ic_wallet_recharge_sucessfull_notification
//        } else if (type == NotificationTypeId.WAYJA_INVITATION_ACCPET.value || type == NotificationTypeId.WAYJA_INVITATION_REQUEST.value) {
//            notificationImage = R.drawable.ic_invite_notification
//
//        } else {
//            notificationImage = R.drawable.ic_wayja_notification


//        }
        var pendingIntent : PendingIntent?=null
        Log.e("firebaseLoaded_", "Message data payload: " + refrenceId + " notificationType" + type)
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("type", type)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE
            )

        } else {
             pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )

        }



//        val contentView = RemoteViews(packageName, R.layout.custom_push)
//        contentView.setImageViewResource(R.id.image, notificationImage)
//        contentView.setTextViewText(R.id.title, mTitle)
//        contentView.setTextViewText(R.id.text, mBody)
        val channelId = "bb_App_User_2"
        val channelName = "WAYJA_USER_saurav"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId).apply {
            setContentTitle(mTitle)
            setContentText(mBody)
            setContent(contentView)
            setAutoCancel(true)
           setContentIntent(pendingIntent)
            setSmallIcon(R.drawable.cnlogo)
        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            channel.setSound(defaultSoundUri, audioAttributes)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }


}


