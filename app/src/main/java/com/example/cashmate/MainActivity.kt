package com.example.cashmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonFnD = findViewById<ImageView>(R.id.btnFnD) //declare the button as val and type is ImageView
        buttonFnD.setOnClickListener {
            // write what happen when you click the button

            intent = Intent(this, ExpCategory::class.java)  //made intent and linked to activity

            startActivity(intent)

        }
    }
}