package card.game

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// Composable bottom navigation bar using Material3's NavigationBar & NavigationBarItem.
@Composable
fun MyBottomNavigationBar(
    currentGame: GameMode,
    onGameSelected: (GameMode) -> Unit
) {
    // List of items for the bottom navigation
    val items = listOf(
        BottomNavItem.Info,
        BottomNavItem.Game1,
        BottomNavItem.Game2
    )

    NavigationBar {
        items.forEach { item ->
            val isSelected = (currentGame == item.gameMode)
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.iconSelected else item.iconUnselected,
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) },
                selected = isSelected,
                onClick = { onGameSelected(item.gameMode) }
            )
        }
    }
}



// Sealed class describing each bottom navigation item
sealed class BottomNavItem(
    val gameMode: GameMode,
    val iconSelected: androidx.compose.ui.graphics.vector.ImageVector,
    val iconUnselected: androidx.compose.ui.graphics.vector.ImageVector,
    val label: String
) {
    object Info : BottomNavItem(
        GameMode.Info,
        Icons.Filled.Info,
        Icons.Outlined.Info,
        "Info"
    )

    object Game1 : BottomNavItem(
        GameMode.Game1,
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder,
        "Dimenziók"
    )

    object Game2 : BottomNavItem(
        GameMode.Game2,
        Icons.Filled.Star,
        Icons.Outlined.Star,
        "Bekérdező"
    )
}
