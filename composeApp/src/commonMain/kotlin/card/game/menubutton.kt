package card.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MenuButton(onMenuItemSelected: (GameMode) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Icon(
            Icons.Default.Menu,
            modifier = Modifier
                .clickable { expanded = true },
            contentDescription = "Menu"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onMenuItemSelected(GameMode.Info)
            }, content = { Text("Info") })
            DropdownMenuItem(onClick = {
                expanded = false
                onMenuItemSelected(GameMode.Game1)
            }, content = { Text("Game1") })
            DropdownMenuItem(onClick = {
                expanded = false
                onMenuItemSelected(GameMode.Game2)
            }, content = { Text("Game2") })
            DropdownMenuItem(onClick = {
                expanded = false
                onMenuItemSelected(GameMode.Game3)
            }, content = { Text("Game3") })
        }
    }
}
