package com.example.finalyearproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.data.remote.dto.device.GetDevicesResponse
import com.example.finalyearproject.data.remote.dto.logs.GetLogs
import com.example.finalyearproject.data.remote.dto.owner.LogService
import com.example.finalyearproject.data.remote.dto.owner.LoginResponse
import com.example.finalyearproject.data.remote.dto.owner.UserService
import com.example.finalyearproject.data.remote.dto.user.AddUnregUser
import com.example.finalyearproject.data.remote.dto.user.GetRegUser
import com.example.finalyearproject.data.remote.dto.user.GetUnregUser
import com.example.finalyearproject.destinations.dashboardDestination
import com.example.finalyearproject.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

var devUpdate = mutableStateOf(true)

val devapiService by lazy {
    UserService.create()
}

val logapiService by lazy {
    LogService.create()
}

val regUserState = mutableStateOf(GetUnregUser(-1,"","","",""))

@Destination
@Composable
fun deviceScreen(navigator: DestinationsNavigator, owner:LoginResponse, device: GetDevicesResponse){

    Box(modifier= Modifier
        .fillMaxSize()
        .background(Color.White)){
        deviceScaffold(device,owner,navigator)
    }
}

@Composable
fun deviceScaffold(device: GetDevicesResponse,owner:LoginResponse,navigator: DestinationsNavigator) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { deviceTopBar(navigator, owner) },
        bottomBar = { deviceBottomBar()}
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it), // use "it" parameter
            ) {
                detailsScreen(device = device)
                usersScreen(device = device)
                logsScreen(device = device)
            }
        }

    }
}

@Composable
fun deviceTopBar(navigator: DestinationsNavigator, owner: LoginResponse) {
    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Icon(
                painterResource(id = R.drawable.back),
                "back",
                Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        navigator.navigate(
                            dashboardDestination(owner)
                        )
                    },
                tint = Color.White
            )
            Text("Device", fontSize = 30.sp, color = Color.White)
        },
        backgroundColor = primary,
        elevation = 0.dp,
        actions = {

            Icon(
                painterResource(id = R.drawable.refresh),
                "refresh",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        devUpdate.value = !devUpdate.value
                    }
                    .padding(horizontal = 16.dp)
            )

            Icon(
                painterResource(id = R.drawable.letter1),
                "nav",
                tint = Color.White,
                modifier = Modifier
                    .clickable { dropDownMenuExpanded = true }
                    .padding(horizontal = 16.dp)
            )

            DropdownMenu(expanded = dropDownMenuExpanded,
                onDismissRequest = { dropDownMenuExpanded = false }
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
fun deviceBottomBar() {
    BottomAppBar(backgroundColor = Color.Black) {
        BottomNavigationItem(
            label = {
                Text(
                    "Details",
                    color = if (deviceScreenState.value == 1) primary else disabled
                )
            },
            icon = {
                Icon(
                    painterResource(id = R.drawable.details),
                    "details",
                    tint = if (deviceScreenState.value == 1) primary else disabled
                )
            },
            selected = deviceScreenState.value == 1,
            onClick = { deviceScreenState.value = 1 ; devUpdate.value = !devUpdate.value}
        )

        BottomNavigationItem(
            label = {
                Text(
                    "Users",
                    color = if (deviceScreenState.value == 2) primary else disabled
                )
            },
            icon = {
                Icon(
                    painterResource(id = R.drawable.users),
                    "users",
                    tint = if (deviceScreenState.value == 2) primary else disabled

                )
            },
            selected = deviceScreenState.value == 2,
            onClick = { deviceScreenState.value = 2 ; devUpdate.value = !devUpdate.value}
        )

        BottomNavigationItem(
            label = {
                Text(
                    "logs",
                    color = if (deviceScreenState.value == 3) primary else disabled
                )
            },
            icon = {
                Icon(
                    painterResource(id = R.drawable.logs),
                    "logs",
                    tint = if (deviceScreenState.value == 3) primary else disabled

                )
            },
            selected = deviceScreenState.value == 3,
            onClick = { deviceScreenState.value = 3 ; devUpdate.value = !devUpdate.value}
        )
    }
}

@Composable
fun detailsScreen(device: GetDevicesResponse){
    AnimatedVisibility(
        visible = deviceScreenState.value==1,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Card(
                modifier= Modifier
                    .fillMaxWidth(0.91f)
                    .padding(top = 18.dp),
                backgroundColor = lighterPrimary,
                shape = RoundedCornerShape(25.dp),
                elevation = 5.dp
                ) {
                Column {
                    Row(
                        Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp,)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                "Device Id",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = primary
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                "Device " + device.id,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                        }
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                "Master Password",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = primary
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                device.masterCode,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Unique Id",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = primary
                        )
                        Text(
                            device.uniqueKey,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun usersScreen(device: GetDevicesResponse){
    AnimatedVisibility(
        visible = deviceScreenState.value==2,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ){
        Column(Modifier.fillMaxSize()){
            Row(
                modifier = Modifier
                    .padding(vertical = 25.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Card(
                    modifier = Modifier
                        .clickable { usersScreenState.value = "R" }
                        .padding(start = 16.dp),
                    shape = RoundedCornerShape(40.dp),
                    backgroundColor = if(usersScreenState.value == "R") primary else disabled
                ){
                    Text(
                        "Registered Users",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if(usersScreenState.value=="R") lighterPrimary else disabledText,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Card(
                    modifier = Modifier
                        .clickable { usersScreenState.value = "U" }
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(40.dp),
                    backgroundColor = if(usersScreenState.value == "U") primary else disabled
                ){
                    Text(
                        "Unregistered Users",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if(usersScreenState.value=="U") lighterPrimary else disabledText,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Box(Modifier.fillMaxSize()){
                androidx.compose.animation.AnimatedVisibility(visible = usersScreenState.value == "R") {
                    Box(Modifier.fillMaxSize()) {
                        regUsers(device = device)
                    }
                }
                androidx.compose.animation.AnimatedVisibility(visible = usersScreenState.value == "U") {
                    Box(Modifier.fillMaxSize()) {
                        unregUsers(device = device)
                    }
                }
            }
        }
        registerUser()
    }
}

@Composable
fun regUsers(device: GetDevicesResponse){

    var R_users = produceState(
        initialValue = remember{ mutableStateListOf<GetRegUser>() },
        producer = {
            value = devapiService.getRegUser(device.id).toMutableStateList()
        }
    )

    LaunchedEffect(devUpdate.value){
        GlobalScope.launch {
            R_users.value.clear()
            R_users.value.addAll(devapiService.getRegUser(device.id))
        }
    }

    Column(Modifier.fillMaxSize()){
        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),contentAlignment = Alignment.TopStart){
            Text(
                "Registered Users",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
            items(R_users.value){
                r_users_tab(user = it)
            }
        }
    }
}

@Composable
fun r_users_tab(user : GetRegUser){
    Column(
        Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                Text(
                    user.first_name+" "+user.last_name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    user.phone_number,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = disabled
                )
            }
            Card(shape = CircleShape, backgroundColor = primary) {
                Icon(
                    painterResource(id = R.drawable.delete),
                    "revoke access",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            GlobalScope.launch {
                                devapiService.revokeRegUser(user.id)
                                devUpdate.value = if (devUpdate.value == true) false else true
                            }
                        },
                    tint = Color.White
                )
            }
        }
        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(disabled)
                .padding(top = 6.dp)
        )
    }

}

@Composable
fun unregUsers(device: GetDevicesResponse){

    var U_users = produceState(
        initialValue = remember{ mutableStateListOf<GetUnregUser>() },
        producer = {
            value = devapiService.getUnregUser(device.uniqueKey).toMutableStateList()
        }
    )

    LaunchedEffect(devUpdate.value){
        GlobalScope.launch {
            U_users.value.clear()
            U_users.value.addAll(devapiService.getUnregUser(device.uniqueKey))
        }
    }

    Column(Modifier.fillMaxSize()){
        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),contentAlignment = Alignment.TopStart){
            Text(
                "Unregistered Users",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
            items(U_users.value){
                u_users_tab(user = it)
            }
        }
    }
}

@Composable
fun u_users_tab(user : GetUnregUser){
    Column(
        Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                user.fingerprint_id.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Card(shape = CircleShape, backgroundColor = primary, modifier = Modifier.clickable {
                regUserState.value = user
                addUserPopup.value = true
            }){
                Icon(
                    painterResource(id = R.drawable.edit),
                    "grant user access",
                    Modifier
                        .padding(3.dp),
                    tint = Color.White
                )
            }
            Text(
                user.date,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                color = primary
            )
            Text(
                user.time,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                color = primary
            )
        }
        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(disabled)
                .padding(top = 10.dp)
        )
    }
}

@Composable
fun registerUser(){

    var firstName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var lastName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var number by remember {
        mutableStateOf(TextFieldValue(""))
    }
    AnimatedVisibility(visible = addUserPopup.value) {
        Box(
            Modifier
                .fillMaxSize()
                .clickable { }
                .background(Color.Black.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.95f)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 16.dp)){
                    Text(
                        "Register User ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                    )
                    Text(
                        regUserState.value.fingerprint_id.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        color = primary
                    )
                }
                Column(
                    Modifier
                        .padding(top = 25.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    TextField(
                        value = firstName,
                        onValueChange = { change ->
                            firstName = change
                        },
                        placeholder = { Text("Enter First Name") }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = lastName,
                        onValueChange = { change ->
                            lastName = change
                        },
                        placeholder = { Text("Enter Last Name") }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = number,
                        onValueChange = { change ->
                            number = change
                        },
                        placeholder = { Text("Enter Phone Number") }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        Modifier
                            .fillMaxWidth(0.85f)
                            .clickable {
                                addUserPopup.value = false
                            },
                        backgroundColor = disabled,
                    ){
                        Text(
                            "Cancel",
                            fontSize = 20.sp,
                            color = disabledText,
                            modifier = Modifier.padding(vertical = 6.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Card(
                        Modifier
                            .fillMaxWidth(0.85f)
                            .clickable {
                                GlobalScope.launch {
                                    devapiService.addRegUser(
                                        AddUnregUser(
                                            regUserState.value.fingerprint_id,
                                            regUserState.value.deviceId,
                                            regUserState.value.pin_code,
                                            firstName.text,
                                            lastName.text,
                                            number.text,
                                            regUserState.value.id
                                        )
                                    )
                                }
                                addUserPopup.value = false
                                devUpdate.value = if (devUpdate.value == true) false else true
                            },
                        backgroundColor = primary,
                    ){
                        Text(
                            "Register",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 6.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun logsScreen(device: GetDevicesResponse){

    var logs = produceState(
        initialValue = remember{ mutableStateListOf<GetLogs>() },
        producer = {
            value = logapiService.getLogs(device.id.toString()).toMutableStateList()
        }
    )

    LaunchedEffect(devUpdate.value){
        GlobalScope.launch {
            logs.value.clear()
            logs.value.addAll(logapiService.getLogs(device.id.toString()))
        }
    }

    var R_users = produceState(
        initialValue = remember{ mutableStateListOf<GetRegUser>() },
        producer = {
            value = devapiService.getRegUser(device.id).toMutableStateList()
        }
    )

    LaunchedEffect(devUpdate.value){
        GlobalScope.launch {
            R_users.value.clear()
            R_users.value.addAll(devapiService.getRegUser(device.id))
        }
    }

    AnimatedVisibility(
        visible = deviceScreenState.value==3,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Device Logs",
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                )
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(logs.value) {
                    Column(Modifier.fillMaxWidth(0.85f)) {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Text(
                                getLogName(it, R_users),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Row(Modifier.fillMaxWidth().padding(vertical = 5.dp)) {
                                Text(
                                    it.date,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 15.sp,
                                    color = primary
                                )
                                Spacer(Modifier.width(20.dp))
                                Text(
                                    it.time,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 15.sp,
                                    color = primary
                                )
                            }

                        }
                        Divider(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(disabled)
                                .padding(top = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

fun getLogName(log: GetLogs, users: State<SnapshotStateList<GetRegUser>>) : String{
    var name = ""

    users.value.forEach {
        if(it.id == log.userId.toLong()){
            name = it.first_name + " " + it.last_name
        }
    }

    return name
}
