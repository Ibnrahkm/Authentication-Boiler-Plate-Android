package com.ibrahim.basicboilerplate.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.ibrahim.basicboilerplate.R

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable { ->
            finish()
            startActivity(Intent(this@SplashActivity, AuthenticationActivity::class.java))

        }, 3000)
    }

}
