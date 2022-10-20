package com.example.currencyconverter

import android.os.Bundle
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout

class ResultPage : NavigationPane() {
    lateinit var result_page: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)
        val mDrawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
       onCreateDrawer(mDrawerLayout)
        result_page = findViewById(R.id.result_page)
        val result = intent.getStringExtra("key")
        result_page.text = result

    }
}



