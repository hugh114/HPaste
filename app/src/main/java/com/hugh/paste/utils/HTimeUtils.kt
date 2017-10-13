package com.hugh.paste.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hugh on 2017/10/9.
 */
class HTimeUtils {

    companion object {

        /**
         * 毫秒与毫秒的倍数
         */
        val MSEC = 1
        /**
         * 秒与毫秒的倍数
         */
        val SEC = 1000
        /**
         * 分与毫秒的倍数
         */
        val MIN = 60000
        /**
         * 时与毫秒的倍数
         */
        val HOUR = 3600000
        /**
         * 天与毫秒的倍数
         */
        val DAY = 86400000

        val COMMON_DATE_SDF = SimpleDateFormat("MM月dd日 HH:mm:ss", Locale.getDefault())
        val COMMON_DATE_YEAR_SDF = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.getDefault())

        /**
         * 通用时间字符串
         *
         * 时间<1分钟，显示 刚刚
         * 1分钟<=时间<1小时，显示 N分钟前
         * 1小时<=时间<24小时，显示 N小时前
         * 1天<=时间<6天，显示 N天前
         * 6天<=时间且未跨年 MM月dd日
         * 6天<=时间且跨年 yyyy年MM月dd日
         *
         * @param time 评论消息时间 秒
         * @return
         */
        fun getCommonTimeStr(time: Long = System.currentTimeMillis()): String {
            val now = System.currentTimeMillis()
            val second = (now - time)

//            return if (second < MIN) {
//                "刚刚"
//            } else if (second < HOUR) {
//                (second / MIN).toString() + "分钟前"
//            } else if (second < DAY) {
//                (second / HOUR).toString() + "小时前"
//            } else if (second < DAY * 6L) {
//                (second / DAY) .toString()+ "天前"
//            } else {
                val c1 = Calendar.getInstance()
                c1.time = Date(now)
                val c2 = Calendar.getInstance()
                c2.time = Date(time)
                return if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
                    // 同一年显示 MM月dd日
                    ms2String(time, COMMON_DATE_SDF)
                } else {
                    // 跨年 yyyy年MM月dd日
                    ms2String(time, COMMON_DATE_YEAR_SDF)
                }
//            }
        }

        /**
         * 将时间戳转为时间字符串
         *
         * 格式为用户自定义
         *
         * @param milliseconds 毫秒时间戳
         * @param format       时间格式
         * @return 时间字符串
         */
        fun ms2String(milliseconds: Long, format: SimpleDateFormat): String {
            return format.format(Date(milliseconds))
        }
    }
}