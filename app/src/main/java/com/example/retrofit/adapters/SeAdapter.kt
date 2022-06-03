package com.example.retrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.diffutil.MyDiffutil
import com.example.retrofit.fragments.SeasonsFragment
import com.example.retrofit.model.seasons.Seasons

class SeAdapter : RecyclerView.Adapter<SeAdapter.MyHolder>() {

    private var list = ArrayList<Seasons>()

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.season_display_name)
        val year: TextView = view.findViewById(R.id.season_year)
        val seasonEnd: TextView = view.findViewById(R.id.season_end)
        val seasonStart: TextView = view.findViewById(R.id.season_start)
        val background: ConstraintLayout = view.findViewById(R.id.itemBackground)

        fun binding() {
            val item = list[adapterPosition]
            name.text = item.displayName
            year.text = item.year.toString()
            seasonEnd.text = item.endDate
            seasonStart.text = item.startDate
            if (adapterPosition % 2 == 0) {
                background.setBackgroundResource(R.color.blue)
            } else
                background.setBackgroundResource(R.color.purple_700)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_season, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitItems(items: List<Seasons>) {
        val myDiffUtil = MyDiffutil(list, items);
        val diff = DiffUtil.calculateDiff(myDiffUtil)
        list.clear()
        list.addAll(items)
        diff.dispatchUpdatesTo(this)
    }
}