package com.example.finalyearproject.data.remote.dto.owner

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LoginRequest(
    val email:String,
    val password:String,
    val firstName:String=" ",
    val lastName:String=" ",
    val number:String=" ",
    val id: Long=-1
)