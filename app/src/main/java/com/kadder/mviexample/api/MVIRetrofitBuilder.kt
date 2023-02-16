package com.kadder.mviexample.api

import com.kadder.mviexample.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MVIRetrofitBuilder {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
    }

    val apiService: ApiService by lazy {
        retrofitBuilder.build().create(ApiService::class.java)
    }
}