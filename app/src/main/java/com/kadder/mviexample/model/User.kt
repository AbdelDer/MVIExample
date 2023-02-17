package com.kadder.mviexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("email")
    val email: String?,
    @Expose
    @SerializedName("username")
    val username: String?,
    @Expose
    @SerializedName("image") val image: String = "https://rb.gy/ehjiio"
){
    override fun toString(): String {
        return "User(email=$email, username=$username, image=$image)"
    }
}
