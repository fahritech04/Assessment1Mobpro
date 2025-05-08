package com.raihanfahrifi3009.assessment1mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raihanfahrifi3009.assessment1mobpro.ui.screen.AboutScreen
import com.raihanfahrifi3009.assessment1mobpro.ui.screen.BankScreen
import com.raihanfahrifi3009.assessment1mobpro.ui.screen.DetailBankScreen
import com.raihanfahrifi3009.assessment1mobpro.ui.screen.KEY_ID_BANK
import com.raihanfahrifi3009.assessment1mobpro.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Bank.route) {
            BankScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailBankScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_BANK) { type = NavType.LongType }
            )
        ){  navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_BANK)
            DetailBankScreen(navController, id)
        }
    }
}