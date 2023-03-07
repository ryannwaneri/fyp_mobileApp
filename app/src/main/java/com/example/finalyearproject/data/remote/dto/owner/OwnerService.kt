package com.example.finalyearproject.data.remote.dto.owner

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface OwnerService {

    suspend fun createOwner(signUpRequest: SignUpRequest): SignUpResponse?

    suspend fun ownerLogin(loginRequest: LoginRequest):LoginResponse?

    @kotlinx.serialization.Serializable
    companion object {
        fun create(): OwnerService{
            return OwnerServiceImplementation(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}