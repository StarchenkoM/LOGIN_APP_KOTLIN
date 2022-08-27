package com.trd.loginapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users_table")
data class UserRoomEntity(
    @PrimaryKey
    var phoneNumber: String = "",
    val surname: String = "",
    val name: String = "",
)