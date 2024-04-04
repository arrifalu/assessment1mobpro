package org.d3if0105.kembalianku.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if0105.kembalianku.R
import org.d3if0105.kembalianku.ui.theme.KembaliankuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    )

    { padding ->
        ScreenContent(Modifier.padding(padding))



    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var JumlahUangTunai by rememberSaveable { mutableStateOf("")}
    var JumlahPembayaran by rememberSaveable { mutableStateOf("")}
    var Kembalian by rememberSaveable { mutableFloatStateOf(0f) }
    var kategori by rememberSaveable { mutableIntStateOf(0) }




    val radioOptions = listOf(
        stringResource(id = R.string.inputan_tidak),
        stringResource(id = R.string.sesuai_inputan)

    )
    var checked by remember { mutableStateOf(radioOptions[0])}

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(value = JumlahUangTunai,
            onValueChange = {JumlahUangTunai = it},
            label = {Text(text = stringResource(R.string.uang_tunai))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value =JumlahPembayaran,
            onValueChange = {JumlahPembayaran = it},
            label = { Text(text = stringResource(R.string.total_pembayaran))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()


        )
        Row (
            modifier = Modifier
                .padding(5.dp)

                .border(
                    1.dp, Color.Transparent, RoundedCornerShape(30.dp),

                    )


        ){
            radioOptions.forEach { text ->
                checked(
                    label = text,
                    isSelected = checked == text,
                    modifier = Modifier
                        .selectable(
                            selected = checked == text,
                            onClick = { checked = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }






        }
        Button(
            onClick = {
                      Kembalian = hitungKembalian(JumlahUangTunai.toFloat(), JumlahPembayaran.toFloat())


            },
            modifier = Modifier.padding(top = 10.dp),

            contentPadding = PaddingValues(horizontal=32.dp,
                vertical=16.dp),


            ) {
            Text(text = stringResource(R.string.hitung))
        }





    }




    }



@Composable
fun checked (label: String, isSelected: Boolean, modifier: Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )

    }

}
private  fun hitungKembalian(cash: Float, totalPembayaran:Float): Float {
    return cash - totalPembayaran

}





@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    KembaliankuTheme {
        MainScreen()
    }
}