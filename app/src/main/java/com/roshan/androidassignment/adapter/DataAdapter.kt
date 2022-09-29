package com.roshan.androidassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roshan.androidassignment.databinding.RvItemBinding
import com.roshan.androidassignment.model.DataModel

class DataAdapter(private val context: Context?, private var list: List<DataModel>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: DataModel = list[position]

        holder.binding!!.id.text = data.id.toString()
        holder.binding.name.text = data.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: RvItemBinding?) : RecyclerView.ViewHolder(binding!!.root)
}