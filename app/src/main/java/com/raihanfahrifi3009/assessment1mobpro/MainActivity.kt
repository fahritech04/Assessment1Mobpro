package com.raihanfahrifi3009.assessment1mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raihanfahrifi3009.assessment1mobpro.ui.screen.MainScreen
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