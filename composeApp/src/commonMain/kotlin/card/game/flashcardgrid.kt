package card.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kartyajatek_2024.composeapp.generated.resources.Res
import kartyajatek_2024.composeapp.generated.resources.compose_multiplatform

@Composable
fun FlashcardGrid(flashcards: List<Flashcard>, onCardClicked: (Int) -> Unit,orientation:String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(orientation=="landscape") {
            val chunkedFlashcards = flashcards.chunked(2)// ha egymás mellé szeretnénk őket rakni
             chunkedFlashcards.forEach { rowFlashcards ->
                 Row(
                     modifier = Modifier.fillMaxWidth().weight(1f),
                     horizontalArrangement = Arrangement.Center,

                 ) {
                     rowFlashcards.forEach { flashcard ->
                         Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                             FlashcardItem(
                                 flashcard = flashcard,
                                 onClick = { onCardClicked(flashcard.id) },
                                imageDrawable =  Res.drawable.compose_multiplatform
                             )
                         }
                     }
                 }
             }
        }

        if(orientation == "portrait") {
            Column(Modifier.fillMaxHeight()) { //sima oszlop
                flashcards.forEach { flashcard ->
                    Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        FlashcardItem(
                            flashcard = flashcard,
                            onClick = { onCardClicked(flashcard.id) },
                            imageDrawable =  flashcard.imageResource
                        )
                    }

                }
            }

        }

    }
}
