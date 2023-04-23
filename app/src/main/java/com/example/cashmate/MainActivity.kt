package com.example.cashmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            //food n drink button
            val buttonFnD = findViewById<ImageView>(R.id.btnFnD) //declare the button as val and type is ImageView
            buttonFnD.setOnClickListener {
                // write what happen when you click the button

                intent = Intent(this, ExpCategory::class.java)  //made intent and linked to activity

                startActivity(intent)
            }

            //med button
            val buttonMed = findViewById<ImageView>(R.id.btnMed)
            buttonMed.setOnClickListener {
                 intent = Intent( this, ExpCategory::class.java)
                startActivity(intent)
            }

            //shopping button
            val buttonShop = findViewById<ImageView>(R.id.btnShop)
            buttonShop.setOnClickListener {
                intent = Intent( this, ExpCategory::class.java)
                startActivity(intent)
            }

            //transport button
            val buttonTrans = findViewById<ImageView>(R.id.btnTrans)
            buttonTrans.setOnClickListener {
                intent = Intent( this, ExpCategory::class.java)
                startActivity(intent)
            }

            //other button
            val buttonOther = findViewById<ImageView>(R.id.btnOther)
            buttonOther.setOnClickListener {
                intent = Intent( this, ExpCategory::class.java)
                startActivity(intent)
            }

            //add income
            val buttonAddIncome = findViewById<ImageView>(R.id.addIncome)
            buttonAddIncome.setOnClickListener {
                intent = Intent( this,activity_overview::class.java)
                startActivity(intent)
            }


            /* //add card
            val buttonAddCard = findViewById<ImageView>(R.id.addCard)
            buttonAddCard.setOnClickListener {
                intent = Intent( this,"add the addcard activity here"::class.java)
                startActivity(intent)
            } */
        }
}