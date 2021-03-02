package com.example.demo_vnshop_sdk

import android.app.Application
import kotlinx.coroutines.runBlocking
import vn.teko.terra.core.android.pandora.model.TerraConfig
import vn.teko.terra.core.android.terra.TerraApp

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        runBlocking {
            TerraApp.initializeApp(
                application = this@SampleApplication,
                appName = "SampleApplication",
                terraConfig = TerraConfig(
                    terraClientId = "icheck:android:playstore:0.0.2",
                    terraEnvironment = TerraConfig.TerraEnv.Stage
                )
            )
        }
    }
}