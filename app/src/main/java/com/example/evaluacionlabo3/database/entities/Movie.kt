package com.example.evaluacionlabo3.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movie")
data class Movie(
    @field:Json(name="Title")
    var Title:String = "N/A",
    @field:Json(name="Year")
    var Year:String = "N/A",
    @PrimaryKey
    @field:Json(name="imdbID")
    var imdbID:String = "N/A",
    @field:Json(name="Type")
    var Type:String = "N/A",
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

) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(Title)
        writeString(Year)
        writeString(imdbID)
        writeString(Type)
        writeString(Poster)

        writeString(Plot)
        writeString(Country)
        writeString(imdbRating)
        writeString(Runtime)
        writeString(Director)
        writeString(Actors)
        writeString(Rated)
        writeString(Genre)
        writeString(Released)

    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}