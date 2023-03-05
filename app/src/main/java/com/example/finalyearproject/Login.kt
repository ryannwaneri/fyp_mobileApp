package com.example.finalyearproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.destinations.signUpScreenDestination
import com.example.finalyearproject.ui.theme.disabled
import com.example.finalyearproject.ui.theme.firtNameColourState
import com.example.finalyearproject.ui.theme.primary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun loginScreen(navigator: DestinationsNavigator){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

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
                                   signUpScreenDestination()
                               )
                    },
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "Login",
                color = Color.Black,
                modifier = Modifier.padding(top = 100.dp, bottom = 40.dp),
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
                .clickable {  }
        )
    }

}