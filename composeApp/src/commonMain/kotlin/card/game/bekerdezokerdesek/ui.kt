package card.game.bekerdezokerdesek

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import card.game.getPlatform
import kartyajatek_2024.composeapp.generated.resources.Res
import kartyajatek_2024.composeapp.generated.resources.endoflist
import org.jetbrains.compose.resources.getStringArray
import org.jetbrains.compose.resources.stringResource

@Composable
fun QuestionsPage(
    audience: String="null",audienceID: Int=0, isRandom: Boolean=false, isLoopEnabled: Boolean=false,

) {
    val restartTrigger = remember { mutableStateOf(0) }


    var questions = remember { mutableStateOf<List<String>>(emptyList()) }
    var viewModel: QuestionsViewModel = remember { QuestionsViewModel(questions.value) }


    val currentQuestion by viewModel.currentQuestion.collectAsState()
    val isEndOfList by viewModel.endOfQuestionList.collectAsState()
    LaunchedEffect(audience, restartTrigger) {
        questions.value = getQuestionsForAudience(audienceID)
        viewModel.importQuestions(questions.value)
        viewModel.onNextClick(isRandom, isLoopEnabled)
    }

    LaunchedEffect(restartTrigger.value) {
        println("restartTrigger=$restartTrigger")
        if (restartTrigger.value > 0) {
            viewModel.resetForLooping(false)
            viewModel.onNextClick(isRandom, isLoopEnabled)
        }
        }


    var platform = getPlatform().name
    println("platform=$platform")
    Scaffold(
        floatingActionButton = {
            if (isEndOfList|| "Android" !in platform /*platform=="Web with Kotlin/Wasm"*/) {
                FloatingActionButton(
                    onClick = { restartTrigger.value++ },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to Main")
                }
            }
        },
        containerColor =    MaterialTheme.colorScheme.primaryContainer
        ,
        floatingActionButtonPosition = FabPosition.Center, // Position the FAB at the center bottom
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                  //  .padding(paddingValues)
                   // .padding(horizontal = 16.dp, vertical = 32.dp),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween // A gyermekek közötti helyek elosztása
                    ) {
                        // A felső rész kitölt egy üres területet, de a tartalom a közepén lesz elhelyezve
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f), // Ez a Box a maradék teret kitölti
                            contentAlignment = Alignment.Center // A tartalom itt középre kerül
                        ) {
                            Text(
                                text = if (isEndOfList) {
                                    stringResource(Res.string.endoflist)
                                } else currentQuestion,
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }

                        // Az alsó rész a QuestionControls komponenst tartalmazza, amely így a képernyő alján helyezkedik el
                        QuestionControls(
                            onBackClick = { viewModel.onBackClick() },
                            onNextClick = { viewModel.onNextClick(isRandom, isLoopEnabled) },
                            currentQuestion = currentQuestion,
                            isEndOfList = isEndOfList
                        )
                    }
                }


            }
        }
    )
}

@Composable
fun QuestionControls(
    onBackClick: () -> Unit, onNextClick: () -> Unit, currentQuestion: String, isEndOfList: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Button
        //a másik beszélgetős játékomban van vissza gomb, de itt kivesszük
       /* AnimatedVisibility(!isEndOfList) {//amúgy az lenne a dolga, hogy amíg nem ér véget a játék, lehet oda-vissza lépkedni
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
                )
            }
        }*/


        // Stopwatch ha lenne rá igény
        //Stopwatch(currentQuestion, isEndOfList)

        AnimatedVisibility(!isEndOfList) {
            IconButton(onClick = onNextClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next"
                )
            }
        }

    }
}


 fun getQuestionsForAudience(audienceID: Int): List<String> {
    return kerdeslista
}