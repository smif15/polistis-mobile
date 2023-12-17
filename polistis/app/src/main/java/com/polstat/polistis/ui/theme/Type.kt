package com.polstat.polistis.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.polstat.polistis.R

// Set of Material typography styles to start with

//val Gilroy = FontFamily(
//    Font(R.font.gilroy_regular, FontWeight.Black, FontStyle.Normal),
//    Font(R.font.gilroy_medium, FontWeight.Black, FontStyle.Normal),
//    Font(R.font.gilroy_bold, FontWeight.Black, FontStyle.Normal),
//)

val Gilroy = FontFamily(
    Font(R.font.gilroy_regular, FontWeight.Normal),
    Font(R.font.gilroy_medium, FontWeight.Medium),
    Font(R.font.gilroy_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily =  Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 10.sp
    ),

    labelSmall = TextStyle(
        fontFamily =  Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 10.sp
    ),

    bodySmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 10.sp
    ),

    titleMedium = TextStyle(
        fontFamily =  Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 10.sp
    ),

    titleSmall = TextStyle(
        fontFamily =  Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 10.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 10.sp
    ),

    labelMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 5.sp
    )
)