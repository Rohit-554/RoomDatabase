package com.example.database.model.database.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.example.database.model.dtos.Users

@androidx.room.Dao
interface Dao {
    @Insert
    suspend fun insertUser(user: Users)

    @Query("SELECT * FROM UserDatabase WHERE email =:email")
    suspend fun getUser(email:String):Users

}