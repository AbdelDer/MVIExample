package com.kadder.mviexample.repository

import androidx.lifecycle.LiveData
import com.kadder.mviexample.api.MVIRetrofitBuilder
import com.kadder.mviexample.model.Photo
import com.kadder.mviexample.model.User
import com.kadder.mviexample.ui.main.state.MainViewState
import com.kadder.mviexample.util.ApiSuccessResponse
import com.kadder.mviexample.util.DataState
import com.kadder.mviexample.util.GenericApiResponse

object MainRepository {
    fun getPhotos(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<Photo>, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Photo>>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        photos = response.body,
                        user = null
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Photo>>> {
                return MVIRetrofitBuilder.apiService.getPhotos()
            }

        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<User, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        photos = null,
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return MVIRetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()
    }
}