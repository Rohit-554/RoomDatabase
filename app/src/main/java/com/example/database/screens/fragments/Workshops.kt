package com.example.database.screens.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.Constants
import com.example.database.R
import com.example.database.databinding.FragmentWorkshopsBinding
import com.example.database.model.dtos.Workshop
import com.example.database.screens.adapters.WorkshopAdapter
import com.google.gson.Gson

class Workshops : Fragment() {
    private lateinit var binding: FragmentWorkshopsBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapter: WorkshopAdapter

    var isLoggedIn:String? = "false"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val b = Bundle()
        val Login_user_name = arguments?.getString("loginName")
        b.putString(Constants.loginWorkshop,"${arguments?.getString(Constants.loginUserName)}")
        b.putString(Constants.loginWorkshop,"${arguments?.getString(Constants.singUpUserName)}")

        binding = FragmentWorkshopsBinding.inflate(layoutInflater,container,false)
        mRecyclerView =binding.mrecycler
        isLoggedIn = arguments?.getString("islogin")
        Log.d("islogin","$isLoggedIn")
        val workshops: List<Workshop> = listOf(
            Workshop("Benjamin Art",R.drawable.benjamin,"Learn Benjamin Art",false),
            Workshop("Black Tint",R.drawable.blacktint,"Learn Black Tint",false),
            Workshop("Sea",R.drawable.sea,"Learn About Seas",false),
            Workshop("Gradienta",R.drawable.gradienta,"Learn About Gradients",false)
        )
        adapter = WorkshopAdapter(requireContext(),workshops)

        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object :WorkshopAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val data  = adapter.getEnrolledCourses(position)
                b.putString("WorkshopList",Gson().toJson(data))
            }
        })
        binding.dashboard.setOnClickListener {
            findNavController().navigate(R.id.action_workshops_to_userDashBoard2,b)
        }


        // Inflate the layout for this fragment
        return binding.root
    }




}