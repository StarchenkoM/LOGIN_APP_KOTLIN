package com.trd.loginapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trd.loginapp.Constants.ROOM_DATABASE_VERSION

@Database(
    entities = [UserRoomEntity::class],
    version = ROOM_DATABASE_VERSION
)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun userDao(): UserDao
}
