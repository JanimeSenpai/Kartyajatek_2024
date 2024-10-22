package card.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashcardViewModel : ViewModel() {

    private val defaultTexts = listOf("A", "B", "C", "D")
    private val colors = listOf(
        Color(0xFFB4DADA),
        Color(0xFFECD8C5),
        Color(0xFFCDD2F6),
        Color(0xFFF8E38F)
    )

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()


    val englishDimensions = listOf("Feeling","Taboo","Person","Place","Lifecircle","Time")
    val hungarianDimensions = listOf("Érzés","Tabu","Személy","Helyszín","Életkört","Idő")

    private fun initializeFlashcards() {
        val flashcards = defaultTexts.mapIndexed { index, defaultText ->
            Flashcard(
                id= index,
                defaultText = when(uiState.value.lang) {
                    "hun" -> hungarianDimensions[index]
                    "eng" -> englishDimensions[index]
                    else -> throw IllegalArgumentException("Unknown language: ${uiState.value.lang
                }")},
                contentHun = getHungarianContentForCard(index),
                contentEng = getEnglishContentForCard(index),
                displayedText = when(uiState.value.lang) {
                    "hun" -> hungarianDimensions[index]
                    "eng" -> englishDimensions[index]
                    else -> throw IllegalArgumentException("Unknown language: ${uiState.value.lang
                    }")},
                backgroundColor = colors.getOrElse(index) { Color.Gray }
            )
        }
        _uiState.value = _uiState.value.copy(flashcards = flashcards)
    }

    init {
        initializeFlashcards()
    }

    private fun getHungarianContentForCard(index: Int): List<String> {
        val languageArraysHun = listOf(
            listOf("alma", "körte", "narancs", "barack", "citrom", "ananász"),
            listOf("kutya", "macska", "egér", "ló", "szamár", "elefánt", "zsiráf", "oroszlán", "tigris", "majom"),
            listOf("matematika", "történelem", "nyelvtan", "irodalom", "rajz", "testnevelés", "földrajz", "ének", "idegen nyelv", "kémia", "fizika", "biológia"),
            listOf("asztal", "szék", "ágy", "kanapé", "fotel", "törülköző", "paplan", "párna", "takaró")
        )
        return languageArraysHun.getOrElse(index) { emptyList() }
    }

    private fun getEnglishContentForCard(index: Int): List<String> {
        val languageArraysEng = listOf(
            listOf("apple", "pear", "orange", "peach", "lemon", "pineapple"),
            listOf("dog", "cat", "mouse", "horse", "donkey", "elephant", "giraffe", "lion", "tiger", "monkey"),
            listOf("maths", "history", "language", "literature", "art", "physical education", "geography", "singing", "foreign language", "chemistry", "physics", "biology"),
            listOf("table", "chair", "bed", "sofa", "armchair", "towel", "duvet", "pillow", "blanket")
        )
        return languageArraysEng.getOrElse(index) { emptyList() }
    }

    fun flipCard(cardId: Int) {
        val currentState = _uiState.value
        val updatedFlashcards = currentState.flashcards.map { flashcard ->//updated flashcards=
            if (flashcard.id == cardId) {//ha az aktuális káryta az, amit kiválasztottunk
                val isFlipped = !flashcard.isFlipped
                val contentList = when (currentState.lang) {
                    "hun" -> flashcard.contentHun
                    "eng" -> flashcard.contentEng
                    else -> emptyList()
                }
                val displayedText = if (isFlipped && contentList.isNotEmpty()) {
                    contentList.random()
                } else {
                    flashcard.defaultText
                }
                flashcard.copy(
                    isFlipped = isFlipped,
                    displayedText = displayedText
                )
            } else {
                flashcard //különben marad addigi állapotban
            }
        }
        _uiState.value = currentState.copy(flashcards = updatedFlashcards)
    }

    fun toggleLanguage() {

        val nextlanguage =nextlanguage()
        var currentState = _uiState.value
        _uiState.value = currentState.copy(
            lang = nextlanguage,
        )
        currentState = _uiState.value
        // Reset flipped state and displayed text when language changes
        val updatedFlashcards = currentState.flashcards.mapIndexed { index, flashcard ->
            val text = when(uiState.value.lang) {
                "hun" -> hungarianDimensions[index]
                "eng" -> englishDimensions[index]
                else -> throw IllegalArgumentException("Unknown language: ${uiState.value.lang
                }")}

            flashcard.copy(
                isFlipped = false,
                displayedText = text,
                defaultText = text
            )
        }
        _uiState.value = currentState.copy(
            flashcards = updatedFlashcards
        )
    }

    fun nextlanguage(): String {
        return when( uiState.value.lang) {
            "hun" -> "eng"
            "eng" -> "hun"
            // Add more cases for other languages if needed
            else -> throw IllegalArgumentException("Unknown language: ${uiState.value.lang
        }")}
    }

    fun selectGame(gameMode: GameMode) {
        // Handle game selection logic here
        _uiState.value = _uiState.value.copy(
            currentGame = gameMode,
            // Reset flashcards if necessary
        )
    }

    // Implement additional functions for Game2 and Game3
}
