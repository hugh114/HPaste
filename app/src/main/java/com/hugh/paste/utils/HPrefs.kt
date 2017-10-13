package com.hugh.paste.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import org.w3c.dom.Text

/**
 * Created by hugh on 2017/9/29.
 */
class HPrefs {
    companion object {
        val SP_CONFIG = "config"
        val SP_CONTENT = "content"
        val SP_TIME = "time"

        val KEY_CONTENT = "content"
        val KEY_TIME = "time"

        private var appContext: Context? = null
        private var cache: SharedPreferences? = null

        fun init(context: Context) {
            appContext = context.applicationContext
        }

        fun getPrefs(name: String, mode: Int = Context.MODE_PRIVATE): SharedPreferences? {
            return appContext?.getSharedPreferences(name, mode)
        }

        fun getEditor(name: String, mode: Int = Context.MODE_PRIVATE): SharedPreferences.Editor? {
            return getPrefs(name, mode)?.edit()
        }

        fun getContent(): String {
            val prefs = getPrefs(SP_CONTENT)
            return prefs?.getString(KEY_CONTENT, "") ?: ""
        }

        fun getTime(): String {
            val prefs = getPrefs(SP_TIME)
            return prefs?.getString(KEY_TIME, "") ?: ""
        }

        fun addContent(value: String?) {
            if (TextUtils.isEmpty(value?.trim())) {
                return
            }

            //add content
            val str = getContent()
            val sb = StringBuilder()
            sb.append(value?.trim()).append("\n\n\n")
            if (!TextUtils.isEmpty(str)) {
                sb.append(str)
            }

            val editor = getEditor(SP_CONTENT)
            editor?.putString(KEY_CONTENT, sb.toString())?.apply()

            //add time
            val str1 = getTime()
            val sb1 = StringBuilder()

            sb1.append(value?.trim()).append("\n").append(HTimeUtils.getCommonTimeStr()).append("\n\n")
            if (!TextUtils.isEmpty(str1)) {
                sb1.append(str1)
            }

            val editor1 = getEditor(SP_TIME)
            editor1?.putString(KEY_TIME, sb1.toString())?.apply()
            System.out.println("addContent time:${sb1.toString()}")
        }

        fun clearContent() {
            //clear content
            var editor = getEditor(SP_CONTENT)
            editor?.remove(KEY_CONTENT)?.apply()
            //clear time
            editor = getEditor(SP_TIME)
            editor?.remove(KEY_TIME)?.apply()
        }
    }

}