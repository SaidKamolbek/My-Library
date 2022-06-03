package com.example.retrofit.model.standings

data class StandingsDATA(
    val name: String,
    val abbreviation: String,
    val seasonDisplay: String,
    val season: String,
    val standings: List<STATS>

)