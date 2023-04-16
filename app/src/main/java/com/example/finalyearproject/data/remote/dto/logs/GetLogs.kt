package com.example.finalyearproject.data.remote.dto.logs

@kotlinx.serialization.Serializable
class GetLogs(
    var date: String,
    var time: String,
    var userId: Int,
    var deviceId: String,
    var id:Long =-1
)