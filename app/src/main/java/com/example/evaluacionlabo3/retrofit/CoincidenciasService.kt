package com.example.evaluacionlabo3.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val MOVIE_API_BASE_URL = "http://www.omdbapi.com/"

interface CoincidenciaService{

    @GET("/")
    fun getMovies(@Query("s")clue : String, @Query("apikey") apikey:String) : Deferred<Response<Coincidencias>>

    companion object {
        fun getCoincidenciaService(): CoincidenciaService{

            return Retrofit.Builder()
                .baseUrl(MOVIE_API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(CoincidenciaService::class.java)
        }
    }
}