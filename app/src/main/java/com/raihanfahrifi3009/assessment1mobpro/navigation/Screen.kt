package com.raihanfahrifi3009.assessment1mobpro.navigation

import com.raihanfahrifi3009.assessment1mobpro.ui.screen.KEY_ID_BANK

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Bank: Screen("bankScreen")
    data object FormBaru: Screen("detailBankScreen")
    data object FormUbah: Screen("detailBankScreen/{$KEY_ID_BANK}") {
        fun withId(id: Long) = "detailBankScreen/$id"
    }
}