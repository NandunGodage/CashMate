package com.example.cashmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class EditExpencess : AppCompatActivity() {
    private lateinit var cate: TextView
    private lateinit var amou: TextView
    private var recordId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_income)

        // Initialize the TextViews
        cate = findViewById(R.id.e_category)
        amou = findViewById(R.id.e_amount)

        // Initialize the Buttons
        var submitButton = findViewById<Button>(R.id.edit)
        var cancelButton = findViewById<Button>(R.id.cancel)

        // Get a reference to your Firebase database
        val database = FirebaseDatabase.getInstance().reference

        // Query the database to retrieve the last row of data
        database.child("Expencess").orderByKey().limitToLast(1).get()
            .addOnSuccessListener { dataSnapshot ->
                // Update the values of that row with the new data
                // Get the first child node
                val lastChild = dataSnapshot.children.last()
                // Store the record ID as a class-level variable
                recordId = lastChild.key
                // Get the values of the child nodes and convert them to strings
                val category = lastChild.child("category").value?.toString()
                val amount = lastChild.child("amount").value?.toString()

                // Set the values of the TextViews
                cate.text = category
                amou.text = amount

                // Set up the submit button onClick listener
                submitButton.setOnClickListener {
                    // Get the updated input values
                    val category = cate.text.toString()
                    val amount = amou.text.toString()

                    // Update the income record with the new values
                    lastChild.ref.updateChildren(
                        mapOf(
                            "category" to category,
                            "amount" to amount,
                        )
                    )
                    // Show a toast message indicating that the record was updated
                    Toast.makeText(this@EditExpencess, "Record updated successfully", Toast.LENGTH_SHORT).show()
                    // Finish the activity and return to the previous screen
                    finish()
                }

            }.addOnFailureListener { exception ->
                // Handle any errors that occur
            }
    }
}
