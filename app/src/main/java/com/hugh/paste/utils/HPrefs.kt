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

        val KEY_CONTENT = "content"

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

        fun addContent(value: String?) {
            if (TextUtils.isEmpty(value?.trim())) {
                return
            }

            val str = getContent()
            val sb = StringBuilder()
            sb.append(value?.trim()).append("\n\n")
            if (!TextUtils.isEmpty(str)) {
                sb.append(str)
            }

            val editor = getEditor(SP_CONTENT)
            editor?.putString(KEY_CONTENT, sb.toString())?.apply()
        }

        fun clearContent() {
            val editor = getEditor(SP_CONTENT)
            editor?.remove(KEY_CONTENT)?.apply()
        }
    }

}