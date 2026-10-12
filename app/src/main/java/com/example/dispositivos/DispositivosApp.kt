package com.example.dispositivos

import android.app.Application
import com.google.android.material.color.DynamicColors

class DispositivosApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
