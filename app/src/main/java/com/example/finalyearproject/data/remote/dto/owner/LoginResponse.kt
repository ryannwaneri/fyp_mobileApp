package com.example.finalyearproject.data.remote.dto.owner

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class LoginResponse(
    val email:String,
    val password:String,
    val firstName:String,
    val lastName:String,
    val number:String,
    val id: Long
):Parcelable

