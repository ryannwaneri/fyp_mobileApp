package com.example.finalyearproject.ui.theme

import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.finalyearproject.R

val bhalooBhai = FontFamily(
    Font(R.font.baloobhai_regular),
    Font(R.font.baloobhai_bold, FontWeight.Bold),
    Font(R.font.baloobhai_extrabold, FontWeight.ExtraBold),
    Font(R.font.baloobhai_medium, FontWeight.Medium),
    Font(R.font.baloobhai_semibold, FontWeight.SemiBold)
)

val bhalooBhaijan = FontFamily(
    Font(R.font.bhaloobhaijan_regular),
    Font(R.font.bhaloobhaijan_bold, FontWeight.Bold),
    Font(R.font.bhaloobhaijan_extrabold, FontWeight.ExtraBold),
    Font(R.font.bhaloobhaijan_medium, FontWeight.Medium),
    Font(R.font.baloobhaijaan_semibold, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = bhalooBhaijan,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp
    ),
    button = TextStyle(
        fontFamily = bhalooBhai,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    caption = TextStyle(
        fontFamily = bhalooBhai,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = bhalooBhai,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    h2 = TextStyle(
        fontFamily = bhalooBhai,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)