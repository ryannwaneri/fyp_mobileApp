package com.example.finalyearproject

import android.graphics.drawable.Drawable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.data.remote.dto.owner.LoginResponse
import com.example.finalyearproject.ui.theme.primary
import com.example.finalyearproject.R.drawable


var addDevicePopUp = mutableStateOf(false)
var deviceScreenState = mutableStateOf(1)
var usersScreenState = mutableStateOf("R")
var addUserPopup = mutableStateOf(false)
