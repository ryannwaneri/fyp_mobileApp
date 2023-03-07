package com.example.finalyearproject.data.remote.dto.owner

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
@SerialName("signUpResponse")
data class SignUpResponse(
    @SerialName("email") val email: String,
    @SerialName("password") val password:String,
    @SerialName("firstName") val firstName:String,
    @SerialName("lastName") val lastName:String,
    @SerialName("number") val number:String,
    @SerialName("id") val id: Long
)