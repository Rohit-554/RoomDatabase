package com.example.database.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.database.Communicator
import com.example.database.Constants
import com.example.database.R
import com.example.database.databinding.FragmentSignupBinding
import com.example.database.model.database.local.UserDatabase
import com.example.database.viewModels.UserViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Signup : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userName: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var communicator: Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.btnSignUp.setOnClickListener {
            binding.progressBar.isVisible = true
            userName = binding.etUsrName.text.toString().trim()
            val userEmail = binding.etEmailSignTn.text.toString().trim()
            val phoneNumber = binding.etPhoneNo.text.toString().trim()
            val password = binding.etPasswordSignIn.text.toString().trim()
            val confirmPassword = binding.etConfirmPass.text.toString().trim()
            val b = Bundle()
            b.putString(Constants.singUpUserName, "$userName")
            if (!isNameFilled() or !isEmailFilled() or !isPassword() or !isPhone() or !isConfirmPassword() or !isPasswordMatch()) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()

            } else {
                val response = signInClient(userEmail, password)
                response.addOnSuccessListener { task ->
                    Toast.makeText(
                        requireContext(),
                        "Account Created Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBar.visibility = View.GONE
                    val currentUser = mAuth.currentUser
                    val userInfo: MutableMap<String, Any> = HashMap()
                    userInfo["email"] = userEmail
                    findNavController().navigate(R.id.action_signup_to_workshops)
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Account Creation Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }

        return binding.root
    }

    fun signInClient(email: String, password: String): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email, password)
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

    //this is for checking password
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