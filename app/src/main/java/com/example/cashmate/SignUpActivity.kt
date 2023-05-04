package com.example.cashmate

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.cashmate.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException

class SignUpActivity : AppCompatActivity() {

    lateinit var btnUserSignUp: Button
    lateinit var txtUserName: EditText
    lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText
    lateinit var txtConfirmPassword: EditText
    lateinit var userProfile: ImageView
    lateinit var btnProfileImageUpload: Button
    private var imageData: ByteArray? = null

    private lateinit var dataBaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        userProfile = findViewById(R.id.profileImage)
        btnUserSignUp = findViewById(R.id.btnSignUp)
        btnProfileImageUpload = findViewById(R.id.btnImageUpload)
        txtUserName = findViewById(R.id.txtUserName)
        txtEmail = findViewById(R.id.txtUserEmail)
        txtPassword = findViewById(R.id.txtUserPassword)
        txtConfirmPassword = findViewById(R.id.txtUserConfirmPassword)


        dataBaseRef = FirebaseDatabase.getInstance().getReference("Users")

        //display message in the console when click btnSignUp
        btnUserSignUp.setOnClickListener {

            registerUser()

        }

        btnProfileImageUpload.setOnClickListener {
            launchGallery()
        }
    }

    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    private fun uploadImage() {
        imageData ?: return
        val fileName = "images/${System.currentTimeMillis()}.png"
        val ref = FirebaseStorage.getInstance().getReference(fileName)
        ref.putBytes(imageData!!).addOnSuccessListener {
                Toast.makeText(applicationContext, "Image uploaded successfully", Toast.LENGTH_LONG)
                    .show()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Image upload failed", Toast.LENGTH_LONG).show()
            }
    }

    @Throws(IOException::class)
    private fun createImageData(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            val uri = data?.data
            if (uri != null) {
                userProfile.setImageURI(uri)
                createImageData(uri)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun registerUser() {
        val profileVal = userProfile.toString()
        val userNameVal = txtUserName.text.toString()
        val userEmailVal = txtEmail.text.toString()
        val userPasswordVal = txtPassword.text.toString()
        val userConfirmPasswordVal = txtConfirmPassword.text.toString()

        if (userNameVal.isEmpty()) {
            txtUserName.error = "Please enter your name"
            return
        }
        if (userEmailVal.isEmpty()) {
            txtEmail.error = "Please enter your email"
            return
        }
        if (userPasswordVal.isEmpty()) {
            txtPassword.error = "Please enter your password"
            return
        }

        if (userConfirmPasswordVal.isEmpty()) {
            txtConfirmPassword.error = "Please confirm your password"
            return
        }

        uploadImage()

        var userID = dataBaseRef.push().key!!
        val userDetails = User(profileVal, userNameVal, userEmailVal, userPasswordVal)

        dataBaseRef.child(userID).setValue(userDetails).addOnCompleteListener {
            Toast.makeText(applicationContext, "User registered successfully", Toast.LENGTH_LONG)
                .show()
            txtUserName.setText("")
            txtEmail.setText("")
            txtPassword.setText("")
            txtConfirmPassword.setText("")
            userProfile.setImageResource(R.drawable.register)
            //navigate to the sign in page
            var intent = Intent(this, SignInPage::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "User registration failed", Toast.LENGTH_LONG).show()
        }

    }

}