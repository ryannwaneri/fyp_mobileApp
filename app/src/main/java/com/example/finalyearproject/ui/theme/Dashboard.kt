package com.example.finalyearproject.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.data.remote.dto.owner.LoginResponse
import com.example.finalyearproject.data.remote.dto.owner.SignUpResponse
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalyearproject.addDevicePopUp
import com.example.finalyearproject.data.remote.dto.device.AddDeviceRequest
import com.example.finalyearproject.data.remote.dto.device.DeviceService
import com.example.finalyearproject.data.remote.dto.device.GetDevicesResponse
import com.example.finalyearproject.data.remote.dto.owner.SignUpRequest
import com.example.finalyearproject.destinations.deviceScreenDestination
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

val apiService by lazy {
    DeviceService.create()
}

var update = mutableStateOf(false)

@Destination
@Composable
fun dashboard(owner: LoginResponse, navigator: DestinationsNavigator){

    Box(modifier= Modifier
        .fillMaxSize()
        .background(Color.White)){

        dashScaffold(owner,navigator)
        addDevice(SignUpRequest(owner.email,owner.password,owner.firstName,owner.lastName,owner.number,owner.id))


    }
}

@Composable
fun dashScaffold(owner:LoginResponse,  navigator: DestinationsNavigator){

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext

    var products = produceState(
        initialValue = remember{mutableStateListOf<GetDevicesResponse>()},
        producer = {
            value = apiService.getDevices(owner.id).toMutableStateList()
        }
    )

    LaunchedEffect(update.value){
        GlobalScope.launch {
            products.value.clear()
            products.value.addAll(apiService.getDevices(owner.id))
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { dashTopBar(owner) }
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it), // use "it" parameter
            ) {
                Row(modifier = Modifier.padding(top = 16.dp, start = 17.dp)) {
                    Text(
                        "Welcome",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        owner.firstName,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = primary
                    )
                }
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
                    items(products.value){
                        deviceCards(device = it,owner = owner, navigator = navigator)
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .clickable { addDevicePopUp.value = true },
                backgroundColor = primary,
                onClick = {
                    addDevicePopUp.value=true
                }) {
                Icon(
                    painter = painterResource(id = com.example.finalyearproject.R.drawable.add),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.clickable {  addDevicePopUp.value=true }
                )
            }
        }
    }
}

@Composable
fun dashTopBar(owner:LoginResponse){
    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {Text("Safe-T", fontSize = 30.sp, color = primary)},
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = {

            Icon(
                painterResource(id = com.example.finalyearproject.R.drawable.refresh),
                "refresh",
                tint = Color.Black,
                modifier = Modifier
                    .clickable {
                        update.value= if(update.value == true) false else true
                    }
                    .padding(horizontal = 16.dp)
            )

            Icon(
                painterResource(id = com.example.finalyearproject.R.drawable.letter1),
                "nav",
                tint = Color.Black,
                modifier = Modifier
                    .clickable { dropDownMenuExpanded = true }
                    .padding(horizontal = 16.dp)
            )

            DropdownMenu(expanded =dropDownMenuExpanded ,
                onDismissRequest = {dropDownMenuExpanded = false}
            ) {
                DropdownMenuItem(onClick = {

                }) {
                    Text("Settings")
                }

                DropdownMenuItem(onClick = {

                }) {
                    Text("Sign Out")
                }
            }
        }
    )
}

@Composable
fun addDevice(owner: SignUpRequest){

    var uniqueKey by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var masterCode by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var confirmCode by remember {
        mutableStateOf(TextFieldValue(""))
    }
AnimatedVisibility(visible = addDevicePopUp.value) {
    Box(
        Modifier
            .fillMaxSize()
            .clickable { }
            .background(Color.Black.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            Modifier
                .fillMaxWidth(0.85f)
                .background(Color.White)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, top = 18.dp), contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Add New Device",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp, vertical = 24.dp)
            ) {
                TextField(
                    value = uniqueKey,
                    onValueChange = { change ->
                        uniqueKey = change
                    },
                    placeholder = { Text("Enter Unique Device Key") }
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = masterCode,
                    onValueChange = { change ->
                        masterCode = change
                    },
                    placeholder = { Text("Enter master code") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = confirmCode,
                    onValueChange = { change ->
                        confirmCode = change
                    },
                    placeholder = { Text("Confirm master code") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = { addDevicePopUp.value = false },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = disabled,
                        contentColor = disabledText
                    )
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        if(confirmCode.text==masterCode.text){
                            var device=AddDeviceRequest(
                                uniqueKey.text,
                                owner,
                                masterCode.text,
                            )
                            GlobalScope.launch {
                                apiService.addDevice(device)
                            }
                            addDevicePopUp.value = false
                        }
                              },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = primary,
                        contentColor = Color.White
                    )
                ) {
                    Text("Add")
                }

            }

        }
    }
}
}

@Composable
fun deviceCards(device: GetDevicesResponse, owner:LoginResponse, navigator: DestinationsNavigator){
    Card(
        backgroundColor = lightPrimary,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 18.dp)
            .clip(RoundedCornerShape(20.dp))
    ){
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth()){
                Text(
                    "Device"+device.id,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp, top = 16.dp)
                        .clickable {
                           navigator.navigate(
                                deviceScreenDestination(owner,device)
                            )
                        }
                )
                Icon(
                    painter = painterResource(id = com.example.finalyearproject.R.drawable.delete),
                    contentDescription = "delete",
                    tint = primary,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(top = 16.dp, end = 16.dp)
                        .clickable {
                            GlobalScope.launch {
                                apiService.deleteDevice(device.id)
                            }
                        }
                )
            }
            Text(
                device.uniqueKey,
                fontSize = 25.sp,
                color = primary,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 16.dp)
            )
        }
    }
}
