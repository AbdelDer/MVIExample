package com.kadder.mviexample.ui.main.state

sealed class MainStateEvent {
    object GetPhotosEvent : MainStateEvent()
    class GetUserEvent(val userId: String) : MainStateEvent()
    object None : MainStateEvent()
}
