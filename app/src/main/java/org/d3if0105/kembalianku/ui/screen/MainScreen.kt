package org.d3if0105.kembalianku.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if0105.kembalianku.R
import org.d3if0105.kembalianku.ui.theme.KembaliankuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(content: @Composable (Modifier) -> Unit) {
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
        content(Modifier.padding(padding))



    }
}




@Composable
fun Greeting (name: String){
    MainScreen{ modifier ->
        Text(
            text = "Hello Android!",
            modifier = modifier)

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KembaliankuTheme {
        Greeting("Android")
    }
}