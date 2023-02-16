package com.kadder.mviexample.api

import androidx.lifecycle.LiveData
import com.kadder.mviexample.model.Photo
import com.kadder.mviexample.model.User
import com.kadder.mviexample.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: String): LiveData<GenericApiResponse<User>>

    @GET("photos")
    fun getPhotos(): LiveData<GenericApiResponse<List<Photo>>>
}