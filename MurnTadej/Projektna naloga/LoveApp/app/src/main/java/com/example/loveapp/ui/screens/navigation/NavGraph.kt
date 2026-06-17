package com.example.loveapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loveapp.ui.screens.HomeScreen
import com.example.loveapp.ui.screens.SettingsScreen
import com.example.loveapp.ui.screens.WelcomeScreen
import com.example.loveapp.ui.viewmodel.RelationshipViewModel

// Centralizirane definicije rout (poti) - da ne pišemo "magic strings"
object Routes {
    const val WELCOME = "welcome"
    const val HOME = "home"
    const val SETTINGS = "settings"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: RelationshipViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Loading state - dokler DataStore prvič ne odda podatkov,
    // ne vemo ali pokazati Welcome ali Home
    if (!state.isLoaded) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // Odločitev kje začnemo - če je setup že narejen, gremo direktno na Home
    val startDestination = if (state.setupDone) Routes.HOME else Routes.WELCOME

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.WELCOME) {
            WelcomeScreen(
                viewModel = viewModel,
                onSetupComplete = {
                    // Po končanem setupu: pojdi na HOME in odstrani WELCOME iz back stacka,
                    // da se uporabnik z back gumbom ne more vrniti na Welcome
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onSettingsClick = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onResetClick = {
                    viewModel.resetAll()
                    navController.navigate(Routes.WELCOME) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}