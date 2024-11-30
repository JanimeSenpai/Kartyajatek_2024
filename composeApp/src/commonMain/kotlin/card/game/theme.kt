package card.game

import androidx.compose.foundation.isSystemInDarkTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.material3.*


val colorscheme = darkColorScheme()

@Composable
fun KartyaJatekTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = colorscheme,
        content = content
    )

}