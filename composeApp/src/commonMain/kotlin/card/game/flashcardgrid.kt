package card.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FlashcardGrid(flashcards: List<Flashcard>, onCardClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       /* val chunkedFlashcards = flashcards.chunked(2)// ha egymás mellé szeretnénk őket rakni
        chunkedFlashcards.forEach { rowFlashcards ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                rowFlashcards.forEach { flashcard ->
                    FlashcardItem(flashcard = flashcard, onClick = { onCardClicked(flashcard.id) })
                }
            }
        }*/

        val scrollState  = rememberScrollState()
        Column(Modifier.fillMaxHeight()) { //sima oszlop
            flashcards.forEach { flashcard ->
                Box(Modifier.weight(1f).fillMaxWidth(),contentAlignment = Alignment.Center){
                    FlashcardItem(flashcard = flashcard, onClick = { onCardClicked(flashcard.id) })
                }

            }
        }



    }
}
