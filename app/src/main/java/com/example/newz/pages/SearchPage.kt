package com.example.newz.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newz.other.BottomNavView
import com.example.newz.other.TopNavView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchPage(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        topBar = {
            TopNavView(navController = navController, title = "Newz")
        },
        bottomBar = {
            BottomNavView(navController = navController)
        }
    ) { paddingValues ->
        Text(
            text = "Discover and search for articles here.",
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp) // Additional padding for better visibility
        )
    }
}
