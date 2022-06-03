package com.example.retrofit.model.standings

data class Stat(
    val abbreviation: String,
    val description: String,
    val displayName: String,
    val displayValue: String,
    val name: String,
    val shortDisplayName: String,
    val type: String,
    val value: Int
)