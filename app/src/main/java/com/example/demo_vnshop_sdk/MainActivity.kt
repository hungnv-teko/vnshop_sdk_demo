package com.example.demo_vnshop_sdk

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import vn.teko.android.auth.core.TerraAuth
import vn.teko.android.auth.login.TerraLogin
import vn.teko.android.auth.login.provider.AUTH_MANAGER_EXTRA_CUSTOM_TOKEN_IDTOKEN
import vn.teko.android.auth.login.provider.AUTH_MANAGER_EXTRA_CUSTOM_TOKEN_PROVIDER
import vn.teko.android.auth.login.provider.AUTH_MANAGER_RC_LOGIN
import vn.teko.android.auth.login.provider.LoginType
import vn.teko.android.core.util.Result
import vn.teko.hestia.android.TerraHestia
import vn.teko.hestia.android.utils.uiHelper.DefaultAndroidHestiaUIHelper
import vn.teko.terra.core.android.terra.TerraApp

class MainActivity : AppCompatActivity() {
    private val terraApp = TerraApp.getInstance()
    private val authManager = TerraAuth.getInstance(terraApp)
    private val loginManager = TerraLogin.getInstance(terraApp)
    private val terraHestia = TerraHestia.getInstance(terraApp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button1).setOnClickListener {
            silentLogin()
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            openVnshop()
        }
    }

    private fun silentLogin() {
        loginManager.login(this@MainActivity, LoginType.CUSTOM_TOKEN)
        val data = Intent().apply {
            putExtra(
                    AUTH_MANAGER_EXTRA_CUSTOM_TOKEN_IDTOKEN,
                    "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjBkNTdhMGJlLTNlZDItNDJhMi1iM2U1LTM2Y2E3ZjkzMjEyMjQifQ.eyJzdWIiOjExMjM2OCwibmFtZSI6IlR14bqlbiBOZ3V54buFbiIsImVtYWlsIjoidHVhbkBnbWFpbC5jb20iLCJwaG9uZV9udW1iZXIiOiIwOTg3NjYzOTY3IiwicGljdHVyZSI6Imh0dHBzOi8vYXNzZXRzLmljaGVjay52bi9tdWx0aXBhcnQvMjAyMC9hcHAwMS85LzhhN2IwY2Y4NjFhOGQ1YzgwYjQzZTUzMWFkNjljODM0LnBuZyIsImFkZHJlc3MiOiIzNjggQ-G6p3UgR2nhuqV5IiwiYmlydGhkYXkiOiIxMi8yLzIwMDAiLCJhdWQiOiJpY2hlY2siLCJpYXQiOjE2MDY0NDg4ODQsImV4cCI6MTYwNjYyMTY4NCwiaXNzIjoibXlpc3N1ZXJuYW1lIn0.OncjCx0mYiGrTBbfyXKzGmGQWoelB11T5JO9oAYOFLtxxMkKBLkYszT2beEST8kKyFshdJ1HrkCIb25vAaeD3Q"
            )
            putExtra(AUTH_MANAGER_EXTRA_CUSTOM_TOKEN_PROVIDER, "icheck")
        }
        loginManager.processLoginResult(AUTH_MANAGER_RC_LOGIN, Activity.RESULT_OK, data) {result ->
            when(result) {
                is Result.Success -> {
                    //Login Success
                }
                is Result.Failure -> {
                    //Login Failure
                }
            }
        }
    }

    private fun openVnshop() {
        terraHestia.startApp(
                "vnshop",
                authManager,
                DefaultAndroidHestiaUIHelper(this)
        )
    }
}