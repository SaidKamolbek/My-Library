package com.example.retrofit.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.adapters.PagerAdapter
import com.example.retrofit.model.BaseResponse
import com.example.retrofit.model.leagueResponce.LeagueDetailResponse
import com.example.retrofit.networking.Network
import com.example.retrofit.networking.api.FootballApi
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(R.layout.main_fragment) {


    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: PagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueID = requireArguments().get("id").toString()

        tabLayout = view.findViewById(R.id.tabLayout)
        val pager: ViewPager2 = view.findViewById(R.id.viewPager2)
        adapter = PagerAdapter(parentFragmentManager, lifecycle, leagueID)
        val name = view.findViewById<TextView>(R.id.main_text)
        val light = view.findViewById<ImageView>(R.id.main_image)

        pager.adapter = adapter
        val progressBar = ProgressDialog(requireContext())
        progressBar.setTitle("Loading...")
        progressBar.setCancelable(false)
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar.setMessage("Application is loading, please wait")

        val api = Network.getRetrofit(requireContext()).create(FootballApi::class.java)
        progressBar.show()
        api.getLeagueDetailById(leagueID)
            .enqueue(object : Callback<BaseResponse<LeagueDetailResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<LeagueDetailResponse>>,
                    response: Response<BaseResponse<LeagueDetailResponse>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        name.text = data?.name


                        Glide.with(light)
                            .load(data?.logos?.light)
                            .placeholder(R.drawable.ic_image)
                            .into(light)

                        progressBar.cancel()
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponse<LeagueDetailResponse>>,
                    t: Throwable
                ) {
                    progressBar.cancel()
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            })





        TabLayoutMediator(tabLayout, pager) { tab, position ->
//            pager.setCurrentItem(position, true)
            when (position) {
//                0 -> tab.text = "League Details"
                1 -> tab.text = "Seasons"
                else -> tab.text = "Standings"
            }
        }.attach()


    }

}