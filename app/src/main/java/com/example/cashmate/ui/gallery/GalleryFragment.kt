package com.example.cashmate.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cashmate.EditProfile
import com.example.cashmate.R
import com.example.cashmate.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var btnEditProfile: Button? = null
    var btnDeleteProfile: Button? = null
    var userName: EditText? = null
    var userEmail: EditText? = null
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


        btnEditProfile?.setOnClickListener {
            var intent = Intent(activity, EditProfile::class.java)
            startActivity(intent)
            activity?.finish()
        }

        btnDeleteProfile?.setOnClickListener {
            //check user username and email are correct
            //if correct then delete the user
            //else show error message

            if (userName?.text.toString() == "admin" && userEmail?.text.toString() == "madushaprasad21@gmail.com") {
                //delete user from firebase
//                var dbconnection

            } else {
                //show error message
            }

        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}