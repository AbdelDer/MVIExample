package com.kadder.mviexample.ui.main.state

import com.kadder.mviexample.model.Photo
import com.kadder.mviexample.model.User

data class MainViewState(
    var photos: List<Photo>? = null,
    var user: User? = null
) {
}