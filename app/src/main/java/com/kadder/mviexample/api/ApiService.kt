package com.kadder.mviexample.api

import com.kadder.mviexample.model.Photo
import com.kadder.mviexample.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: String): User
    @GET("photos")
    fun getPhotos(): List<Photo>
}