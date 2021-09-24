package com.shaadi.demo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.shaadi.demo.R


private val Sac = FontFamily(
    Font(R.font.sacramento_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Sac,
        fontWeight = FontWeight.W600,
        fontSize = 50.sp,
        textAlign = TextAlign.Center
    ),
    h2 = TextStyle(
        fontFamily = Sac,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    ),
    h3 = TextStyle(
        fontFamily = Sac,
        fontWeight = FontWeight.W400,
        fontSize = 26.sp,
        textAlign = TextAlign.Center
    ),
)