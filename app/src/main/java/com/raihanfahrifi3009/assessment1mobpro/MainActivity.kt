package com.raihanfahrifi3009.assessment1mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raihanfahrifi3009.assessment1mobpro.navigation.SetupNavGraph
import com.raihanfahrifi3009.assessment1mobpro.ui.theme.Assessment1MobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assessment1MobproTheme {
                SetupNavGraph()
            }
        }
    }
}