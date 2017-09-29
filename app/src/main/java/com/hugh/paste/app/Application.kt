package com.hugh.paste.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.hugh.paste.utils.HPrefs

/**
 * Created by hugh on 2017/9/29.
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        if (inMainProcess()) {
            HPrefs.init(this)
        }
    }

    /**
     * 是否在主进程
     *
     * @return
     */
    private fun inMainProcess(): Boolean {
        val packageName = packageName
        val processName = getProcessName(this)
        return packageName == processName
    }

    /**
     * 获取当前进程名称
     *
     * @param context
     * @return
     */
    private fun getProcessName(context: Context): String? {
        var processName: String? = null

        // ActivityManager
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        while (true) {
            for (info in am.runningAppProcesses) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName

                    break
                }
            }

            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName
            }

            // take a rest and again
            try {
                Thread.sleep(100L)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }

        }
    }
}