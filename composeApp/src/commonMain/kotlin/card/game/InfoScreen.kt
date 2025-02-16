package card.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kartyajatek_2024.composeapp.generated.resources.Res
import kartyajatek_2024.composeapp.generated.resources.info
import org.jetbrains.compose.resources.stringResource

@Composable
fun infoScreen(){
    Column(
        modifier= Modifier.padding(10.dp).verticalScroll(rememberScrollState())
    ){
        Text(stringResource(Res.string.info))

    }
}