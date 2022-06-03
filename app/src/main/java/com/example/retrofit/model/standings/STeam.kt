package com.example.retrofit.model.standings

data class STeam(
    val id: String,
    val uid: String,
    val location: String,
    val name: String,
    val abbreviation: String,
    val displayName: String,
    val shortDisplayName: String,
    val isActive: String,
    val logos: List<LOGOS>,
)
