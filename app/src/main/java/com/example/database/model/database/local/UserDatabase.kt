package com.example.database.model.database.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.model.dtos.Users

@androidx.room.Database(entities = [Users::class], version = 1)
abstract class UserDatabase(): RoomDatabase() {
    abstract fun userDao():Dao

}