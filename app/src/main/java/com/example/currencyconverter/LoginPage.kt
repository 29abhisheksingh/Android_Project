package com.example.currencyconverter

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_page.*

//
class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show()

        sign_up_button.setOnClickListener {
            val signUpIntent = Intent(this, SignUpPage::class.java)
            startActivity(signUpIntent)
        }

        login_button.setOnClickListener {
            loginUserAccount()
        }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Please Login", Toast.LENGTH_LONG).show()
        return
    }

    private fun loginUserAccount() {
        val userId = userID.text.toString()
        val password = password.text.toString()

        if (TextUtils.isEmpty(userId)) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show()
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_LONG).show()
        }

        val mAuth = FirebaseAuth.getInstance()

        mAuth.signInWithEmailAndPassword(userId, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val homeIntent = Intent(this, HomePage::class.java)
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                    startActivity(homeIntent)
                } else {
                    Toast.makeText(
                        this,
                        "Login Failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
    }
}


