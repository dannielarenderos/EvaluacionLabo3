package com.example.evaluacionlabo3.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    var Title:String,
    var Year:String,
    @PrimaryKey
    var imdbID:String,
    var Type:String,
    var Poster: String
)