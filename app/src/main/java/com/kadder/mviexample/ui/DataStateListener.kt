package com.kadder.mviexample.ui

import com.kadder.mviexample.util.DataState


interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}