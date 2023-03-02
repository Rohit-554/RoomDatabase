package com.example.database.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.database.R
import com.example.database.databinding.FragmentAuthBinding
import com.example.database.databinding.FragmentLoginBinding

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(layoutInflater,container,false)
        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_login)
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_signup)
        }
        binding.skip.setOnClickListener{
            findNavController().navigate(R.id.action_authFragment_to_workshops)
        }

        return binding.root
    }

}