package com.example.finalyearproject.data.remote.dto.user

@kotlinx.serialization.Serializable
class GetRegUser(
    var fingerprint_id: Int,
    var device: String,
    var pin_code:String,
    var first_name:String,
    var last_name:String,
    var phone_number:String,
    var id:Long =-1
)