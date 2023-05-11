package com.example.cashmate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cashmate.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class PieChartActivity : AppCompatActivity() {

    lateinit var pieChart:PieChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        pieChart=findViewById(R.id.pie_chart)

        val list: ArrayList<PieEntry> =ArrayList()

        list.add(PieEntry(100f,100f))
        list.add(PieEntry(101f,101f))
        list.add(PieEntry(102f,102f))
        list.add(PieEntry(103f,103f))
        list.add(PieEntry(104f,104f))

        val pieDataSet = PieDataSet(list,"List")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,225)
        pieDataSet.valueTextColor= Color.BLACK

        var pieData = PieData(pieDataSet)
        pieChart.data=pieData
        pieChart.description.text="Pie Chart"
        pieChart.centerText="List"
        pieChart.animateY(2000)

    }
}