package com.example.cashmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.FragmentNavigator
import com.example.cashmate.ui.home.HomeFragment

class SignInPage : AppCompatActivity() {

    lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText
    lateinit var keepMeLog: CheckBox
    lateinit var btnSignIn: Button
    lateinit var btnSignUp: Button
    lateinit var forgetPass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        keepMeLog = findViewById(R.id.checkBoxKeepLogin)
        btnSignIn = findViewById(R.id.btnSign)
        btnSignUp = findViewById(R.id.btnSign2)


        btnSignUp.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignIn.setOnClickListener {
            var intent = Intent(this, Navigation::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun checkUserIdentity() {

    }
}