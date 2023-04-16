package com.example.finalyearproject.data.remote.dto.owner

import android.util.Log
import com.example.finalyearproject.HttpRoutes
import com.example.finalyearproject.data.remote.dto.device.GetDevicesResponse
import com.example.finalyearproject.data.remote.dto.user.AddUnregUser
import com.example.finalyearproject.data.remote.dto.user.GetRegUser
import com.example.finalyearproject.data.remote.dto.user.GetUnregUser
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class UserServiceImplementation(
    private val client:HttpClient
):UserService {
    override suspend fun getRegUser(deviceId : Long): MutableList<GetRegUser>{
        return try {
            client.get{
                    url(HttpRoutes.GET_REG_USERS)
                    parameter("deviceId",deviceId)
            }
        }
        catch(e: Exception) {
         mutableListOf<GetRegUser>()
        }
    }

    override suspend fun getUnregUser(deviceId: String): MutableList<GetUnregUser>{
        return try {
            client.get{
                url(HttpRoutes.GET_UNREG_USERS)
                parameter("deviceId",deviceId)
            }
        }
        catch(e: Exception) {
            mutableListOf<GetUnregUser>()
        }
    }

    override suspend fun revokeRegUser(userId : Long) {
        try{
            client.delete {
                url(HttpRoutes.REVOKE_ACCESS)
                parameter("userId",userId)
            }
        }
        catch (e:java.lang.Exception){
        }    }

    override suspend fun addRegUser(user: AddUnregUser) {
        try {
            client.post{
                url(HttpRoutes.REGISTER_USER)
                contentType(ContentType.Application.Json)
                body = user
            }
        }
        catch(e: Exception) {
            Log.d("tag","Error: ${e.message}")
        }
    }

    override suspend fun getUser(userId: Long): GetRegUser {
        return try {
            client.get{
                url(HttpRoutes.GET_USER)
                parameter("userId",userId)
            }
        }
        catch(e: Exception) {
            GetRegUser(-1,"","","","","")
        }
    }
}
