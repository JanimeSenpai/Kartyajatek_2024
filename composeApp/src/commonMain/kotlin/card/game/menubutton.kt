package card.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
            }, text = { Text("Info") })
            DropdownMenuItem(onClick = {
                expanded = false
                onMenuItemSelected(GameMode.Game1)
            }, text = { Text("Game1") })
            DropdownMenuItem(onClick = {
                expanded = false
                onMenuItemSelected(GameMode.Game2)
            }, text = { Text("Bekérdező kártyák") })//translations
           /* DropdownMenuItem(onClick = {
                expanded = false
                onMenuItemSelected(GameMode.Game3)
            }, text = { Text("Game3") })*/
        }
    }
}



