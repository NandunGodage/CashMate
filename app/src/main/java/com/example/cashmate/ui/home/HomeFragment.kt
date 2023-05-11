package com.example.cashmate.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cashmate.AddExpencess
import com.example.cashmate.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.ExpenseCard
    homeViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }

      // Add a reference to the floating action button
      val fabButton: FloatingActionButton = binding.addBank
      // Set an onClickListener for the floating action button

      fabButton.setOnClickListener {
          // Create an intent to launch the Create_Payment activity

      }

      // Add a reference to the addcategory floating action button
      val catButton: FloatingActionButton = binding.addCategory

      // Set an onClickListener for the floating action button
      catButton.setOnClickListener {
          val intent = Intent(activity, AddExpencess::class.java)
          startActivity(intent)
          // Create an intent to launch the Create_Payment activity

      }




      val income : Button = binding.button3

      // Set an onClickListener for the floating action button
      income.setOnClickListener {
          // Create an intent to launch the Create_Payment activity
          val intent = Intent(activity, AddExpencess::class.java)
          startActivity(intent)
      }





      return root
  }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class FragmentHomeBinding {

}
