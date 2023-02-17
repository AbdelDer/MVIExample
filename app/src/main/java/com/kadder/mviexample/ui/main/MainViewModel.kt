package com.kadder.mviexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kadder.mviexample.model.Photo
import com.kadder.mviexample.model.User
import com.kadder.mviexample.repository.MainRepository
import com.kadder.mviexample.ui.main.state.MainStateEvent
import com.kadder.mviexample.ui.main.state.MainViewState
import com.kadder.mviexample.util.AbsentLiveData
import com.kadder.mviexample.util.DataState

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val viewState: LiveData<MainViewState> get() = _viewState
    val dataState: LiveData<DataState<MainViewState>> = Transformations.switchMap(_stateEvent) { stateEvent ->
        stateEvent?.let {
            when (stateEvent) {
                is MainStateEvent.GetPhotosEvent -> {
                    MainRepository.getPhotos()
                }
                is MainStateEvent.GetUserEvent -> {
                    MainRepository.getUser(stateEvent.userId)
                }
                is MainStateEvent.None -> {
                    AbsentLiveData.create()
                }
            }
        }
    }

    fun setPhotosData(photos: List<Photo>) {
        val update = getCurrentViewStateOrNew()
        update.photos = photos
        _viewState.value = update
    }

    fun setUserData(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    private fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value ?: MainViewState()
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }

}