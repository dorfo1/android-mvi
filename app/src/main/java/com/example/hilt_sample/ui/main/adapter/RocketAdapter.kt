package com.example.hilt_sample.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hilt_sample.R
import com.example.hilt_sample.model.Rocket


class RocketAdapter(private val listener : (Rocket) -> Unit) : RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {

    var items : List<Rocket> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_rocket_item_row,parent,false)
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        val title = holder.itemView.findViewById<TextView>(R.id.title)

        title.text = items[position].name

        val url : String? = items[position].photos[0]

        url?.let {
            Glide.with(image).load(it).into(image)
        }

        holder.itemView.setOnClickListener {
            listener.invoke(items[position])
        }

    }

    override fun getItemCount(): Int = items.size

    class RocketViewHolder(view : View) : RecyclerView.ViewHolder(view)

}