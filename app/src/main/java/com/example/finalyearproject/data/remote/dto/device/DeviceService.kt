package com.example.finalyearproject.data.remote.dto.device

import com.example.finalyearproject.data.remote.dto.owner.*
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlin.text.Charsets

interface DeviceService {

    suspend fun addDevice(deviceRequest: AddDeviceRequest): AddDeviceResponse?

    suspend fun getDevices(ownerId:Long): List<GetDevicesResponse>

    suspend fun deleteDevice(deviceId:Long)

    @kotlinx.serialization.Serializable
    companion object {
        fun create(): DeviceService {
            return DeviceServiceImplementation(
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