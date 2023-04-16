package com.example.finalyearproject.data.remote.dto.owner

import com.example.finalyearproject.data.remote.dto.user.AddUnregUser
import com.example.finalyearproject.data.remote.dto.user.GetRegUser
import com.example.finalyearproject.data.remote.dto.user.GetUnregUser
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlin.text.Charsets

interface UserService {

    suspend fun getRegUser(deviceId : Long): MutableList<GetRegUser>

    suspend fun getUnregUser(deviceId : String): MutableList<GetUnregUser>

    suspend fun revokeRegUser(userId : Long)

    suspend fun addRegUser(user : AddUnregUser)

    suspend fun getUser(userId : Long) : GetRegUser


    @kotlinx.serialization.Serializable
    companion object {
        fun create(): UserService{
            return UserServiceImplementation(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                    Charsets {
                        // Allow using `UTF_8`.
                        register(Charsets.UTF_8)

                        // Allow using `ISO_8859_1` with quality 0.1.
                        register(Charsets.ISO_8859_1, quality=0.1f)

                    }
                }
            )
        }
    }
}