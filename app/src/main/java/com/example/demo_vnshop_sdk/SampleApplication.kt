package com.example.demo_vnshop_sdk

import android.app.Application
import vn.teko.terra.core.android.terra.TerraApp

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        TerraApp.initializeApp(
            application = this,
            clientId = "cyhome:android:playstore:0.0.1",
            defaultConfigName = "cyhome_pandora_config.json"
        )
    }
}