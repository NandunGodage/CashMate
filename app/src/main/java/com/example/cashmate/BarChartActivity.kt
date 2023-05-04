package com.example.cashmate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cashmate.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class BarChartActivity : AppCompatActivity() {

    lateinit var barChart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        barChart=findViewById(R.id.bar_chart)

        var list:ArrayList<BarEntry> = ArrayList()

        list.add(BarEntry(100f,100f))
        list.add(BarEntry(101f,101f))
        list.add(BarEntry(102f,102f))
        list.add(BarEntry(103f,103f))
        list.add(BarEntry(104f,104f))

        val barDataSet = BarDataSet(list,"List")
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,225)
        barDataSet.valueTextColor=Color.BLACK

        var barData = BarData(barDataSet)
        barChart.setFitBars(true)
        barChart.data=barData
        barChart.animateY(2000)

    }
}