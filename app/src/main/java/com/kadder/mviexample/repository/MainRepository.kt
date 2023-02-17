package com.kadder.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kadder.mviexample.api.MVIRetrofitBuilder
import com.kadder.mviexample.ui.main.state.MainViewState
import com.kadder.mviexample.util.ApiEmptyResponse
import com.kadder.mviexample.util.ApiErrorResponse
import com.kadder.mviexample.util.ApiSuccessResponse
import com.kadder.mviexample.util.DataState

object MainRepository {
    fun getPhotos(): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(MVIRetrofitBuilder.apiService.getPhotos()) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    when (apiResponse) {
                        is ApiSuccessResponse -> {
                            value = DataState.data(
                                message = null,
                                data = MainViewState(
                                    photos = apiResponse.body
                                )
                            )
                        }
                        is ApiErrorResponse -> {
                            value =
                                DataState.error(message = apiResponse.errorMessage)
                        }
                        is ApiEmptyResponse -> {
                            value =
                                DataState.error(message = "HTTP 204. Returned NOTHING!")
                        }
                    }
                }
            }
        }
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(MVIRetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    when (apiResponse) {
                        is ApiSuccessResponse -> {
                            value = DataState.data(
                                message = null,
                                data = MainViewState(
                                    user = apiResponse.body
                                )
                            )
                        }
                        is ApiErrorResponse -> {
                            value =
                                DataState.error(message = apiResponse.errorMessage)
                        }
                        is ApiEmptyResponse -> {
                            value =
                                DataState.error(message = "HTTP 204. Returned NOTHING!")
                        }
                    }
                }
            }
        }
    }
}