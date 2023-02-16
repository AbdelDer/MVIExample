package com.kadder.mviexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kadder.mviexample.ui.main.state.MainStateEvent
import com.kadder.mviexample.ui.main.state.MainViewState
import com.kadder.mviexample.util.AbsentLiveData

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val viewState: LiveData<MainViewState> get() = _viewState
    val dataState: LiveData<MainViewState> = Transformations.switchMap(_stateEvent) { stateEvent ->
        stateEvent?.let {
            when (stateEvent) {
                is MainStateEvent.GetPhotosEvent -> {
                    AbsentLiveData.create<MainViewState>()
                }
                is MainStateEvent.GetUserEvent -> {
                    AbsentLiveData.create()
                }
                is MainStateEvent.None -> {
                    AbsentLiveData.create()
                }
            }
        }
    }
}