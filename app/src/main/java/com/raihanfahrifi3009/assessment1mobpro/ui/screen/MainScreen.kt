package com.raihanfahrifi3009.assessment1mobpro.ui.screen

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raihanfahrifi3009.assessment1mobpro.R
import com.raihanfahrifi3009.assessment1mobpro.ui.theme.Assessment1MobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assessment1MobproTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    Scaffold(
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
    ) { innerPadding ->
        CurrencyConverterScreen(Modifier.padding(innerPadding))
    }
}

@Composable
fun CurrencyConverterScreen(modifier: Modifier = Modifier){
    var inputValue by remember { mutableStateOf("") }
    var isRupiahToDollar by remember { mutableStateOf(true) }
    var result by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val exchangeRate = 16500f // 1 USD = 16,500 IDR

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            RadioButton(
                selected = isRupiahToDollar,
                onClick = {
                    isRupiahToDollar = true
                    inputValue = ""
                    result = ""
                    showError = false
                }
            )
            Text(text = stringResource(R.string.rptousd), Modifier.padding(start = 4.dp))

            Spacer(Modifier.width(16.dp))

            RadioButton(
                selected = !isRupiahToDollar,
                onClick = {
                    isRupiahToDollar = false
                    inputValue = ""
                    result = ""
                    showError = false
                }
            )
            Text(text = stringResource(R.string.usdtorp), Modifier.padding(start = 4.dp))
        }

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                showError = false
            },
            label = {
                Text(if (isRupiahToDollar) stringResource(R.string.inputrupiah) else stringResource(R.string.inputdolar))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = showError,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                try {
                    val amount = inputValue.toFloat()
                    result = if (isRupiahToDollar) {
                        "%.2f USD".format(amount / exchangeRate)
                    } else {
                        "%.0f IDR".format(amount * exchangeRate)
                    }
                    showError = false
                } catch (e: NumberFormatException) {
                    showError = true
                    result = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.konversi))
        }


    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Assessment1MobproTheme {
        MainScreen()
    }
}