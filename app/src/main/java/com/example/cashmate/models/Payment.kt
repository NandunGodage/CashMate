package com.example.cashmate.models

data class Payment(
    val type:String,
    val cardnumber: CharSequence,
    val name: String,
    val exDate: String,
    var cvcNo: CharSequence
)