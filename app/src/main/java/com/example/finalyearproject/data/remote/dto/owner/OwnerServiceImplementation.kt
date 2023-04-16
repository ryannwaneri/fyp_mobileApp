package com.example.finalyearproject.data.remote.dto.owner

import android.util.Log
import com.example.finalyearproject.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class OwnerServiceImplementation(
    private val client:HttpClient
):OwnerService {
    override suspend fun createOwner(signUpRequest: SignUpRequest): SignUpResponse? {
        return try {
            client.post<SignUpResponse> {
                url(HttpRoutes.SIGN_UP)
                contentType(ContentType.Application.Json)
                body = signUpRequest
            }
        }
        catch(e: Exception) {
            Log.d("tga","Error: ${e.message}")
            SignUpResponse(e.message.toString(),"","","","",-1)
        }
    }

    override suspend fun ownerLogin(loginRequest: LoginRequest): LoginResponse? {
        return try {
            client.post<LoginResponse> {
                contentType(ContentType.Application.Json)
                url(HttpRoutes.LOGIN)
                //contentType(ContentType.Application.Json)
                body = loginRequest
            }
        }
        catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }


}
