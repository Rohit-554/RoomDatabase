package com.example.database.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.model.dtos.Users
import com.example.database.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {
    private val usersLiveData = MutableLiveData<Users>()
    val userData:LiveData<Users>
    get() = usersLiveData

    fun insertUser(name:String,email:String,phoneNumber:String,password:String){
        viewModelScope.launch {
            userRepository.insertData(name,email, phoneNumber, password)
        }
    }
    fun getUser(email: String){
        viewModelScope.launch {
            userRepository.getLoginDetails(email).let {
                usersLiveData.postValue(it)
            }

        }
    }


}