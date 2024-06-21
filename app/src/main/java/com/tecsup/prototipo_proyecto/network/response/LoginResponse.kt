package com.tecsup.prototipo_proyecto.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("token")
    var token: String,

    @SerializedName("username")
    var username: String
)