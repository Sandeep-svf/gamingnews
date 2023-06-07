package com.bb.gamingnews

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.bb.gamingnews.api.ApiClient
import com.bb.gamingnews.koin.appModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        FirebaseApp.initializeApp(this)
        startKoin {
            ApiClient.context = this@MyApplication
            androidContext(this@MyApplication)
            modules(listOf(appModule))
        }
    }
}