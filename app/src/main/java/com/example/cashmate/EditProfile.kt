package com.example.cashmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cashmate.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfile : AppCompatActivity() {

    lateinit var txtUserName: EditText
    lateinit var txtEmail: EditText
    lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        txtUserName = findViewById(R.id.editUserNameValue)
        txtEmail = findViewById(R.id.editTextTextEmailAddress)
        updateButton = findViewById(R.id.btnEdit)

        //get username and email from in shared preferences
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        val password = sharedPref.getString("password", "")

        readData(email!!, password!!)

        updateButton.setOnClickListener {
            updateData(email, password)
        }
    }

    //read data from database by passing email and password
    fun readData(email: String, password: String) {
        //get user details from database by using email and password
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        println("user: ${user.toString()}}")
                        if (user != null) {
                            if (user.email == email && user.password == password) {
                                //user found
                                //save user details in shared preferences
                                //navigate to home page

                                //save user details in shared preferences
                                val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
                                val editor = sharedPref.edit()
                                editor.putString("email", user.email)
                                editor.putString("password", user.password)
                                editor.putString("userName", user.userName)
                                editor.apply()

                                //set username and email in edit text
                                txtEmail.setText(user.email)
                                txtUserName.setText(user.userName)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EditProfile, "Error: ${error.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }

    fun updateData(email: String, password: String) {
        //update user details in database
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        println("user: ${user.toString()}}")
                        if (user != null) {
                            if (user.email == email && user.password == password) {
                                //user found
                                //update user details in database
                                val user = User(
                                    userSnapshot.key.toString(),
                                    txtUserName.text.toString(),
                                    txtEmail.text.toString(),
                                    user.password
                                )
                                dbRef.child(userSnapshot.key.toString()).setValue(user)
                                Toast.makeText(
                                    this@EditProfile,
                                    "User details updated successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                //navigate to home page
                                var intent = Intent(this@EditProfile, Navigation::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@EditProfile, "Error: ${error.message}", Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}