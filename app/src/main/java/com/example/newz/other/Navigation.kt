package com.example.newz.other

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newz.pages.ArticlePage
import com.example.newz.pages.FavoritePage
import com.example.newz.pages.HomePage
import com.example.newz.pages.LoginPage
import com.example.newz.pages.Register
import com.example.newz.pages.SearchPage
import com.example.newz.vms.AuthViewModel
import com.example.newz.vms.MainViewModel

@Composable
fun Navigation(authViewModel: AuthViewModel, repository: Repository, mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login"){
            LoginPage(navController = navController, authViewModel = authViewModel)
        }
        composable("register"){
            Register(navController = navController, authViewModel = authViewModel)
        }
        composable("home"){
            HomePage(authViewModel = authViewModel, navController = navController, mainViewModel = mainViewModel)
        }
        composable("favorite"){
            FavoritePage(authViewModel = authViewModel, navController = navController, mainViewModel = mainViewModel)
        }
        composable("search"){
            SearchPage(navController = navController)
        }
        composable("article/{articleId}"){
                backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId")
            if (articleId != null) {
                ArticlePage(articleId = articleId, mainViewModel = mainViewModel, navController = navController)
            }
        }
    }
}