package card.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguageToggle(lang: String, onToggleLanguage: () -> Unit) {
    Text(
        text = when(lang) {
            "hun" -> "HUN"
            "eng" -> "ENG"
            else -> throw IllegalArgumentException("Unknown language: $lang")
        },
        fontSize = 18.sp,
        modifier = Modifier
            .padding(bottom = 10.dp)
            .clickable { onToggleLanguage() }
    )
}
