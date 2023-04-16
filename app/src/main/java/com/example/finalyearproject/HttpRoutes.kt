package com.example.finalyearproject

object HttpRoutes {
    private const val BASE_URL="https://fypbackend-production.up.railway.app"
    const val SIGN_UP= BASE_URL+"/signUp"
    const val LOGIN= BASE_URL+"/login"
    const val ADD_DEVICE= BASE_URL+"/createDevice"
    const val GET_DEVICES= BASE_URL+"/devices"
    const val GET_REG_USERS= BASE_URL+"/getRegUsers"
    const val GET_UNREG_USERS= BASE_URL+"/getUnregUsers"
    const val DELETE_DEVICE= BASE_URL+"/deleteDevice"
    const val REGISTER_USER= BASE_URL+"/registerUser"
    const val REVOKE_ACCESS= BASE_URL+"/revokeAccess"
    const val GET_LOGS= BASE_URL+"/getLog"
    const val GET_USER= BASE_URL+"/getUser"
}