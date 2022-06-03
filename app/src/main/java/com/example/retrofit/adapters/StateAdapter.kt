package com.example.retrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.model.standings.STATS
import com.example.retrofit.model.standings.Stat

class StateAdapter : RecyclerView.Adapter<StateAdapter.MyHolder>() {

    private var list = ArrayList<Stat>()

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private val name: TextView = view.findViewById(R.id.stat_name)
//        private val value: TextView = view.findViewById(R.id.stat_value)


        fun binding() {
            val item = list[adapterPosition]
//            name.text = item.displayName
//            value.text = item.displayValue
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.stats_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitItems(items: List<Stat>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}