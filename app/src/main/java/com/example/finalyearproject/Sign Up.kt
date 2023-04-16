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
    Card(modifier , backgroundColor = primary){
        Text(text, fontSize = 24.sp, textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(vertical = 5.dp))
    }
}

@Destination(start=true)
@Composable
fun signUpScreen(navigator:DestinationsNavigator){

    var service = OwnerService.create()
    var status by remember{mutableStateOf("")}

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
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(
                text = "Login",
                color = Color.Red,
                modifier = Modifier
                    .padding(end = 16.dp, top = 16.dp)
                    .clickable {
                        navigator.navigate(
                            loginScreenDestination
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
                text = "Sign Up",
                color = Color.Black,
                modifier = Modifier.padding(top = 45.dp, bottom = 40.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = true)
        ) {
            //firstName
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.name),
                    contentDescription = "first name",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    tint = firtNameColourState.value
                )
                TextField(
                    value = firstName,
                    onValueChange = { value -> firstName = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    placeholder = { Text(text = "First Name", Modifier.padding(start = 0.dp)) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Red,
                        backgroundColor = Color.Transparent,
                        disabledTextColor = disabled,
                        disabledPlaceholderColor = disabled
                    )
                )
            }

            //LastName
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.name),
                    contentDescription = "last name",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    tint = firtNameColourState.value
                )
                TextField(
                    value = lastName,
                    onValueChange = { value -> lastName = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    placeholder = { Text(text = "Last Name", Modifier.padding(start = 0.dp)) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Red,
                        backgroundColor = Color.Transparent,
                        disabledTextColor = disabled,
                        disabledPlaceholderColor = disabled
                    )
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

            //number
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.number),
                    contentDescription = "number",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    tint = firtNameColourState.value
                )
                TextField(
                    value = number,
                    onValueChange = { value -> number = value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    placeholder = { Text(text = "Phone Number", Modifier.padding(start = 0.dp)) },
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
        }

        //Spacer(modifier=Modifier.weight(1f))

        enterButton(text = "Sign Up",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    val owner = SignUpRequest(
                        email.text,
                        password.text.hash(),
                        firstName.text,
                        lastName.text,
                        number.text
                    )
                    GlobalScope.launch{
                    if (service.createOwner(owner)?.id == -1L) {
                            status = "Email already exists"
                            alertColor = Purple700
                            alertState = true
                            delay(3000)
                            alertState = false
                    } else {
                        status = "Success proceed to login"
                        alertColor = Teal200
                        alertState = true
                        delay(3000)
                        alertState = false
                    }

                    }
                }
        )
    }
}
