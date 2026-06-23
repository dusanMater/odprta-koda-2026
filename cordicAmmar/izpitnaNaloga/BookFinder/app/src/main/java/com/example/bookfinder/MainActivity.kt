package com.example.bookfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookfinder.screens.BookDetails
import com.example.bookfinder.screens.FavouritesScreen
import com.example.bookfinder.screens.MainScreen

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Favourites : Screen("favourites")
    object BookDetails : Screen("details/{bookDetails}")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentRoute = navController.currentBackStackEntry?.destination?.route

            NavHost(
                navController = navController,
                startDestination = Screen.Main.route
            ) {
                composable (Screen.Main.route) {
                    MainScreen(navController)
                }

                composable (Screen.Favourites.route) {
                    FavouritesScreen(navController)
                }

                composable (Screen.BookDetails.route) { bookStackEntry ->
                    val bookKey = bookStackEntry.arguments?.getString("bookDetails")
                    BookDetails(bookKey)
                }
            }

            Scaffold (
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = currentRoute == "main",
                            onClick = {
                                navController.navigate("main")
                            },
                            icon = {
                                Image(
                                    painter = painterResource(
                                        R.drawable.home
                                    ),
                                    contentDescription = "Home",
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        )

                        NavigationBarItem(
                            selected = currentRoute == "favourites",
                            onClick = {
                                navController.navigate("favourites")
                            },
                            icon = {
                                Image(
                                    painter = painterResource(
                                        R.drawable.star
                                    ),
                                    contentDescription = "Favourites",
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        )
                    }
                },
                content = { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Main.route) {
                            MainScreen(navController)
                        }
                        composable(Screen.Favourites.route) {
                            FavouritesScreen(navController)
                        }
                        composable(Screen.BookDetails.route) {bookStackEntry ->
                            val bookKey = bookStackEntry.arguments?.getString("bookDetails")
                            BookDetails(bookKey)
                        }
                    }
                }
            )
        }
    }
}
