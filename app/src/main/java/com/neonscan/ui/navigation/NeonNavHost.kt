package com.neonscan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neonscan.data.repo.DocumentRepository
import com.neonscan.data.settings.SettingsRepository
import com.neonscan.ui.detail.DetailScreen
import com.neonscan.ui.edit.EditScreen
import com.neonscan.ui.home.HomeScreen
import com.neonscan.ui.options.OptionsScreen
import com.neonscan.ui.scan.ScanScreen
import com.neonscan.ui.settings.SettingsScreen

@Composable
fun NeonNavHost(
    repo: DocumentRepository,
    settingsRepository: SettingsRepository,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController, repo = repo) }
        composable("scan") { ScanScreen(navController = navController, settingsRepository = settingsRepository) }
        composable("edit") { EditScreen(navController = navController) }
        composable("options") { OptionsScreen(navController = navController, repo = repo, settingsRepository = settingsRepository) }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            DetailScreen(navController = navController, repo = repo, id = id)
        }
        composable("settings") { SettingsScreen(navController = navController, settingsRepository = settingsRepository) }
    }
}
