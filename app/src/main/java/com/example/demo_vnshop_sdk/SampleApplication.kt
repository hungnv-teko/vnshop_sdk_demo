package com.example.demo_vnshop_sdk

import android.app.Application
import vn.teko.terra.core.android.terra.TerraApp

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        TerraApp.initializeApp(
            application = this,
            clientId = "icheck:android:playstore:0.0.1",
            defaultConfigName = "icheck_pandora_config.json"
        )
    }
}