package com.example.finalyearproject

import Security.hash
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.data.remote.dto.owner.LoginRequest
import com.example.finalyearproject.data.remote.dto.owner.LoginResponse
import com.example.finalyearproject.data.remote.dto.owner.OwnerService
import com.example.finalyearproject.data.remote.dto.owner.SignUpRequest
import com.example.finalyearproject.destinations.dashboardDestination
import com.example.finalyearproject.destinations.signUpScreenDestination
import com.example.finalyearproject.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Destination
@Composable
fun loginScreen(navigator: DestinationsNavigator){

    var service = OwnerService.create()
    var status by remember{mutableStateOf("")}
    var move by remember{ mutableStateOf(false) }

    var alertState by remember { mutableStateOf(false) }
    var alertColor by remember { mutableStateOf(primary) }

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    var stat by remember{mutableStateOf(LoginResponse("","","","","",-1L) )}

    if(move){
        navigator.navigate(
            dashboardDestination(stat)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(
                text = "Sign Up",
                color = Color.Red,
                modifier = Modifier
                    .padding(end = 16.dp, top = 16.dp)
                    .clickable {
                        navigator.navigate(
                            signUpScreenDestination
                        )
                    },
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        AnimatedVisibility(visible = alertState,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(3000))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp, horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(alertColor),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    status,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "Login",
                color = Color.Black,
                modifier = Modifier.padding(top = 45.dp, bottom = 40.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp
            )
        }

        //email
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "email",
                modifier = Modifier.padding(horizontal = 20.dp),
                tint = firtNameColourState.value
            )
            TextField(
                value = email,
                onValueChange = { value -> email = value },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                placeholder = { Text(text = "Email", Modifier.padding(start = 0.dp)) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red,
                    backgroundColor = Color.Transparent,
                    disabledTextColor = disabled,
                    disabledPlaceholderColor = disabled
                )
            )
        }

        //password
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.password),
                contentDescription = "password",
                modifier = Modifier.padding(horizontal = 20.dp),
                tint = firtNameColourState.value
            )
            TextField(
                value = password,
                onValueChange = { value -> password = value },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                placeholder = { Text(text = "Password", Modifier.padding(start = 0.dp)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Red,
                    backgroundColor = Color.Transparent,
                    disabledTextColor = disabled,
                    disabledPlaceholderColor = disabled
                )
            )
        }

        Spacer(modifier=Modifier.weight(1f))

        enterButton(text = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    val owner = LoginRequest(
                        email.text,
                        password.text.hash(),
                        firstName = "",
                        lastName = "",
                        number = "",
                        id = -1L
                    )


                    GlobalScope.launch {
                        stat = service.ownerLogin(owner)!!

                        if (stat.id != -1L) {
                            status = "Success"
                            alertColor = Teal200
                            alertState = true
                            delay(2000)
                            alertState = false
                            move = true

                        } else {
                            status = stat.email.toString()
                            alertColor = Purple700
                            alertState = true
                            delay(3000)
                            alertState = false
                        }
                    }


                }
        )
    }

}