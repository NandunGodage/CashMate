package com.example.cashmate

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import com.example.cashmate.models.User
import com.example.cashmate.ui.home.HomeFragment
import com.google.firebase.database.*

class SignInPage : AppCompatActivity() {

    lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText
    lateinit var keepMeLog: CheckBox
    lateinit var btnSignIn: Button
    lateinit var btnSignUp: Button
    lateinit var forgetPass: TextView
    lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        keepMeLog = findViewById(R.id.checkBoxKeepLogin)
        btnSignIn = findViewById(R.id.btnSign)
        btnSignUp = findViewById(R.id.btnSign2)


        btnSignUp.setOnClickListener {
            var intent = Intent(this, SignInPage::class.java)
            startActivity(intent)
            finish()
        }

        btnSignIn.setOnClickListener {
            checkUserIdentity()
        }
    }

    fun checkUserIdentity() {
        //get user details from database by using email and password
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        when {
                            user != null -> {
                                if (user.email == txtEmail.text.toString() && user.password == txtPassword.text.toString()) {
                                    //user found
                                    //save user details in shared preferences
                                    //navigate to home page
                                    val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
                                    val editor = sharedPref.edit()
                                    editor.putString("email", user.email)
                                    editor.putString("password", user.password)
                                    editor.apply()

//                                    val intent = Intent
                                    val intent = Intent(this@SignInPage, MainActivity::class.java)
//                                    val intent = Intent(applicationContext, HomeFragment::class.java)
                                    startActivity(intent)
                                    finish();
                                }
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInPage, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}