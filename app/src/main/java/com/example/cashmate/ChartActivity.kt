package com.example.cashmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChartActivity : AppCompatActivity() {
    lateinit var goBarChart: Button
    lateinit var goPieChart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        goBarChart = findViewById(R.id.go_bar_chart)
        goPieChart = findViewById(R.id.go_pie_chart)

        goBarChart.setOnClickListener {

            println("goBarChart.setOnClickListener")
            var intent = Intent(this, BarChartActivity::class.java)
            startActivity(intent)
            finish()
        }
        goPieChart.setOnClickListener {
            println("goPieChart.setOnClickListener")
            var intent = Intent(this, PieChartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}