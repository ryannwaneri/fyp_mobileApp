package com.example.finalyearproject.data.remote.dto.device

import android.os.Parcelable
import com.example.finalyearproject.data.remote.dto.owner.SignUpRequest
import kotlinx.parcelize.Parcelize

@Parcelize
@kotlinx.serialization.Serializable
class GetDevicesResponse (
    val uniqueKey:String,
    val owner: SignUpRequest,
    val masterCode:String,
    val id: Long = -1
    ):Parcelable