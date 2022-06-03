package com.example.retrofit.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.adapters.SeAdapter
import com.example.retrofit.model.BaseResponse
import com.example.retrofit.model.seasons.Season
import com.example.retrofit.networking.Network
import com.example.retrofit.networking.api.FootballApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeasonsFragment(private val leagueId: String) : Fragment(R.layout.fragment_seasons) {

    private val adapter = SeAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler2)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        val progressBar = ProgressDialog(requireContext())
        progressBar.setTitle("Loading...")
        progressBar.setCancelable(false)
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar.setMessage("Application is loading, please wait")
        val api = Network.getRetrofit(requireContext()).create(FootballApi::class.java)
        progressBar.show()
        api.getSeasonsById(leagueId)
            .enqueue(object : Callback<BaseResponse<Season>> {
                override fun onResponse(
                    call: Call<BaseResponse<Season>>,
                    response: Response<BaseResponse<Season>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        if (data != null) {
                            val list = data.seasons
                            adapter.submitItems(list)
                            progressBar.cancel()
                        } else {
                            Log.d("TTT", "not received data")
                        }
                    } else
                        Log.d("TTT", "no response")
                }

                override fun onFailure(call: Call<BaseResponse<Season>>, t: Throwable) {
                    progressBar.cancel()
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

}