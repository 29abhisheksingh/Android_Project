package com.example.currencyconverter


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_market_status_page.*

class MarketStatusPage : NavigationPane() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_status_page)

        val mDrawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        onCreateDrawer(mDrawerLayout)
        base_currency.text= homeFeed.source
        val arrayAdapter:ArrayAdapter<*>
        val array = ArrayList<String>()
        for (i in homeFeed.quotes.keySet()){
            array.add("$i : ${homeFeed.quotes[i]}")
        }

        val mListView = findViewById<ListView>(R.id.userlist)
        arrayAdapter = ArrayAdapter(this , android.R.layout.simple_list_item_1 , array)
        mListView.adapter = arrayAdapter

    }
}