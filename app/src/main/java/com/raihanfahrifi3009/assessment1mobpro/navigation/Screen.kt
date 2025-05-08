package com.raihanfahrifi3009.assessment1mobpro.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Bank: Screen("bankScreen")
}