package com.kadder.mviexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(
    @Expose
    @SerializedName("id")
    val id: String?,
    @Expose
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("url") val url: String?
) {
    override fun toString(): String {
        return super.toString()
    }
}
