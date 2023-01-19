package com.example.database.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.database.Communicator
import com.example.database.Constants
import com.example.database.R
import com.example.database.databinding.FragmentSignupBinding
import com.example.database.model.database.local.UserDatabase
import com.example.database.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Signup : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userName: String
    private lateinit var communicator: Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
//        communicator = activity as Communicator
        binding.btnSignUp.setOnClickListener {
            userName = binding.etUsrName.text.toString().trim()
            val userEmail = binding.etEmailSignTn.text.toString().trim()
            val phoneNumber = binding.etPhoneNo.text.toString().trim()
            val password = binding.etPasswordSignIn.text.toString().trim()
            val confirmPassword = binding.etConfirmPass.text.toString().trim()
//            communicator.passData(userName)
            val b = Bundle()
            b.putString(Constants.singUpUserName, "$userName")
            //get userName

            if (!isNameFilled() or !isEmailFilled() or !isPassword() or !isPhone() or !isConfirmPassword() or !isPasswordMatch()) {
                Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
            } else {
                userViewModel.insertUser(userName, userEmail, phoneNumber, password)
                Constants.isLoggedIn = true
                findNavController().navigate(R.id.action_signup_to_workshops,b)
            }


        }

        return binding.root
    }



    private fun isNameFilled(): Boolean {
        return if (userName.isEmpty()) {
            binding.etUsrName.error = "Name can not be empty"
            false
        } else {
            binding.etUsrName.error = null
            true
        }
    }

    private fun isEmailFilled(): Boolean {
        val userEmail: String = binding.etEmailSignTn.text.toString().trim()
        return if (userEmail.isEmpty()) {
            binding.etEmailSignTn.error = "Name can not be empty"
            false
        } else {
            binding.etEmailSignTn.error = null
            true
        }
    }

    private fun isPhone(): Boolean {
        val userPhone: String = binding.etPhoneNo.text.toString().trim()
        return if (userPhone.isEmpty()) {
            binding.etPhoneNo.error = "Name can not be empty"
            false
        } else {
            binding.etPhoneNo.error = null
            true
        }
    }

    private fun isPassword(): Boolean {
        val userPassword: String = binding.etPasswordSignIn.text.toString().trim()
        return if (userPassword.isEmpty()) {
            binding.etPasswordSignIn.error = "Name can not be empty"
            false
        } else {
            binding.etPasswordSignIn.error = null
            true
        }
    }

    private fun isConfirmPassword(): Boolean {
        val userConfirmPassword: String = binding.etConfirmPass.text.toString().trim()
        return if (userConfirmPassword.isEmpty()) {
            binding.etConfirmPass.error = "Name can not be empty"
            false
        } else {
            binding.etConfirmPass.error = null
            true
        }
    }

    private fun isPasswordMatch(): Boolean {
        return if (binding.etPasswordSignIn.text.toString() != binding.etConfirmPass.text.toString()) {
            Toast.makeText(requireContext(), "Password don't Match", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }



}