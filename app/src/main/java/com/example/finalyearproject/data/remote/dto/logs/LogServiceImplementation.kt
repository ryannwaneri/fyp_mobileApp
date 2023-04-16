package com.example.finalyearproject.data.remote.dto.logs

import android.util.Log
import com.example.finalyearproject.HttpRoutes
import com.example.finalyearproject.data.remote.dto.owner.LogService
import io.ktor.client.*
import io.ktor.client.request.*

class LogServiceImplementation(
 private var client : HttpClient
):LogService{
    override suspend fun getLogs(deviceId: String): MutableList<GetLogs> {
        return try{
            client.get{
                url(HttpRoutes.GET_LOGS)
                parameter("device",deviceId)
            }
        }
        catch(e:java.lang.Exception){
            Log.d("tag", "Error ${e.message}")
            mutableListOf<GetLogs>()
        }
    }

}