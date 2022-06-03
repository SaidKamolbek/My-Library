package com.example.retrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.diffutil.MyDiffutil
import com.example.retrofit.model.leagueResponce.LeagueResponse

class RAdapter : RecyclerView.Adapter<RAdapter.MyHolder>() {
    private var list = ArrayList<LeagueResponse>()
    private var listener: ((LeagueResponse) -> Unit)? = null
    fun setListener(l: (LeagueResponse) -> Unit) {
        listener = l
    }

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val abbr = view.findViewById<TextView>(R.id.abbr)
        val name = view.findViewById<TextView>(R.id.leagueName)
        val imageLogo = view.findViewById<ImageView>(R.id.logo)

        fun binding() {
            val item = list[adapterPosition]
            abbr.text = item.abbr
            name.text = item.name
            Glide.with(imageLogo)
                .load(item.logos.light)
                .placeholder(R.drawable.ic_image)
                .into(imageLogo)
        }

        init {
            itemView.setOnClickListener {
                listener?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitItems(items: List<LeagueResponse>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

}