package card.game

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FlashcardItem(flashcard: Flashcard, onClick: () -> Unit,width:Float=0.9f,) {
    val rotation by animateFloatAsState(
        targetValue = if (flashcard.isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    val textrotation by animateFloatAsState(
        targetValue = if (flashcard.isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight(0.9f)
            .fillMaxWidth(width)
            //.widthIn(200.dp, 400.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
            .background(flashcard.backgroundColor, shape = RoundedCornerShape(40.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = flashcard.displayedText,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp).graphicsLayer {
                rotationY = textrotation
                cameraDistance = 12 * density
            },
            color = Color.Black
        )
    }
}
