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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raihanfahrifi3009.assessment1mobpro.R
import com.raihanfahrifi3009.assessment1mobpro.ui.theme.Assessment1MobproTheme
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

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
    var inputValue by rememberSaveable { mutableStateOf("") }
    var isRupiahToDollar by rememberSaveable { mutableStateOf(true) }
    var result by rememberSaveable { mutableStateOf("") }
    var showError by rememberSaveable { mutableStateOf(false) }
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
            trailingIcon = {
                IconPicker(
                    showError,
                    if (isRupiahToDollar) "Rp" else "$"
                )
            },
            supportingText = { ErrorHint(showError) },
            isError = showError,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                try {
                    val amount = inputValue.toFloat()
                    result = if (isRupiahToDollar) {
                        val usdAmount = amount / exchangeRate
                        formatCurrency(usdAmount, isDollar = true)
                    } else {
                        val rupiahAmount = amount * exchangeRate
                        formatCurrency(rupiahAmount, isDollar = false)
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

        if (result.isNotEmpty()) {
            Text(
                text = "${stringResource(R.string.hasil)}: $result",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 24.dp)
            )
        }


    }
}

fun formatCurrency(amount: Float, isDollar: Boolean): String {
    val decimalFormat = if (isDollar) {
        DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.US))
    } else {
        DecimalFormat("#,##0", DecimalFormatSymbols(Locale("id", "ID")))
    }

    return if (isDollar) {
        "USD ${decimalFormat.format(amount)}"
    } else {
        "Rp ${decimalFormat.format(amount)}"
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.showerror))
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