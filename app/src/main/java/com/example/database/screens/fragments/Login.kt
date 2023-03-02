package com.example.database.screens.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.database.Communicator
import com.example.database.Constants
import com.example.database.R
import com.example.database.databinding.FragmentLoginBinding
import com.example.database.model.dtos.Users
import com.example.database.viewModels.UserViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userDashBoard: UserDashBoard
    private lateinit var communicator: Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val b = Bundle()
//        communicator = activity as Communicator
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.btnSignIn.setOnClickListener {
            userEmail = binding.etEmailSignTn.text.toString()
            userPassword = binding.etPasswordSignIn.text.toString()
            userViewModel.getUser(userEmail)

            if (!isEmailFilled() or !isPassword()) {
                Toast.makeText(
                    requireContext(),
                    "Please Check your Credentials",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                binding.progressBar.visibility = View.VISIBLE
                val response = loginWithEmailPassword(userEmail, userPassword)
                response.addOnSuccessListener {
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_login_to_workshops2)
                    binding.progressBar.visibility = View.GONE
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }


            //get the user name after Loging


            binding.tvSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_signup)
            }

        }
        return binding.root

    }

    private fun isEmailFilled(): Boolean {
        return if (userEmail.isEmpty()) {
            binding.etEmailSignTn.error = "Email can not be empty"
            false
        } else {
            binding.etEmailSignTn.error = null
            true
        }
    }

    private fun isPassword(): Boolean {
        return if (userPassword.isEmpty()) {
            binding.etPasswordSignIn.error = "Password can not be empty"
            false
        } else {
            binding.etPasswordSignIn.error = null
            true
        }
    }
    fun loginWithEmailPassword(email: String, password: String): Task<AuthResult> {
        mAuth = FirebaseAuth.getInstance()
        return mAuth.signInWithEmailAndPassword(email,password)
    }

}