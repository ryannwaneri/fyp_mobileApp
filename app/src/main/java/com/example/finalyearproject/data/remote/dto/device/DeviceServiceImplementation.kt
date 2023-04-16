package com.example.finalyearproject.data.remote.dto.device

import android.util.Log
import com.example.finalyearproject.HttpRoutes
import com.example.finalyearproject.data.remote.dto.owner.SignUpRequest
import com.example.finalyearproject.data.remote.dto.owner.SignUpResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class DeviceServiceImplementation(
    var client:HttpClient
):DeviceService{
    override suspend fun addDevice(deviceRequest: AddDeviceRequest): AddDeviceResponse? {
        return try {
            client.post<AddDeviceResponse> {
                url(HttpRoutes.ADD_DEVICE)
                contentType(ContentType.Application.Json)
                body = deviceRequest
            }
        }
        catch(e: Exception) {
           AddDeviceResponse(
               e.message.toString(),
               SignUpRequest("","","","","",-1),
               "",
               -1L
           )
        }
    }

    override suspend fun getDevices(ownerId : Long): MutableList<GetDevicesResponse> {
        return try{
            client.get<MutableList<GetDevicesResponse>> {
                url(HttpRoutes.GET_DEVICES)
                parameter("ownerId",ownerId)
            }
        }
        catch (e:java.lang.Exception){
            mutableListOf<GetDevicesResponse>()
        }
    }

    override suspend fun deleteDevice(deviceId : Long) {
        try{
            client.delete {
                url(HttpRoutes.DELETE_DEVICE)
                parameter("deviceId",deviceId)
            }
        }
        catch (e:java.lang.Exception){
        }
    }

}