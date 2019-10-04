package com.example.timernav.utils

import android.app.Application
import android.content.Context


class AppAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: Application? = null

        val context: Context
            get() = instance!!.applicationContext
    }
}