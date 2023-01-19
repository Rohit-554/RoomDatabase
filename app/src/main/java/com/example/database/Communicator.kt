package com.example.database

interface Communicator {
    fun passData(userName:String)
    fun getUserName(userName: String)
    fun isLoggedIn(isLoggedIn:Boolean)
}