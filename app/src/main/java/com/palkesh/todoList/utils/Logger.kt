package com.palkesh.todoList.utils

import com.palkesh.todoList.BuildConfig

object Logger {

    private var isLogToShow = BuildConfig.DEBUG

    fun i(tag: String, string: String) {
        if (isLogToShow) android.util.Log.i(tag, string)
    }

    fun e(tag: String, string: String) {
        if (isLogToShow) android.util.Log.e(tag, string)
    }

    fun d(tag: String, string: String) {
        if (isLogToShow) android.util.Log.d(tag, string)
    }

    fun v(tag: String, string: String) {
        if (isLogToShow) android.util.Log.v(tag, string)
    }

    fun w(tag: String, string: String) {
        if (isLogToShow) android.util.Log.w(tag, string)
    }
}
