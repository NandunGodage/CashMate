package com.example.cashmate.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cashmate.BarChartActivity
import com.example.cashmate.PieChartActivity
import com.example.cashmate.R
import com.example.cashmate.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var goBarChart: Button
    lateinit var goPieChart: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        goBarChart = root.findViewById(R.id.go_bar_chart)
        goPieChart = root.findViewById(R.id.go_pie_chart)

        goBarChart.setOnClickListener {
            println("goBarChart.setOnClickListener")
            var intent = Intent(activity, BarChartActivity::class.java)
            startActivity(intent)

        }

        goPieChart.setOnClickListener {
            println("goPieChart.setOnClickListener")
            var intent = Intent(activity, PieChartActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}