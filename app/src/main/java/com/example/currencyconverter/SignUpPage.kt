package com.example.currencyconverter

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up_page.*


class SignUpPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        submit.setOnClickListener{
            registerNewUser()
        }

    }

    private fun registerNewUser() {
        val firstName = first_name.text.toString()
        val lastName = last_name.text.toString()
        val mailAddr = email.text.toString()
        val dob = DOB.text.toString()
        val pwd = password.text.toString()
        val confirmPWD = confirm_password.text.toString()


        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "Please enter First Name", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Please enter Last Name", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(mailAddr)) {
            Toast.makeText(this, "Please enter E-mail", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Please enter Date of Birth", Toast.LENGTH_LONG).show()
            return
        }
        if (pwd != confirmPWD) {
            Toast.makeText(this, "Password did not match", Toast.LENGTH_LONG).show()
            return
        }

        val mAuth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()
        val ref = database.reference.child("Users")

        mAuth.createUserWithEmailAndPassword(mailAddr, pwd).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userID = mAuth.currentUser!!.uid
                val currentUser = ref.child(userID)

                currentUser.child("firstName").setValue(firstName)
                currentUser.child("lastName").setValue(lastName)
                currentUser.child("dob").setValue(dob)
                currentUser.child("e-mail").setValue(mailAddr)
                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()

                val loginIntent = Intent(this, LoginPage::class.java)
                startActivity(loginIntent)
            } else {
                Toast.makeText(this, "Signup Failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}
