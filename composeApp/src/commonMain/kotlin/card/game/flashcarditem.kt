package card.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun FlashcardItem(
    flashcard: Flashcard,
    onClick: () -> Unit,
    width: Float = 0.95f,
    imageDrawable: DrawableResource,) {
    // Animations for the card flip and text rotation.
    val rotation by animateFloatAsState(
        targetValue = if (flashcard.isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )
    val textRotation by animateFloatAsState(
        targetValue = if (flashcard.isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight(0.96f)
            .fillMaxWidth(width)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
            .background(flashcard.backgroundColor, shape = RoundedCornerShape(20.dp))
            .clickable { onClick() },
        // By default the Box content is centered.
        contentAlignment = Alignment.Center
    ) {
        // AnimatedVisibility to show/hide the image when the card is not flipped.
        AnimatedVisibility(
            visible = !flashcard.isFlipped && imageDrawable != null,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(
                painter = painterResource( imageDrawable),
                contentDescription = "Flashcard front image",
                modifier = Modifier
                    .size(100.dp).padding(4.dp)
                    .graphicsLayer {
                        // This will ensure the image follows the card's rotation.
                        rotationY = rotation
                        cameraDistance = 12 * density
                    }
            )
        }

        // The text remains centered in the card.
        Text(
            text = flashcard.displayedText,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .graphicsLayer {
                    rotationY = textRotation
                    cameraDistance = 12 * density
                },
            color = Color.Black
        )
    }
}
