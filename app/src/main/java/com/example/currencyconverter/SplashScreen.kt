package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    private var mDelayHandler: Handler? =null
    private val splashDelay: Long=3000
    private val mRunnable:Runnable = Runnable{
        val loginIntent = Intent(this, LoginPage::class.java)
        finish()
        startActivity(loginIntent)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable,splashDelay)

    }
    public override fun onDestroy(){
        if(mDelayHandler!=null)
        {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}