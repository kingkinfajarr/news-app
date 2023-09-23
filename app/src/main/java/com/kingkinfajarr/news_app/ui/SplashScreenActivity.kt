package com.kingkinfajarr.news_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kingkinfajarr.news_app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }

}