package card.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kartyajatek_2024.composeapp.generated.resources.Res
import kartyajatek_2024.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(orientation:String) {
    KartyaJatekTheme {
            MainScreen(orientation)
    }
}