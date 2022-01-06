package com.example.katalogfilm_byfadli.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.katalogfilm_byfadli.ui.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2_000)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}