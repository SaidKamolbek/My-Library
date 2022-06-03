package com.example.retrofit.model.standings

data class STATS(
    val team: STeam,
    val note: Notes,
    val stats: List<Stat>
)