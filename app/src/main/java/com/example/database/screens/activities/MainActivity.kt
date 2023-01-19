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

//    override fun passData(userName: String) {
//        val args = Bundle()
//        args.putString("username", userName)
//        val transaction = this.supportFragmentManager.beginTransaction()
//        val fragment = UserDashBoard()
//        fragment.arguments = args
//        transaction.replace(R.id.fragmentContainerView ,fragment).commit()
//    }
//
//    override fun getUserName(userName: String) {
//        val args = Bundle()
//        args.putString("Login", userName)
////        navController.setGraph(R.navigation.nav_graph,args)
////        val transaction = this.supportFragmentManager.beginTransaction()
////        val fragment = UserDashBoard()
////        fragment.arguments = args
////        transaction.replace(R.id.fragmentContainerView ,fragment).commit()
//    }
//
//    override fun isLoggedIn(isLoggedIn: Boolean) {
//        val args = Bundle()
//        args.putString("islogin", isLoggedIn.toString())
////        navController.setGraph(R.navigation.nav_graph,args)
////        val transaction = this.supportFragmentManager.beginTransaction()
////        val fragment = Workshops()
////        fragment.arguments = args
////        transaction.replace(R.id.fragmentContainerView ,fragment).commit()
//    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.isLoggedIn = false
    }


}