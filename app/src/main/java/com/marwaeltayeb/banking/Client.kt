package com.marwaeltayeb.banking

import androidx.room.PrimaryKey

data class Client (
    val name: String,
    val email: String,
    val phoneNumber: String,
    var balance: Double,
    @PrimaryKey(autoGenerate = true) val client_id: Int  = 0,
)