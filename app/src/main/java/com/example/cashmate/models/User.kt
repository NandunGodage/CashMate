package com.example.cashmate.models

import android.widget.EditText
import android.widget.ImageView

data class User(
    var profileImage: String? = null,
    var userName: String? = null,
    var email: String? = null,
    var password: String? = null
)