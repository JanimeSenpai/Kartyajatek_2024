package card.game

import androidx.compose.foundation.isSystemInDarkTheme

import androidx.compose.runtime.Composable
import androidx.compose.material3.*


val commoncolorscheme = darkColorScheme()




//val typography = MaterialTheme.typography.copy()


@Composable
fun KartyaJatekCommonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = commoncolorscheme,
        content = content,
      //  typography =
    )

}