package com.example.cashmate

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReadIncome : AppCompatActivity() {

    private lateinit var cate: TextView
    private lateinit var amou: TextView
    private var recordId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_income)

        cate = findViewById(R.id.r_category)
        amou = findViewById(R.id.r_amount)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("income").limitToLast(1)

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val lastchild = dataSnapshot.children.last() // get the first child node
                recordId = lastchild.key // store the record ID as a class-level variable
                val category = lastchild.child("category").value?.toString()
                val amount = lastchild.child("amount").value?.toString()

                cate.text = category
                amou.text = amount
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        val deleteButton: Button = findViewById(R.id.Delete)
        deleteButton.setOnClickListener {
            val databaseReference = FirebaseDatabase.getInstance().getReference("income")
            val recordReference = databaseReference.child(recordId ?: "")

            Log.d("DeleteIncome", "Deleting record with ID: $recordId")

            // Remove the record from Firebase
            recordReference.removeValue()

            val intent = Intent(this@ReadIncome, ReadIncome::class.java)
            startActivity(intent)
        }

        val editButton: Button = findViewById(R.id.edit)
        editButton.setOnClickListener {
            val intent = Intent(this@ReadIncome, EditIncome::class.java)
            intent.putExtra("recordId", recordId)
            startActivity(intent)
        }
    }
}
