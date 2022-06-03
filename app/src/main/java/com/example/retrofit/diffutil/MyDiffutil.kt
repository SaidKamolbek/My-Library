package com.example.retrofit.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofit.model.seasons.Seasons

class MyDiffutil(
    private val oldItems: List<Seasons>,
    private val newItems: List<Seasons>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].displayName == newItems[newItemPosition].displayName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}