package com.hugh.paste

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.ClipboardManager
import android.content.Intent
import android.os.IBinder
import com.hugh.paste.app.BaseActivity
import com.hugh.paste.utils.HPrefs


/**
 * Created by hugh on 2017/9/29.
 */
class HPasteService: Service() {

    override fun onCreate() {
        super.onCreate()
        startService()
        startClipListener()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startService() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val content = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = Notification.Builder(this)
                .setContentTitle("HPaste")
                .setContentText("Clip Service is Running")
                .setContentIntent(content)
                .setSmallIcon(R.mipmap.ic_logo)
                .setWhen(System.currentTimeMillis())
                .build()
        startForeground(1, notification)
    }

    private fun startClipListener () {
        val clipMgr = getSystemService(Service.CLIPBOARD_SERVICE) as ClipboardManager
        clipMgr.addPrimaryClipChangedListener {
            if (!BaseActivity.isForeground()) {
                //非应用内粘贴
                val item = clipMgr.primaryClip.getItemAt(0)
                HPrefs.addContent(item.text.toString())
                System.out.println("got clip: ${item.text}")
            }
        }
    }
}