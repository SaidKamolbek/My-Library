package com.example.retrofit.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofit.fragments.SeasonsFragment
import com.example.retrofit.fragments.SecondFragment
import com.example.retrofit.fragments.Standings

class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, id: String) :
    FragmentStateAdapter(fm, lifecycle) {


    private val seasonsFragment = SeasonsFragment(id)
//    val secondFragment = SecondFragment(id)
    val standings = Standings(id)

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
//            0 -> secondFragment
            1 -> seasonsFragment
            else -> standings
        }
    }
}