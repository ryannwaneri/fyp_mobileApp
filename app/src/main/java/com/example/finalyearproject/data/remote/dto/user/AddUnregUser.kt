package com.example.finalyearproject.data.remote.dto.user

@kotlinx.serialization.Serializable
class AddUnregUser(
    var fingerprint_id: Int,
    var deviceId: String,
    var pin_code:String,
    var firstname:String,
    var lastname:String,
    var number:String,
    var id:Long =-1
)