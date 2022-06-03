package com.example.retrofit.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.model.BaseResponse
import com.example.retrofit.model.leagueResponce.LeagueDetailResponse
import com.example.retrofit.model.standings.STATS
import com.example.retrofit.networking.Network
import com.example.retrofit.networking.api.FootballApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragment(private val leagueId: String) : Fragment(R.layout.fragment_second) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //receiving id from another fragment


        val abbr = view.findViewById<TextView>(R.id.team_abbr)
        val slug = view.findViewById<TextView>(R.id.team_slug)
        val id = view.findViewById<TextView>(R.id.team_id)
        val dark = view.findViewById<ImageView>(R.id.logo_dark)





    }


}