package card.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import card.game.bekerdezokerdesek.QuestionsPage

// Main Screen that holds the main content and bottom navigation bar
@Composable
fun MainScreen(orientation: String) {
    // Instead of using dependency injection, we simply create the ViewModel here as a sample.
    val viewModel: FlashcardViewModel = remember { FlashcardViewModel() }
    val uiState by viewModel.uiState.collectAsState()
    val color = when (uiState.currentGame) {
        GameMode.Game2 -> MaterialTheme.colorScheme.primaryContainer
        GameMode.Info, GameMode.Game1 -> MaterialTheme.colorScheme.background
    }    // Use Scaffold to add a bottomBar.
    Scaffold(
        bottomBar = {
            MyBottomNavigationBar(
                currentGame = uiState.currentGame,
                onGameSelected = { gameMode -> viewModel.selectGame(gameMode) }
            )
        },
        containerColor = color,
    ) {
        innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                  //  .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
              //  Spacer(modifier = Modifier.height(10.dp))
                // Language Toggle remains at the top


              /*  LanguageToggle(
                    lang = uiState.lang,
                    onToggleLanguage = { viewModel.toggleLanguage() }
                )*/


               // Spacer(modifier = Modifier.height(10.dp))

                // Display content based on the selected game mode.
                when (uiState.currentGame) {
                    GameMode.Game1 -> {
                        FlashcardGrid(
                            flashcards = uiState.flashcards,
                            onCardClicked = { viewModel.flipCard(it) },
                            orientation = orientation
                        )
                    }
                    GameMode.Info -> {
                        // Display an informational screen content, such as InfoScreen()
                    }
                    GameMode.Game2 -> {
                        QuestionsPage()
                    }
                }
            }
        }
    }
}