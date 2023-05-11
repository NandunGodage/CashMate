package com.example.cashmate.ui.gallery

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cashmate.EditProfile
import com.example.cashmate.R
import com.example.cashmate.SignInPage
import com.example.cashmate.databinding.FragmentGalleryBinding
import com.example.cashmate.models.User
import com.example.cashmate.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var btnEditProfile: Button? = null
    var btnDeleteProfile: Button? = null
    var userName: EditText? = null
    var userEmail: EditText? = null
    var dbRef: DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btnEditProfile = root.findViewById(R.id.btnEdit)
        btnDeleteProfile = root.findViewById(R.id.btnDelete)
        userName = root.findViewById(R.id.editUserNameValue)
        userEmail = root.findViewById(R.id.editTextTextEmailAddress)

        //username and email are not editable
        userName?.isEnabled = false
        userEmail?.isEnabled = false


        //get username and email from in shared preferences
        val sharedPref = activity?.getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        val email = sharedPref?.getString("email", "")
        val password = sharedPref?.getString("password", "")
        val userName = sharedPref?.getString("userName", "")

        readData(email!!, password!!)

        btnEditProfile?.setOnClickListener {
            var intent = Intent(activity, EditProfile::class.java)
            startActivity(intent)
            activity?.finish()
        }

        btnDeleteProfile?.setOnClickListener {
            deleteProfile(email, password)
        }

        return root

    }

    fun deleteProfile(email: String, password: String) {
        //delete profile from database by using email and password
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
                                //delete user details from database
                                userSnapshot.ref.removeValue()
                                //delete user details from shared preferences
                                val sharedPref =
                                    activity?.getSharedPreferences(
                                        "MyPref",
                                        AppCompatActivity.MODE_PRIVATE
                                    )
                                val editor = sharedPref?.edit()
                                editor?.clear()
                                editor?.apply()
                                //display toast message
                                Toast.makeText(
                                    activity,
                                    "Profile deleted successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                //navigate to sign in page
                                var intent = Intent(activity, SignInPage::class.java)
                                startActivity(intent)
                                activity?.finish()
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("error: ${error.message}")
            }
        })

    }

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
                                val sharedPref =
                                    activity?.getSharedPreferences(
                                        "MyPref",
                                        AppCompatActivity.MODE_PRIVATE
                                    )
                                val editor = sharedPref?.edit()
                                editor?.putString("email", user.email)
                                editor?.putString("password", user.password)
                                editor?.putString("userName", user.userName)
                                editor?.apply()
                                //username and email are not editable
                                userName?.isEnabled = true
                                userEmail?.isEnabled = true


                                //set username and email in edit text
                                userEmail?.setText(user.email)
                                userName?.setText(user.userName)

                                //username and email are not editable
                                userName?.isEnabled = false
                                userEmail?.isEnabled = false
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("error: ${error.message}")
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}