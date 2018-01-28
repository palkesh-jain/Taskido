package com.palkesh.todoList

import android.app.Application

class TaskidoApplication : Application() {

    companion object {
        lateinit var instance: TaskidoApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}
