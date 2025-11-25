package com.neonscan.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonAppleDim
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.ui.theme.NeonGray
import com.neonscan.ui.theme.NeonSurface
import com.neonscan.ui.theme.NeonWhite

private val NeonColors: ColorScheme = darkColorScheme(
    primary = NeonApple,
    onPrimary = NeonBlack,
    secondary = NeonApple,
    onSecondary = NeonBlack,
    background = NeonBlack,
    onBackground = NeonWhite,
    surface = NeonSurface,
    onSurface = NeonWhite,
    surfaceVariant = NeonSurface.copy(alpha = 0.85f),
    onSurfaceVariant = NeonGray,
    outline = NeonAppleDim
)

@Composable
fun NeonTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = NeonColors,
        typography = Typography,
        content = content
    )
}
