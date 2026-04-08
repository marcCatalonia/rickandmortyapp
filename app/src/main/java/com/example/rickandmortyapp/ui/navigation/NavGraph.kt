package com.example.rickandmortyapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortyapp.ui.screens.characters.CharactersScreen
import com.example.rickandmortyapp.ui.screens.detail.CharacterDetailScreen

@Composable
fun NavGraph(paddingValues: PaddingValues){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Screen.Characters.route){

        composable(Screen.Characters.route) {
            CharactersScreen(paddingValues = paddingValues, navController = navController)
        }

        composable(
            route = "${Screen.Detail.route}/{characterId}",
            arguments = listOf(
                navArgument("characterId"){ type = NavType.IntType}
            )) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("characterId")
            CharacterDetailScreen(paddingValues)
        }
    }
}


sealed class Screen(val route: String){
    object Characters: Screen("characters")
    object Detail: Screen("detail"){
        fun createRoute(id: Int) = "detail/$id"
    }
}