package com.example.retrofit.model.leagueResponce



data class LeagueDetailResponse(
    val abbr: String,
    val id: String,
    val logos: Logos,
    val name: String,
    val slug: String
)