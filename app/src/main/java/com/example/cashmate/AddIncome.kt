package com.example.cashmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cashmate.ui.home.HomeFragment
import com.example.influxapp.model.Income
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddIncome : AppCompatActivity() {
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        // Get a reference to your Firebase Realtime Database
        databaseRef = FirebaseDatabase.getInstance().getReference("income")

        // Get references to the EditText views
        val categoryEditText = findViewById<EditText>(R.id.i_category)
        val amountEditText = findViewById<EditText>(R.id.i_amount)

        // Set an onClickListener for the submit button
        val submitButton = findViewById<Button>(R.id.add)
        submitButton.setOnClickListener {
            // Get the values from the EditTexts
            val category = categoryEditText.text.toString().trim()
            val amount = amountEditText.text.toString().toDoubleOrNull()

            // Check if the amount is valid
            if (amount == null) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Create a new Income object with the values
            val income = Income(category,  amount)

            // Add the Income object to the Firebase Realtime Database
            databaseRef.push().setValue(income)
                .addOnSuccessListener {
                    // Show a success message to the user
                    Toast.makeText(this, "Income added successfully", Toast.LENGTH_LONG).show()

                    // Clear the EditText fields
                    categoryEditText.text.clear()
                    amountEditText.text.clear()

                    // Navigate to the home screen
                    val intent = Intent(this, ReadIncome::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)

                }
                .addOnFailureListener {
                    // Show an error message to the user
                    Toast.makeText(this, "Failed to add income", Toast.LENGTH_LONG).show()
                }
        }
    }
}
