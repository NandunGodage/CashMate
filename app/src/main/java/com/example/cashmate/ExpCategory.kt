package com.example.cashmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class ExpCategory  : AppCompatActivity() {
    //new code
    val spinnerInEx : Spinner = findViewById(R.id.dropInEx)
    private val option1 = arrayOf("Income", "Expense")
    val adapter1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, option1)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter1

    val spinnerCat : Spinner = findViewById(R.id.dropCat)
    private val option2 = arrayOf("Food & Drink ", "Medicine", "Shopping", "Transportation", "Other")
    val adapter2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, option2)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter2
    //end of new code






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp_category)

    }
}

