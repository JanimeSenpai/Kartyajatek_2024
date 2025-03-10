package card.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kartyajatek_2024.composeapp.generated.resources.Res
import kartyajatek_2024.composeapp.generated.resources.delete
import kartyajatek_2024.composeapp.generated.resources.hands
import kartyajatek_2024.composeapp.generated.resources.hourglass
import kartyajatek_2024.composeapp.generated.resources.people
import kartyajatek_2024.composeapp.generated.resources.pie_chart
import kartyajatek_2024.composeapp.generated.resources.placeholder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashcardViewModel : ViewModel() {

    private val defaultTexts = listOf("A", "B", "C", "D", "E", "F")
    private val colors = listOf(
        Color(0xFFB4DADA), // Light teal
        Color(0xFFECD8C5), // Soft peach
        Color(0xFFCDD2F6), // Light lavender
        Color(0xFFF8E38F), // Soft yellow
        Color(0xFFC5E1A5), // Light green
        Color(0xFFF5C6CB)  // Soft pink
    )

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()


    val englishDimensions = listOf("Feeling", "Taboo", "Person", "Place", "Lifecircle", "Time")
    val hungarianDimensions = listOf("Érzés", "Tabu", "Személy", "Helyszín", "Életkör", "Idő")
    val images =listOf(Res.drawable.hands,Res.drawable.delete,Res.drawable.people,Res.drawable.placeholder,Res.drawable.pie_chart,Res.drawable.hourglass)

    private fun initializeFlashcards() {
        val flashcards = defaultTexts.mapIndexed { index, defaultText ->
            Flashcard(
                id = index,
                defaultText = when (uiState.value.lang) {
                    "hun" -> hungarianDimensions[index]
                    "eng" -> englishDimensions[index]
                    else -> throw IllegalArgumentException(
                        "Unknown language: ${
                            uiState.value.lang
                        }"
                    )
                },
                contentHun = getHungarianContentForCard(index),
                contentEng = getEnglishContentForCard(index),
                displayedText = when (uiState.value.lang) {
                    "hun" -> hungarianDimensions[index]
                    "eng" -> englishDimensions[index]
                    else -> throw IllegalArgumentException(
                        "Unknown language: ${
                            uiState.value.lang
                        }"
                    )
                },
                backgroundColor = colors.getOrElse(index) { Color.Gray },
                imageResource = images[index]
            )
        }
        _uiState.value = _uiState.value.copy(flashcards = flashcards)
    }

    init {
        initializeFlashcards()
    }

    private fun getHungarianContentForCard(index: Int): List<String> {
        val languageArraysHun = listOf(
            /*érzés*/        listOf(
                "Meglepődés Riadt Döbbent",
                "Meglepődés Riadt Megrémült",
                "Meglepődés Zavarodott Kiábrándult",
                "Meglepődés Zavarodott Meghökkent",
                "Meglepődés Megdöbbent Elképedt",
                "Meglepődés Megdöbbent Félelemmel vegyes bámulatba ejtett",
                "Meglepődés Izgatott Buzgó",
                "Meglepődés Izgatott Energikus",
                "Boldogság Érdeklődő Kíváncsi",
                "Boldogság Érdeklődő Derűs",
                "Boldogság Büszke Fontos",
                "Boldogság Büszke Magabiztos",
                "Boldogság Békés Szeretetteljes",
                "Boldogság Békés Reményteli",
                "Boldogság Optimista Nyitott",
                "Boldogság Optimista Inspirált",
                "Szomorúság Unott Érdektelen",
                "Szomorúság Unott Közönyös",
                "Szomorúság Lehangolt Kiüresedett",
                "Szomorúság Lehangolt Alsóbbrendű",
                "Szomorúság Elhagyatott Bántalmazott",
                "Szomorúság Elhagyatott Mellőzött",
                "Szomorúság Bűnös Megszégyenült",
                "Szomorúság Bűnös Bűnbánó",
                "Undor Elkerülő Határozatlan",
                "Undor Elkerülő Idegenkedés",
                "Undor Irtózatos Utálatos",
                "Undor Irtózatos Ellenérzés",
                "Undor Csalódott Fellázadt",
                "Undor Csalódott Visszataszító",
                "Undor Helytelenítő Megvető",
                "Undor Helytelenítő Ítélkező",
                "Harag Távolságtartó Gyanakvó",
                "Harag Távolságtartó Zárkózott",
                "Harag Frusztrált Ingerlékeny",
                "Harag Frusztrált Feldühödött",
                "Harag Agresszív Ellenséges",
                "Harag Agresszív Provokált",
                "Harag Sértett Letört",
                "Harag Sértett Zavart",
                "Félelem Visszautasított Elidegenedett",
                "Félelem Visszautasított Alkalmatlan",
                "Félelem Engedelmes Jelentéktelen",
                "Félelem Engedelmes Értéktelen",
                "Félelem Bizonytalan Alsóbbrendű",
                "Félelem Bizonytalan Alkalmatlan",
                "Félelem Szorongó Aggódó",
                "Félelem Szorongó Túlterhelt"
            ).map { it.replace(" ", ">>") },
            /*tabu*/ listOf(
                "Szexuális orientáltság",
                "Erőszak",
                "Szülők által tiltott dolgok",
                "Saját tested",
                "Nagyszülőkkel való kapcsolat",
                "Intimitás",
                "Rang/Státusz",
                "Ürítés",
                "Irigység",
                "Társadalmi különbség",
                "Családon belüli erőszak",
                "Megcsalás",
                "Függőség",
                "Ex párunk",
                "Szexualitás",
                "Szülőkkel való kapcsolat",
                "Testvérrel való kapcsolat",
                "Gender",
                "Félelmek, traumák",
                "Politika",
                "Barátaink, akikre számíthatunk",
                "Intelligencia különbség",
                "Halál, betegség",
                "Pénz",
                "Férfiasság / Nőiesség",
                "Önértékelés, önbizalom",
                "Vallás",
                "Tisztálkodás",
                "Gonoszság",
                "Szellentés, böfögés, orrtúrás, fültúrás",
                "Népek gyűlölete, fajok gyűlölete",
                "Gyávaság",
                "Önsajnáltatás",
                "Sodródás",
                "Megbánás",
                "Bűntudat",
                "Áldozatnak lenni",
                "Szívességet kérni",
                "Elhidegülés",
                "Örömszerzés",
                "Gyengeség",
                "Kezdeményezés",
                "Hazugság",
                "Megromlott kapcsolatok",
                "Szorongás",
                "Fekete humor",
                "Szeretetnyelv",
                "Ami zavar",
                "Megjátszás",
                "Stressz",
                "Menekülés",
                "Tartás",
                "Felnézni önmagunkra",
                "Önbecsapás",
                "Kémia",
                "Kihasználás",
                "Tagadás",
                "Magabiztosság",
                "Leblokkolás",
                "Hitelesség",
                "Felelősséghárítás",
                "Hibák",
                "Viták / Veszekedés / Játszmák",
                "Időrablók",
                "Önszabotázs",
                "Ki nem mondott szavak",
                "Ahogy mások látnak",
                "Kudarc",
                "Panaszkodás",
                "Megfelelés",
                "Magány",
                "NEM-et mondani",
                "Kisugárzás",
                "Szembenézés",
                "Nevelés",
                "Mentális egészség",
                "Szenvedés",
                "Jövőkép",
                "Bizonyítás",
                "Önmagvalósítás"
            ),
            /*személy*/ listOf(
                "Testvérek",
                "Nagyszülők",
                "Tanárok",
                "Első szerelmek",
                "Edzők",
                "Édesapa",
                "Édesanya",
                "Főnök",
                "Barátok",          // New element
                "Kollégák",         // New element
                "Szomszédok",       // New element
                "Unokatestvérek",   // New element
                "Gyerekek",         // New element
                "Osztálytársak",    // New element
                "Mentorok",         // New element
                "Pszichológus",     // New element
                "Orvosok",          // New element
                "Üzleti partnerek", // New element
                "Lakótársak",       // New element
                "Rokonok"           // New element
            ),
            /*helyszín*/
            listOf(
                "Szülőváros",
                "Lakóhely",
                "Óvoda",
                "Általános iskola",
                "Középiskola",
                "Egyetem",
                "Edzőtábor",
                "Nyaralás",
                "Külföld",
                "Múzeum",          // New element
                "Könyvtár",        // New element
                "Színház",         // New element
                "Mozi",            // New element
                "Park",            // New element
                "Bevásárlóközpont",// New element
                "Kávézó",          // New element
                "Étterem",         // New element
                "Kórház",          // New element
                "Repülőtér",       // New element
                "Vasútállomás",    // New element
                "Tengerpart",      // New element
                "Hegyek"           // New element
            ),
            /*életkör*/ listOf(
                  "Egyetem, munka, karrier",
            "Párkapcsolat",
            "Egészséges életmód",
            "Barátok",
            "Család",
            "Szabadidő, hobbi",
            "Életkörülmények",
            "Én"
        ),
            /*idő*/listOf(
                "Születésnapomkor",
                "26-30 éves kor",
                "0-3 éves kor",
                "Elmúlt 1 hónap",
                "Munka közben",
                "Hétvégéken, szabadidő alatt",
                "Amikor munkából hazaérsz",
                "Éjjelente",
                "Elmúlt 1 év",
                "Karácsonykor, ünnepek ideje alatt",
                "3-6 éves kor",
                "Amikor felkelsz reggel",
                "Amikor mások, a barátaid sikereit látod a LinkedIn-en",
                "Amikor a szüleid leszidtak",
                "Amikor a barátaidat látod közösen sztorit feltölteni a buliban, ahova nem hívtak",
                "Most, ebben a pillanatban",
                "12-18 éves kor",
                "Amikor a barátaiddal vagy",
                "Gyerekkor",
                "Tegnap",
                "Elmúlt 1 hét",
                "22-26 éves kor",
                "Elmúlt 5 év"
            )
        )
        return languageArraysHun.getOrElse(index) { emptyList() }
    }

    private fun getEnglishContentForCard(index: Int): List<String> {
        val languageArraysEng = listOf(
            listOf("apple", "pear", "orange", "peach", "lemon", "pineapple"),
            listOf(
                "dog",
                "cat",
                "mouse",
                "horse",
                "donkey",
                "elephant",
                "giraffe",
                "lion",
                "tiger",
                "monkey"
            ),
            listOf(
                "maths",
                "history",
                "language",
                "literature",
                "art",
                "physical education",
                "geography",
                "singing",
                "foreign language",
                "chemistry",
                "physics",
                "biology"
            ),
            listOf(
                "table",
                "chair",
                "bed",
                "sofa",
                "armchair",
                "towel",
                "duvet",
                "pillow",
                "blanket"
            )
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

        val nextlanguage = nextlanguage()
        var currentState = _uiState.value
        _uiState.value = currentState.copy(
            lang = nextlanguage,
        )
        currentState = _uiState.value
        // Reset flipped state and displayed text when language changes
        val updatedFlashcards = currentState.flashcards.mapIndexed { index, flashcard ->
            val text = when (uiState.value.lang) {
                "hun" -> hungarianDimensions[index]
                "eng" -> englishDimensions[index]
                else -> throw IllegalArgumentException(
                    "Unknown language: ${
                        uiState.value.lang
                    }"
                )
            }

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
        return when (uiState.value.lang) {
            "hun" -> "eng"
            "eng" -> "hun"
            // Add more cases for other languages if needed
            else -> throw IllegalArgumentException(
                "Unknown language: ${
                    uiState.value.lang
                }"
            )
        }
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
