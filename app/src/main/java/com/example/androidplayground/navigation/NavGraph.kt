package com.example.androidplayground.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidplayground.navigation.AppDestination.MARVEL_CHARACTER_ID_PARAM
import com.example.androidplayground.navigation.AppDestination.MARVEL_CHARACTER_PARAM
import com.example.androidplayground.navigation.model.MarvelCharacterArgType
import com.example.androidplayground.navigation.model.NavMarvelCharacter
import com.example.androidplayground.navigation.model.toMarvelCharacter
import com.example.androidplayground.ui.screen.main.MainScreen
import com.example.androidplayground.ui.screen.marvel.dialog.MarvelCharacterDialogScreen
import com.example.androidplayground.ui.screen.marvel.list.MarvelCharacterListScreen
import com.example.androidplayground.ui.screen.marvel.splash.SplashScreen
import com.example.androidplayground.ui.screen.marvel.detail.MarvelCharacterDetailScreen
import com.google.gson.Gson

fun NavGraphBuilder.addFeedScreenGraph(navController: NavController) {
    composable(route = AppScreen.Splash.route) {
        SplashScreen {
            navController.popBackStack()
            navController.navigate(AppScreen.Main.route)
        }
    }
    composable(route = AppScreen.Main.route) {
        MainScreen { navController.navigate(AppScreen.MarvelCharacterList.route) }
    }
    composable(route = AppScreen.MarvelCharacterList.route) {
        MarvelCharacterListScreen(
            onItemClicked = { marvelCharacter -> navController.navigate(AppScreen.MarvelCharacterDetail.createRoute(marvelCharacter.id)) },
            onError = { navController.popBackStack() }
        )
    }
    // Ejemplo para ver como pasar algo por param que no sea string..
    // si es string no hay que definir el arguments
    composable(
        route = AppScreen.MarvelCharacterDetail.route,
        arguments = listOf(
            navArgument(MARVEL_CHARACTER_ID_PARAM) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val marvelCharacterId = backStackEntry.arguments?.getInt(MARVEL_CHARACTER_ID_PARAM)
        requireNotNull(marvelCharacterId)
        MarvelCharacterDetailScreen(
            marvelCharacterId = marvelCharacterId,
            onImageClicked = { marvelCharacter -> navController.navigate(AppScreen.MarvelCharacterDialog.createRoute(marvelCharacter)) },
            onError = { navController.popBackStack() }
        )
    }
    // Esto lo hice solamente como ejemplo para ver como pasar un objecto por param
    composable(
        route = AppScreen.MarvelCharacterDialog.route,
        arguments = listOf(
            navArgument(MARVEL_CHARACTER_PARAM) { type = MarvelCharacterArgType() }
        )
    ) { backStackEntry ->
        val navMarvelCharacter =
            backStackEntry.arguments?.getString(MARVEL_CHARACTER_PARAM)?.let { Gson().fromJson(it, NavMarvelCharacter::class.java) }
        requireNotNull(navMarvelCharacter)
        MarvelCharacterDialogScreen(
            navMarvelCharacter.toMarvelCharacter(),
            onDismiss = { navController.popBackStack() }
        )
    }
}