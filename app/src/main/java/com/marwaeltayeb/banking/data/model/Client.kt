package com.marwaeltayeb.banking.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "client_table")
data class Client (
    val name: String,
    val email: String,
    val phoneNumber: String,
    var balance: Double,
    @PrimaryKey(autoGenerate = true) val client_id: Int  = 0,
): Parcelable