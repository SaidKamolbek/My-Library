package com.example.retrofit.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.adapters.RAdapter
import com.example.retrofit.model.BaseResponse
import com.example.retrofit.model.leagueResponce.LeagueResponse
import com.example.retrofit.networking.Network
import com.example.retrofit.networking.api.FootballApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var adapter = RAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        val progressBar = ProgressDialog(requireContext())
        progressBar.setTitle("Loading...")
        progressBar.setCancelable(false)
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar.setMessage("Application is loading, please wait")

        val api = Network.getRetrofit(requireContext()).create(FootballApi::class.java)
        progressBar.show()
        api.getAllLeagues().enqueue(object : Callback<BaseResponse<List<LeagueResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<LeagueResponse>>>,
                response: Response<BaseResponse<List<LeagueResponse>>>
            ) {

                if (response.isSuccessful) {
                    adapter.submitItems(response.body()?.data ?: arrayListOf())
                    progressBar.cancel()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<LeagueResponse>>>, t: Throwable) {
                progressBar.cancel()
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })

        adapter.setListener {
            Navigation.findNavController(view).navigate(R.id.mainFragment,
                Bundle().apply {
                    putString("id", it.id)
                })
        }
    }

}