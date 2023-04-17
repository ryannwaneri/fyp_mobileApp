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
import com.example.finalyearproject.destinations.loginScreenDestination
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
        AnimatedVisibility(visible = alertState,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(3000))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(alertColor),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    status,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = Typography.caption
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Text(
                text = "Login",
                color = Color.Black,
                modifier = Modifier.padding(top = 25.dp, bottom = 20.dp, start = 25.dp),
                style = Typography.h1
            )
        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Column(
                Modifier.fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //email
                TextField(
                    value = email,
                    onValueChange = { value -> email = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    placeholder = {
                        Text(
                            text = "Email",
                            Modifier.padding(start = 0.dp),
                            style = Typography.caption
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = primary,
                        backgroundColor = disabled,
                        disabledTextColor = disabledText,
                        disabledPlaceholderColor = disabledText,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "email",
                            tint = primary
                        )
                    },
                    textStyle = Typography.h2
                )


                //password
                TextField(
                    value = password,
                    onValueChange = { value -> password = value },
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Password",
                            style = Typography.caption
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = primary,
                        backgroundColor = disabled,
                        disabledTextColor = disabledText,
                        disabledPlaceholderColor = disabledText,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.password),
                            contentDescription = "password",
                            tint = primary
                        )
                    },
                    textStyle = Typography.h2
                )
            }
        }

        Box(Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 10.dp, end = 16.dp), contentAlignment = Alignment.CenterEnd) {
            enterButton(text = "Login ",
                modifier = Modifier
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

        Row(
            Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                "Do not have an account? ",
                style = Typography.subtitle1,
                color = disabledText
            )
            Text(
                "Sign Up",
                style = Typography.subtitle1,
                color = primary,
                modifier = Modifier.clickable {
                    navigator.navigate(
                        signUpScreenDestination
                    )
                }
            )
        }
    }

}