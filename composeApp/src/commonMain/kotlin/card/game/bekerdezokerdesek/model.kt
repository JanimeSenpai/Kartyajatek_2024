package card.game.bekerdezokerdesek

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuestionsViewModel(
    private var questions: List<String>
) : ViewModel() {

    private val _remainingQuestions = MutableStateFlow(questions.toMutableList())
    val remainingQuestions: StateFlow<List<String>> = _remainingQuestions.asStateFlow()

    private val _askedQuestions = MutableStateFlow(mutableListOf<String>())
    val askedQuestions: StateFlow<List<String>> = _askedQuestions.asStateFlow()

    private val _currentQuestion = MutableStateFlow("")
    val currentQuestion: StateFlow<String> = _currentQuestion.asStateFlow()

    private val _endOfQuestionList = MutableStateFlow(false)
    val endOfQuestionList: StateFlow<Boolean> = _endOfQuestionList.asStateFlow()

    private val _bgColor = MutableStateFlow( Color(0xFFFFFFFF))
    val bgColor: StateFlow<Color> = _bgColor.asStateFlow()

    val cardcolors = listOf(
      Color(0xFFFFFFFF),
        Color(0xFF000000),
        Color(0xFF6EE679),
        Color(0xFFFFD11D),
        Color(0xFF5F4BB6),
        Color(0xFFFF6978),
        Color(0xFF6E8898),
        Color(0xFF266DD3),
    )



    fun onBackClick() {
        if (_askedQuestions.value.isNotEmpty()) {
            // Add the current question back to the remaining questions
            _remainingQuestions.value = _remainingQuestions.value.toMutableList().apply {
                add(0, _currentQuestion.value)
            }
            // Get the last asked question and set it as the current question
            val lastAskedQuestion = _askedQuestions.value.removeLast()
            _currentQuestion.value = lastAskedQuestion
        }

    }

    fun onNextClick(isRandom: Boolean, isLoopEnabled: Boolean){
        if (_remainingQuestions.value.isNotEmpty()) {
            // Add the current question to the asked questions
            if (_currentQuestion.value.isNotEmpty()) {
                _askedQuestions.value = _askedQuestions.value.toMutableList().apply {
                    add(_currentQuestion.value)
                }
            }

            // Get the next question
            val nextIndex = if (isRandom) {
                _remainingQuestions.value.indices.random()
            } else {
                0 // Always take the first question in non-random mode
            }

            val nextQuestion = _remainingQuestions.value[nextIndex]
            _remainingQuestions.value = _remainingQuestions.value.toMutableList().apply {
                removeAt(nextIndex)
            }

            // Update the current question
            _currentQuestion.value = nextQuestion
            _bgColor.value=cardcolors.random()


        } else {
            if (isLoopEnabled) {
                resetForLooping(preserveLastQuestion = true)
                if (_remainingQuestions.value.isNotEmpty()) {
                    onNextClick(isRandom, isLoopEnabled)
                }
            } else {
                _endOfQuestionList.value = true
                // return "We reached the end of the question list" //todo handle separately
            }
        }
    }


    /* fun getNextQuestion(isRandom: Boolean, isLoopEnabled: Boolean): String {//deprecated
         if (_remainingQuestions.value.isNotEmpty()) {
             val nextIndex = if (isRandom) {
                 _remainingQuestions.value.indices.random()
             } else {
                 _currentQuestionIndex.value
             }

             val nextQuestion = _remainingQuestions.value[nextIndex]
             _remainingQuestions.value = _remainingQuestions.value.toMutableList().apply { removeAt(nextIndex) }
             _askedQuestions.value = _askedQuestions.value.toMutableList().apply { add(nextQuestion) }

             _currentQuestionIndex.value = nextIndex

             return nextQuestion
         } else {
             if (isLoopEnabled) {
                 resetForLooping(false)//deprecated
                 return if (_remainingQuestions.value.isNotEmpty()) {
                     getNextQuestion(isRandom, isLoopEnabled)
                 } else {
                     "No questions available after reset"
                 }
             } else {
                 _endOfQuestionList.value = true
                 return "We reached the end of the question list"
             }
         }
     }*/

    fun importQuestions(newQuestions: List<String>) {
        questions  = newQuestions.toMutableList()
        resetForLooping(preserveLastQuestion = false)
    }
    fun resetForLooping(preserveLastQuestion: Boolean) {
        _remainingQuestions.value = questions.shuffled().toMutableList()
        if(!preserveLastQuestion) _askedQuestions.value.clear()
        _endOfQuestionList.value = false
    }
}