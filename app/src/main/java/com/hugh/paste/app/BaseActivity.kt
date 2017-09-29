package com.hugh.paste.app

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by hugh on 2017/9/29.
 */
open class BaseActivity: AppCompatActivity() {

    /** activity是否已销毁 */
    private var isDestroy = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activities.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        activities.remove(this)
    }

    /** activity是否已销毁 */
    open fun isDestroy():Boolean {
        return isDestroy
    }

    companion object {
        private var msgBase = 100
        protected val activities = mutableListOf<Activity>()

        /** 仅供获取handler msg id */
        fun getMsgId(): Int {
            return msgBase++
        }

        /**
         * 获取栈顶activity
         * @return
         */
        fun getTopActivity(): Activity? {
            return if (!activities.isEmpty()) activities.last() else null
        }

        /**
         * 关闭指定的activity

         * @param actClass
         */
        fun finishActivity(actClass: Class<*>) {
            activities.forEach {
                if (it.javaClass == actClass) {
                    it.finish()
                }
            }
        }

        /**
         * 关闭所有activity
         */
        fun finishAllActivity() {
            activities.forEach {
                it.finish()
            }
        }

        /**
         * 关闭某个activity之前的所有activity
         *
         * @param actClass
         */
        fun finishToActivity(actClass: Class<*>) {
            for (i in activities.size - 1 downTo 0) {
                val activity = activities[i]
                if (activity.javaClass != actClass && !activity.isFinishing) {
                    activity.finish()
                } else {
                    break
                }
            }
        }
    }
}