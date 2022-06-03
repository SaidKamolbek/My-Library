package com.example.retrofit.networking.api

import com.example.retrofit.model.*
import com.example.retrofit.model.leagueResponce.LeagueDetailResponse
import com.example.retrofit.model.leagueResponce.LeagueResponse
import com.example.retrofit.model.seasons.Season
import com.example.retrofit.model.standings.STATS
import com.example.retrofit.model.standings.StandingsDATA
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballApi {
    @GET("leagues")
    fun getAllLeagues(): Call<BaseResponse<List<LeagueResponse>>>

    @GET("leagues/{id}")
    fun getLeagueDetailById(@Path("id") id: String): Call<BaseResponse<LeagueDetailResponse>>

    @GET("leagues/{id}/seasons")
    fun getSeasonsById(@Path("id") id: String): Call<BaseResponse<Season>>

    @GET("leagues/{id}/standings")
    fun getStandings(
        @Path("id") id: String,
        @Query("season") season: String,
        @Query("sort") sort: String = "asc"
    ): Call<BaseResponse<StandingsDATA>>

}