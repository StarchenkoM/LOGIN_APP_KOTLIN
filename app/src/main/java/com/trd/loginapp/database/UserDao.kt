package com.trd.loginapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserToDb(news: UserRoomEntity): Long

    @Query("SELECT * FROM Users_table")
    fun getAllUsersAsList(): List<UserRoomEntity>

    @Query("SELECT * FROM Users_table WHERE phoneNumber =:phoneNumber")
    fun getUserById(phoneNumber: String): UserRoomEntity

    @Query("DELETE FROM Users_table WHERE phoneNumber =:phoneNumber")
    fun deleteUserFromDB(phoneNumber: String): Int

}