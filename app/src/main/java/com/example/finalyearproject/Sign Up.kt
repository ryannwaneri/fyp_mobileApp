package com.example.finalyearproject

import Security.hash
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import ch.qos.logback.core.spi.LifeCycle
import com.example.finalyearproject.data.remote.dto.owner.OwnerService
import com.example.finalyearproject.data.remote.dto.owner.SignUpRequest
import com.example.finalyearproject.destinations.loginScreenDestination
//import com.example.finalyearproject.destinations.loginScreenDestination
import com.example.finalyearproject.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//future ryan i appologise for putting this one here it shuld be in a file of its own
@Composable
fun enterButton(text: String, modifier: Modifier){
    Card(modifier , backgroundColor = lightPrimary){
        Row(modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text + " ",
                textAlign = TextAlign.Center,
                color = Color.Black,
                style = Typography.button
            )
            Icon(
                painterResource(id = R.drawable.front),
                "go",
                tint = Color.Black
            )
        }
    }
}

@Destination(start=true)
@Composable
fun signUpScreen(navigator:DestinationsNavigator) {

    var service = OwnerService.create()
    var status by remember { mutableStateOf("") }

    var alertState by remember { mutableStateOf(false) }
    var alertColor by remember { mutableStateOf(primary) }

    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var number by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        AnimatedVisibility(
            visible = alertState,
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
                text = "Sign Up",
                color = Color.Black,
                modifier = Modifier.padding(top = 25.dp, bottom = 20.dp, start = 25.dp),
                style = Typography.h1
            )
        }

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //firstName

                TextField(
                    value = firstName,
                    onValueChange = { value -> firstName = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    placeholder = {
                        Text(
                            text = "First Name",
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
                            painter = painterResource(id = R.drawable.name),
                            contentDescription = "first name",
                            tint = primary
                        )
                    },
                    textStyle = Typography.h2
                )


                //LastName

                TextField(
                    value = lastName,
                    onValueChange = { value -> lastName = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    placeholder = {
                        Text(
                            text = "Last Name",
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
                            painter = painterResource(id = R.drawable.name),
                            contentDescription = "last name",
                            tint = primary
                        )
                    },
                    textStyle = Typography.h2
                )


                //email

                TextField(
                    value = email,
                    onValueChange = { value -> email = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
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


                //number

                TextField(
                    value = number,
                    onValueChange = { value -> number = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    placeholder = {
                        Text(
                            text = "Phone Number",
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
                            painter = painterResource(id = R.drawable.number),
                            contentDescription = "number",
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
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    placeholder = {
                        Text(
                            text = "Password",
                            Modifier.padding(start = 0.dp),
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

        val effect = remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = effect.value){
            if(effect.value == true) {
                navigator.navigate(
                    loginScreenDestination
                )
            }
        }

        //Spacer(modifier=Modifier.weight(1f))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            enterButton(text = "Sign Up",
                modifier = Modifier
                    .padding(end = 20.dp, bottom = 20.dp, top = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        val owner = SignUpRequest(
                            email.text,
                            password.text.hash(),
                            firstName.text,
                            lastName.text,
                            number.text
                        )
                        GlobalScope.launch {
                            if (service.createOwner(owner)?.id == -1L) {
                                status = "Email already exists"
                                alertColor = errorColor
                                alertState = true
                                delay(3000)
                                alertState = false
                            } else {
                                status = "Success proceed to login"
                                alertColor = Teal200
                                alertState = true
                                delay(3000)
                                alertState = false
                                effect.value = !effect.value
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
                "Already have an account? ",
                style = Typography.subtitle1,
                color = disabledText
            )
            Text(
                "Login",
                style = Typography.subtitle1,
                color = primary,
                modifier = Modifier.clickable {
                    navigator.navigate(
                        loginScreenDestination
                    )
                }
            )
        }
    }
}
