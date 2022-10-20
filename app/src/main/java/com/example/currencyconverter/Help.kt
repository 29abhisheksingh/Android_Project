package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout

class Help : NavigationPane() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var help_page_ans_basecurr:TextView
        lateinit var help_page_ans_numcurr:TextView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        val mDrawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)
        help_page_ans_numcurr.text = homeFeed.quotes.keySet().size.toString()
        help_page_ans_basecurr.text = homeFeed.source

    }
}