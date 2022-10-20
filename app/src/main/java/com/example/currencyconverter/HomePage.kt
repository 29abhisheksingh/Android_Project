package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.drawerlayout.widget.DrawerLayout
import com.google.gson.GsonBuilder
import java.io.IOException
import  kotlinx.android.synthetic.main.activity_home_page.*
import okhttp3.*



class HomePage : NavigationPane() {

    private var mDelayHandler: Handler?=null
    private val splashDelay: Long=3000
    //val original = listOf("orange", "apple")




    private val mRunnable = Runnable{
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, homeFeed.quotes.keySet().toList())
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item)
        with(fromCurr){
            adapter = aa
            setSelection(0,true)
            gravity = android.view.Gravity.CENTER
        }
        with(toCurr){
            adapter = aa
            setSelection(0,true)
            gravity = android.view.Gravity.CENTER

        }

    }

    private fun fetchJson() {
        print("Attempting")
        val url = "https://api.apilayer.com/currency_data/live?source=&currencies="

//        Log.d("HomePage", "fetchJson:url"+"https://api.apilayer.com/fixer/symbols")

        Log.d("HomePage", "fetchJson: " + url)
        var request = Request.Builder()
//            .url("https://api.apilayer.com/fixer/symbols")
            .url(url)
            .addHeader("apikey", "tm75LqhB1cUGF8Eg7BZ8UyHdqVRYwedm")
            .build()
        var client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                Log.d("Response Body", body.toString())
                val gson = GsonBuilder().create()
                homeFeed = gson.fromJson(body, HomeFeed::class.java)
                print("Success")
            }

        })
    }
//        client.newCall(request).enqueue(object : Callback {
//
//            override fun onResponse(call: Call?, response: Response?) {
//                Log.d("HomePage", "onResponse: ")
//              //  val body = response?.body()?.string()
//                //val gson = GsonBuilder().create()
//                //homeFeed = gson.fromJson(body, HomeFeed::class.java)
//                print("Success")
//            }
//
//            override fun onFailure(call: Call?, e: IOException?) {
//            print("Failed to execute request")
//            }
//        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
            val mDrawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
            onCreateDrawer(mDrawerLayout)
            fetchJson()


        convertBtn.setOnClickListener{
            val resultIntent = Intent(this, ResultPage::class.java )
            if(enterAmt.text!=null ) {
                val amount = enterAmt.text.toString()
                val finalValue = conversion(fromCurr.selectedItem.toString(), toCurr.selectedItem.toString(),
                    amount
                )
                resultIntent.putExtra("key", finalValue.toString())
                startActivity(resultIntent)
            }
        }

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable,splashDelay)
            }
    private fun conversion(from:String,to:String,amount:String) :Float{
        val fromCurrRate = homeFeed.quotes[from].toString()
        val toCurrRate = homeFeed.quotes[to].toString()
        return ((amount.toFloat()* toCurrRate.toFloat()) /fromCurrRate.toFloat())
    }
}