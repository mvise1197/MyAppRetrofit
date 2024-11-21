package com.miguelvise.myappretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// Es el responsable de enlazar los datos con la lista que se muestra en el RecyclerView

class MyAdapter(var con : Context, var list: List<UserItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.RV_tv)
        var tvAvatar: ImageView = itemView.findViewById(R.id.RV_Image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

        override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = list[position].login
        Glide.with(con).load(list[position].avatar_url).into(holder.tvAvatar)
    }
}