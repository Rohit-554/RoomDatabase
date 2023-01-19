package com.example.database.screens.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.Constants
import com.example.database.R
import com.example.database.databinding.FragmentUserDashBoardBinding
import com.example.database.model.dtos.Workshop
import com.example.database.screens.adapters.EnrolledAdapter
import com.example.database.viewModels.UserViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type


@AndroidEntryPoint
class UserDashBoard : Fragment() {
    private lateinit var binding: FragmentUserDashBoardBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: EnrolledAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var workshop: Workshop
    var data: String? = "welcome"
    var userName:String?="welcome"
    private var enrolled:List<Workshop>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentUserDashBoardBinding.inflate(layoutInflater, container, false)
        val b = Bundle()

//        val userData = arguments?.getParcelableArrayList<Workshop>("WorkshopList")
        val bundle = Bundle()
        val userData = arguments?.getString("WorkshopList")
        val listType: Type? = object : TypeToken<List<Workshop?>?>() {}.type
        Log.d("enrolleduser","$userData")


//        val data: Workshop? =  arguments?.getParcelable("WorkshopList",Workshop::class.java)
//        val data: MutableList<Workshop> = userData.toMutableList()
        val workshops: List<Workshop> = listOf(
            Workshop("No course Added",R.drawable.benjamin,"Not Available",false)
        )
        adapter = EnrolledAdapter(workshops as MutableList<Workshop>)
        mRecyclerView = binding.enrolledRecyclerView
        if(userData!=null){
            enrolled = Gson().fromJson(userData,listType)
            adapter = EnrolledAdapter(enrolled as MutableList<Workshop>)
        }else{
            Toast.makeText(requireContext(), "Please Select a course", Toast.LENGTH_SHORT).show()

        }


        mRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        mRecyclerView.adapter = adapter

        data = arguments?.getString(Constants.loginUserName)
        userName = arguments?.getString(Constants.singUpUserName)
        b.putString("username_dash","$data")

        Log.d("userValue", "$userName")
        Log.d("userValue2","$data")
        if(data!=null){
            binding.userName.text = data
        }else if(userName!=null){
            binding.userName.text = userName
        }else{
            binding.userName.text = "Welcome"
        }


        return binding.root
    }


}