package com.kadder.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kadder.mviexample.api.MVIRetrofitBuilder
import com.kadder.mviexample.ui.main.state.MainViewState
import com.kadder.mviexample.util.ApiEmptyResponse
import com.kadder.mviexample.util.ApiErrorResponse
import com.kadder.mviexample.util.ApiSuccessResponse

object MainRepository {
    fun getPhotos(): LiveData<MainViewState> {
        return Transformations.switchMap(MVIRetrofitBuilder.apiService.getPhotos()) { apiResponse ->
            object : LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    when (apiResponse) {
                        is ApiSuccessResponse -> {
                            value = MainViewState(
                                photos = apiResponse.body
                            )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState() //handle error
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState() //handle empty error
                        }
                    }
                }
            }
        }
    }

    fun getUser(userId: String): LiveData<MainViewState> {
        return Transformations.switchMap(MVIRetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
            object : LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    when (apiResponse) {
                        is ApiSuccessResponse -> {
                            value = MainViewState(
                                user = apiResponse.body
                            )
                        }
                        is ApiErrorResponse -> {
                            value = MainViewState() //handle error
                        }
                        is ApiEmptyResponse -> {
                            value = MainViewState() //handle empty error
                        }
                    }
                }
            }
        }
    }
}