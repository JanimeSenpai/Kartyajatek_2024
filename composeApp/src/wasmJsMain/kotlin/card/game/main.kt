package card.game

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val orientation : MutableState<String> = remember { mutableStateOf("undefined") }
        val density = LocalDensity.current
        val windowWidthPx = window.innerWidth
        val widthDp = with(density) { windowWidthPx.toDp() }
        val w = remember { mutableStateOf(widthDp) }
        BoxWithConstraints {
            KartyaJatekCommonTheme {
            App(orientation = orientation.value)
            w.value = maxWidth
            val h = remember { mutableStateOf(maxHeight) }
            LaunchedEffect(w.value){
                orientation .value=  if (w .value< h.value) {//ha keskenyebb mint amilyen magas
                    "portrait"
                } else {
                    "landscape"
                }
            }
            //   println("orientation (box)= ${orientation.value}")
        }
    }
    }
}