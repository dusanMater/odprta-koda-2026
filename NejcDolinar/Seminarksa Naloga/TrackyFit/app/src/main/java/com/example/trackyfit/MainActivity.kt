package com.example.trackyfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trackyfit.ui.theme.TrackyFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackyFitTheme {
                val navController = rememberNavController()
                val workoutViewModel: WorkoutViewModel = viewModel()
                val weightViewModel: WeightViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "workouts"
                ) {
                    composable("workouts") {
                        WorkoutScreen(
                            navController = navController,
                            viewModel = workoutViewModel
                        )
                    }
                    composable("calories") {
                        CalorieScreen(
                            navController = navController
                        )
                    }
                    composable("weight") {
                        WeightScreen(
                            navController = navController,
                            viewModel = weightViewModel
                        )
                    }
                }
            }
        }
    }
}