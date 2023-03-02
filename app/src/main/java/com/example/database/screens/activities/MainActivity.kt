package com.example.database.screens.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.database.Communicator
import com.example.database.Constants
import com.example.database.R
import com.example.database.screens.fragments.UserDashBoard
import com.example.database.screens.fragments.Workshops
import com.example.database.viewModels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

    }


    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null){
            navController.navigate(R.id.action_authFragment_to_userDashBoard)

        }
    }


}