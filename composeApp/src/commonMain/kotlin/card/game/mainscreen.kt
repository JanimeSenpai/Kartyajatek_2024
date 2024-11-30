package card.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(orientation:String) {
    val viewModel: FlashcardViewModel = remember {FlashcardViewModel()}
    val uiState by viewModel.uiState.collectAsState()
Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
){



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menu Button and Dropdown
        MenuButton(onMenuItemSelected = { viewModel.selectGame(it) })

        // Language Toggle
        LanguageToggle(
            lang = uiState.lang,
            onToggleLanguage = { viewModel.toggleLanguage() }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Display content based on the current game mode
        when (uiState.currentGame) {
//            GameMode.Info -> InfoScreen()
            GameMode.Game1 -> FlashcardGrid(flashcards = uiState.flashcards, onCardClicked = { viewModel.flipCard(it) },orientation)
           /// GameMode.Game2 -> SingleFlashcard(/* Pass appropriate data */)
           // GameMode.Game3 -> SingleFlashcard(/* Pass appropriate data */)
            GameMode.Info -> TODO()
            GameMode.Game2 -> TODO()
            GameMode.Game3 -> TODO()
        }
    }
}

}