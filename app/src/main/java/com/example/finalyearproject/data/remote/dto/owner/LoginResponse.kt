package com.example.finalyearproject.data.remote.dto.owner

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LoginResponse(
    val response:String
)