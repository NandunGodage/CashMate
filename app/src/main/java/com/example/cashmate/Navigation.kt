package com.example.cashmate

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.cashmate.databinding.ActivityNavigationBinding
import com.example.cashmate.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Navigation : AppCompatActivity() {

    var myUserNameVal: TextView? = null
    var myemailVal: TextView? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarTest.toolbar)

        binding.appBarTest.toolbar.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_test)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )


        myemailVal = findViewById(R.id.myEmail)
        myUserNameVal = findViewById(R.id.myName)

        //get username and email from in shared preferences
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val email = sharedPref?.getString("email", "")
        val password = sharedPref?.getString("password", "")

        readData(email!!, password!!)


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_test)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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
                                    getSharedPreferences(
                                        "MyPref",
                                        AppCompatActivity.MODE_PRIVATE
                                    )
                                val editor = sharedPref?.edit()
                                editor?.putString("email", user.email)
                                editor?.putString("password", user.password)
                                editor?.putString("userName", user.userName)
                                editor?.apply()
                                //set username and email in edit text
                                myemailVal?.setText(user.email)
                                myUserNameVal?.setText(user.userName)
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
}