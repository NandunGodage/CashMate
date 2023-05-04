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

        goBarChart=findViewById(R.id.go_bar_chart)
        goPieChart=findViewById(R.id.go_pie_chart)

        goBarChart.setOnClickListener{
            startActivity(Intent(this,BarChartActivity:: class.java))

        }
        goPieChart.setOnClickListener{
            startActivity(Intent(this,PieChartActivity:: class.java))

        }
    }
}