package com.example.database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.database.model.database.local.Dao
import com.example.database.model.dtos.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: Dao) {
    private val usersLiveData = MutableLiveData<Users>()
    val usersData:LiveData<Users>
        get() = usersLiveData



    suspend fun insertData(name:String,email:String,phoneNumber:String,password:String) {
        CoroutineScope(IO).launch {
            val loginDetails = Users(name, email,phoneNumber,false, password)
            userDao.insertUser(loginDetails)
        }

    }
    suspend fun getLoginDetails(userEmail: String):Users {

        return userDao.getUser(userEmail)

    }

}