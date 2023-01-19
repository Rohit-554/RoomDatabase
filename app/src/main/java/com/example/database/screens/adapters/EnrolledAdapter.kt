package com.example.database.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.database.R
import com.example.database.model.dtos.Workshop

class EnrolledAdapter(private val items: MutableList<Workshop>?): RecyclerView.Adapter<EnrolledAdapter.EnrolledViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnrolledViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return EnrolledViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: EnrolledViewHolder, position: Int) {
        val currentItem = items?.get(position)
        if (currentItem != null) {
            Glide.with(holder.itemView.context)
                .load(currentItem.Image)
                .into(holder.image)
        };
        if (currentItem != null) {
            holder.Title.text = currentItem.Title
        }
        if (currentItem != null) {
            holder.description.text = currentItem.Description
        }
        holder.Enroll.isEnabled = false
        holder.Enroll.setBackgroundResource(R.drawable.round_disabled)
        holder.Enroll.text = "Enrolled"
    }
    fun initData(itemsList:MutableList<Workshop>){
        this.items?.clear()
        this.items?.addAll(itemsList)
        notifyDataSetChanged()
    }

    class EnrolledViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.workshopImage)
        val Title = itemView.findViewById<TextView>(R.id.Title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val Enroll = itemView.findViewById<TextView>(R.id.Enroll)


    }

}