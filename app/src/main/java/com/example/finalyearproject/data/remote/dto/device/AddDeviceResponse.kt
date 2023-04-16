package com.example.finalyearproject.data.remote.dto.device

import com.example.finalyearproject.data.remote.dto.owner.SignUpRequest

@kotlinx.serialization.Serializable
class AddDeviceResponse(
    val uniqueKey:String,
    val owner: SignUpRequest,
    val masterCode:String,
    val id: Long = -1
)