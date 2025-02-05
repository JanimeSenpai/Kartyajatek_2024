package card.game

import androidx.compose.ui.graphics.Color

data class Flashcard(
    val id: Int,
    val defaultText: String,
    val contentHun: List<String>,
    val contentEng: List<String>,
    val isFlipped: Boolean = false,
    val displayedText: String = "",
    val backgroundColor: Color
)

data class UIState(
    val flashcards: List<Flashcard> = emptyList(),
    val lang: String = "hun", // hun for Hungarian, eng for English
    val currentGame: GameMode = GameMode.Game1
)

enum class GameMode {
    Info,
    Game1,
    Game2,
}
