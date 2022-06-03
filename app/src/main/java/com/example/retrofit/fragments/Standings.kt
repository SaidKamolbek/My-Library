package com.example.retrofit.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.model.BaseResponse
import com.example.retrofit.model.seasons.Season
import com.example.retrofit.model.standings.STATS
import com.example.retrofit.model.standings.StandingsDATA
import com.example.retrofit.networking.Network
import com.example.retrofit.networking.api.FootballApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException


class Standings(private val leagueId: String) : Fragment(R.layout.fragment_standings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val recyclerView: RecyclerView = view.findViewById(R.id.recycler3)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = adapter
        val array = ArrayList<STATS>()
        val spinner = view.findViewById<Spinner>(R.id.AllYears)
        val progressBar = ProgressDialog(requireContext())
        progressBar.setTitle("Loading...")
        progressBar.setCancelable(false)
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar.setMessage("Application is loading, please wait")
        val api = Network.getRetrofit(requireContext()).create(FootballApi::class.java)

        api.getSeasonsById(leagueId).enqueue(
            object : Callback<BaseResponse<Season>> {
                override fun onResponse(
                    call: Call<BaseResponse<Season>>,
                    response: Response<BaseResponse<Season>>
                ) {
                    if (response.isSuccessful) {
                        val list = ArrayList<String>()
                        response.body()?.data?.seasons?.forEach {
                            list.add(it.year.toString())
                        }
                        spinner.adapter = ArrayAdapter(
                            requireContext(),
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            list
                        )
                    }

                }

                override fun onFailure(call: Call<BaseResponse<Season>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            }
        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view1: View?,
                position: Int,
                id: Long
            ) {
                progressBar.show()
                api.getStandings(leagueId, parent?.getItemAtPosition(position).toString())
                    .enqueue(object : Callback<BaseResponse<StandingsDATA>> {
                        override fun onResponse(
                            call: Call<BaseResponse<StandingsDATA>>,
                            response: Response<BaseResponse<StandingsDATA>>
                        ) {
                            if (response.isSuccessful) {
                                val group: TableLayout = view.findViewById(R.id.tableView)
                                try {
                                    group.removeViews(1, array.size)
                                } catch (e: IndexOutOfBoundsException) {

                                }
                                array.clear()
                                Log.d("TTT", "data kelyaptimi")
                                array.addAll(response.body()?.data?.standings!!)

                                array.forEach {
                                    val item = LayoutInflater.from(requireContext())
                                        .inflate(R.layout.team_item, group, false).apply {
                                            this.findViewById<TextView>(R.id.name).text =
                                                it.team.name
                                            this.findViewById<TextView>(R.id.wins).text =
                                                it.stats[0].displayValue
                                            this.findViewById<TextView>(R.id.losses).text =
                                                it.stats[1].displayValue
                                            this.findViewById<TextView>(R.id.draws).text =
                                                it.stats[2].displayValue
                                            this.findViewById<TextView>(R.id.gamesPlayed).text =
                                                it.stats[3].displayValue
                                            this.findViewById<TextView>(R.id.goalsFor).text =
                                                it.stats[4].displayValue
                                            this.findViewById<TextView>(R.id.goalsAginst).text =
                                                it.stats[5].displayValue
                                            this.findViewById<TextView>(R.id.points).text =
                                                it.stats[6].displayValue
                                            this.findViewById<TextView>(R.id.rankChange).text =
                                                it.stats[7].displayValue
                                            this.findViewById<TextView>(R.id.rank).text =
                                                it.stats[8].displayValue
                                            this.findViewById<TextView>(R.id.GoalDiffernce).text =
                                                it.stats[9].displayValue
                                            this.findViewById<TextView>(R.id.pointsPG).text =
                                                it.stats[10].displayValue
                                            this.findViewById<TextView>(R.id.overall).text =
                                                it.stats[11].displayValue
                                            val image: ImageFilterView =
                                                this.findViewById(R.id.Itemimage)

                                            try {
                                                Glide.with(image)
                                                    .load(it.team.logos[0].href)
                                                    .into(image)
                                            } catch (e: NullPointerException) {
                                                Toast.makeText(
                                                    requireContext(),
                                                    "some images can be null",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }



                                    Log.d("TTT", "data ni yuklayappiz ${it.stats[2].displayValue}")
                                    group.addView(item)
                                }
                                progressBar.cancel()
                            } else Log.d("TTT", "data ni yuklayappiz ${array.size}")
                        }

                        override fun onFailure(
                            call: Call<BaseResponse<StandingsDATA>>,
                            t: Throwable
                        ) {
                            progressBar.cancel()
                            Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                            Log.d("TTT", "data kelmadi nimagadir")
                        }

                    })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

}