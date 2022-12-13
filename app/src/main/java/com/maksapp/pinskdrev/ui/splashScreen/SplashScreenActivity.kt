package com.maksapp.pinskdrev.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.ui.viewPager.ViewPagerActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, ViewPagerActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // delaying 3 sec to open main activity
    }
}