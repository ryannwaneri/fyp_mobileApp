package com.example.finalyearproject.data.remote.dto.owner

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
@SerialName("signUpRequest")
data class SignUpRequest(
    @SerialName("email") val email:String,
    @SerialName("password")val password:String,
    @SerialName("firstName")val firstName:String,
    @SerialName("lastName")val lastName:String,
    @SerialName("number")val number:String,
    @SerialName("id")val id: Long = -1
):Parcelable