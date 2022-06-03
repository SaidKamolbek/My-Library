package com.example.retrofit.model.seasons

data class Seasons(
    val displayName: String,
    val endDate: String,
    val startDate: String,
    val types: List<Type>,
    val year: Int
)