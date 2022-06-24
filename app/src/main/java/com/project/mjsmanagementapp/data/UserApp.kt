package com.project.mjsmanagementapp.data

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho


class UserApp : Application(){
    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        private lateinit var appContext: Context
        fun getContext() = appContext?.applicationContext
    }
}