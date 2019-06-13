package com.example.evaluacionlabo3.retrofit

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class MovieDetails (
    @field:Json(name="Title")
    var Title:String = "N/A",
    @field:Json(name="imdbID")
    var imdbID:String = "N/A",
    @field:Json(name="Type")
    var Type:String = "N/A",
    @field:Json(name="Year")
    var Year:String = "N/A",
    @field:Json(name="Poster")
    var Poster: String = "N/A",
    @field:Json(name="Plot")
    var Plot:String = "N/A",
    @field:Json(name="Country")
    var Country: String = "N/A",
    @field:Json(name="imdbRating")
    var imdbRating: String = "N/A",
    @field:Json(name="Runtime")
    var Runtime: String = "N/A",
    @field:Json(name="Director")
    var Director: String = "N/A",
    @field:Json(name="Actors")
    var Actors : String = "N/A",
    @field:Json(name="Rated")
    var Rated: String = "N/A",
    @field:Json(name="Genre")
    var Genre : String = "N/A",
    @field:Json(name="Released")
    var Released: String = "N/A"
)
