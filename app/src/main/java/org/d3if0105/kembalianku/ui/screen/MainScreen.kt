package org.d3if0105.kembalianku.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0105.kembalianku.R
import org.d3if0105.kembalianku.navigation.Screen
import org.d3if0105.kembalianku.ui.theme.KembaliankuTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick =  {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary)





                    }
                }


                )

        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var JumlahUangTunai by rememberSaveable { mutableStateOf("")}
    var JumlahUangTunaiError by rememberSaveable { mutableStateOf(false)}

    var JumlahPembayaran by rememberSaveable { mutableStateOf("")}
    var JumlahPembayaranError by rememberSaveable { mutableStateOf(false)}
    var Kembalian by rememberSaveable { mutableFloatStateOf(0f) }
    val context = LocalContext.current


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
        OutlinedTextField(
            value = JumlahUangTunai,
            onValueChange = {JumlahUangTunai = it},
            label = {Text(text = stringResource(R.string.uang_tunai))},
            isError = JumlahUangTunaiError,
            trailingIcon = { IconPicker(JumlahUangTunaiError, "Rp")},
            supportingText = { ErrorHint(JumlahUangTunaiError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = JumlahPembayaran,
            onValueChange = {JumlahPembayaran = it},
            label = { Text(text = stringResource(R.string.total_pembayaran))},
            isError = JumlahPembayaranError,
            trailingIcon = { IconPicker(JumlahPembayaranError, "Rp")},
            supportingText = { ErrorHint(JumlahPembayaranError)},
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
                    1.dp, Color.Transparent, RoundedCornerShape(20.dp)
                )
        ){
            radioOptions.forEach { text ->
                Checked(
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
                JumlahUangTunaiError = (JumlahUangTunai =="" || JumlahUangTunai =="A")
                JumlahPembayaranError = (JumlahPembayaran == "" || JumlahPembayaran =="A")
                if (JumlahUangTunaiError || JumlahPembayaranError) return@Button
                Kembalian= hitungKembalian(JumlahUangTunai.toFloat(), JumlahPembayaran.toFloat())


            },


            modifier = Modifier.padding(top = 10.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))


        }

        Text(
            text = "Kembalian: ${Kembalian} ",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = {
                val message = context.getString(
                    R.string.bagikan_template,
                    JumlahUangTunai, JumlahPembayaran, String.format("%.2f", Kembalian)
                )
                shareData(context, message)
            },
            modifier = Modifier.padding(top = 5.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.bagikan))
        }


    }
}

@Composable
fun Checked (label: String, isSelected: Boolean, modifier: Modifier){
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
        Button(
            onClick = { },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.bagikan))

        }
    }
}

private fun hitungKembalian(cash: Float, totalPembayaran:Float): Float {
    return cash - totalPembayaran
}

private fun shareData(context: Context, message: String) {
val shareIntent = Intent(Intent.ACTION_SEND) . apply {
    type = "text/plain"
    putExtra(Intent.EXTRA_TEXT, message)
}

    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)

    }

}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    KembaliankuTheme {
        MainScreen(rememberNavController())
    }
}
@Composable
fun IconPicker(isError: Boolean, unit:String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else{
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}





