package com.example.database.screens.adapters

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.database.Constants
import com.example.database.R
import com.example.database.model.dtos.Workshop

class WorkshopAdapter(var context: Context, private val items: List<Workshop>) :
    RecyclerView.Adapter<WorkshopAdapter.WorkshopViewHolder>() {


    var enrolledWorkshops = mutableListOf<Workshop>()
    private lateinit var mListener: onItemClickListener
    private var positionValue: Int? = null


    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    init {
        Log.d("itemList", "$items")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkshopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return WorkshopViewHolder(view, mListener)
    }

    fun getEnrolledCourses(position: Int): MutableList<Workshop> {
        positionValue = position
//        enrolledWorkshops = mutableListOf(items[positionValue!!])
        enrolledWorkshops.add(items[position])
        return enrolledWorkshops
        Log.d("positionvalue", "$enrolledWorkshops")

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WorkshopViewHolder, position: Int) {
        val currentItem = items[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.Image)
            .into(holder.image);
        holder.Title.text = currentItem.Title
        holder.description.text = currentItem.Description
    }

    class WorkshopViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.workshopImage)
        val Title = itemView.findViewById<TextView>(R.id.Title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val Enroll = itemView.findViewById<TextView>(R.id.Enroll)

        init {
            itemView.findViewById<Button>(R.id.Enroll).setOnClickListener {
                listener.onItemClick(adapterPosition)
                Thread(Runnable {
                    Handler(Looper.getMainLooper()).postDelayed({
                        it.setBackgroundResource(R.drawable.round_disabled)
                        val x = itemView.findViewById<Button>(R.id.Enroll)
                        x.text = "Enrolled"
                        it.isEnabled = false
                    }, 500)
                }).start()

            }
        }
    }


}