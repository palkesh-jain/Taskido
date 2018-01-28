package com.palkesh.todoList.utils

import android.content.SharedPreferences
import com.palkesh.todoList.TaskidoApplication

object PreferenceManager {

    private val mSharedPreferences: SharedPreferences
    private val mEditor: SharedPreferences.Editor
    private const val MODE: Int = 0

    init {
        mSharedPreferences = TaskidoApplication.instance.getSharedPreferences(TaskidoApplication.instance.packageName, MODE)
        mEditor = mSharedPreferences.edit()
    }

    fun putBoolean(key: String, value: Boolean) {
        mEditor.putBoolean(key, value)
        mEditor.commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    fun putString(key: String, value: String) {
        mEditor.putString(key, value)
        mEditor.commit()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return mSharedPreferences.getString(key, defaultValue)
    }

}
