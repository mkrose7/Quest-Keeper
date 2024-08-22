package com.example.inventory.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.inventory.R

val FairyRose = FontFamily(
    Font(R.font.fairyrose)
)

val OptimusPrinceps = FontFamily(
    Font(R.font.optimusprinceps)
)

val LiberationSerifRegular = FontFamily(
    Font(R.font.liberationserifregular)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FairyRose,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp
    ),
    displayMedium = TextStyle(
        fontFamily = OptimusPrinceps,
        fontWeight = FontWeight.Bold,
        fontSize = 29.sp
    ),
    displaySmall = TextStyle(
        fontFamily = OptimusPrinceps,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = LiberationSerifRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = OptimusPrinceps,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = OptimusPrinceps,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    ),
    labelSmall = TextStyle(
        fontFamily = LiberationSerifRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    )
)