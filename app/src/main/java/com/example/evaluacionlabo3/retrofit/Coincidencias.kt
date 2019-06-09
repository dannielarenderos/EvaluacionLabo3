package com.example.evaluacionlabo3.retrofit

import com.example.evaluacionlabo3.database.entities.Movie
import com.squareup.moshi.Json

data class Coincidencias(
    @field:Json(name="totalResults")
    var totalResults : String,
    @field:Json(name="Search")
    var Search : List<Movie>,
    @field:Json(name="Response")
    var Response : String
)