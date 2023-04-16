package com.example.finalyearproject.data.remote.dto.user

@kotlinx.serialization.Serializable
class GetUnregUser(
    var fingerprint_id: Int,
    var deviceId: String,
    var pin_code:String,
    var date:String,
    var time:String,
    var id:Long =-1
)