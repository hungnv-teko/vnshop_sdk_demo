package com.example.demo_vnshop_sdk

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
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
                    "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjU5MTAyOGNjLWIwZjEtNDJiZC1hMjI4LTM2Zjk2NDgyOGNhNSJ9.eyJpc3MiOiJjeWhvbWVpc3N1ZXIiLCJzdWIiOjc2NjgsImlhdCI6MTYzODM0MDQ1MywiZXhwIjoxNjM4MzQwNDUzLCJwaG9uZV9udW1iZXIiOiIrODQ5NjkxMzc4NzQiLCJhdWQiOiJjeWhvbWUifQ.guCVWWVSqOp1U7TI6IBrvd_X8T9r3JrA0wMTsfjkMcHIuZZSqjkj-WPMH1gtsMHECn2DBHBA7CPzhGLKUEJBZ-5796frH3quO8oPJLVFloX_LDJ1jUc0tAIDoQ1IRETkiYzwdjKymoL-EWDlQCMj7E5mjxRSx4mnmihaQ14NW99m2Wcx9XeA5nKoZ5on4jaMxtQ0FWVl5EQVDy01E85RjLTvCTy5IgG7h4MEnKC1EOfPw-zN14MtdzIhy81WYzDrMVnnb3sQdjPd2Ymo4st9xs3eohq4NVH6D8Urqq0Q-PJNcK271LHFvIWKuLq9yB3jCKbtWQ6tQ3VePfTVhBBfPvN1HWK0YOHzX-HRYJAa6Fyd7t08vvyuoyCfZKRMjNO3yut_zb4uw4JYvJUt8x5f8Kt9v93KVWw8Bq6C7hIQbPF8_JQdCoJbHQo8DpJkA68ZaJjtEmN-ibkmELwZlEMDmJTYC8LVVjocEu6OiLFOQ8K82PuBpTxJbaMlbFDzS5b1RL3bshL1YuES3tyU7sETceK4kwt8LyBQ3BqHAkbQZKOYIrfOYl-d2Ft014OBxcjFk4RvsdPW92gC8_2MgfhOjz6PjvHeB_xiXyJLrMjuoEOA1lJnVLUcbQHWrvaScihIwwvwozrSruylrJy2fwcmFRXihaIeQu_KKIoqLfN4O0o"
            )
            putExtra(AUTH_MANAGER_EXTRA_CUSTOM_TOKEN_PROVIDER, "cyhome")
        }
        loginManager.processLoginResult(AUTH_MANAGER_RC_LOGIN, Activity.RESULT_OK, data) {result ->
            when(result) {
                is Result.Success -> {
                    AlertDialog.Builder(this).setMessage("Login successfully").setPositiveButton("ok") { _, _ -> }
                        .show()
                }
                is Result.Failure -> {
                    val error = result.exception()
                    AlertDialog.Builder(this).setMessage("Login error: $error").setPositiveButton("ok") { _, _ -> }
                        .show()
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