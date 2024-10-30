package com.example.newz.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newz.api.NewsList
import com.example.newz.api.Result
import com.example.newz.other.BottomNavView
import com.example.newz.other.NewsListScreen
import com.example.newz.other.TopNavView
import com.example.newz.room.FavoriteNews
import com.example.newz.room.RoomDb
import com.example.newz.vms.AuthViewModel
import com.example.newz.vms.MainViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FavoritePage(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController,
    mainViewModel: MainViewModel,

) {
    val context = LocalContext.current
    authViewModel.authState.observeAsState()
    val auth = FirebaseAuth.getInstance()
    val favorites by mainViewModel.liveDataDb.observeAsState(initial = emptyList())

    var isFavorite by remember {
        mutableStateOf(false)
    }
    rememberCoroutineScope()

    var favoriteNews by remember { mutableStateOf<List<FavoriteNews>>(emptyList()) }

    LaunchedEffect(Unit) {
        isFavorite = true
        try {
            val userId = auth.currentUser!!.uid
            mainViewModel.getFavoriteNews(userId)
            favoriteNews = favorites

        } catch (e: Exception) {
            Log.e("Fav", "Error fetching favorites", e)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            isFavorite = false
        }
    }

    val newsListResults = favoriteNews.map { favorite ->
        Result(
            ai_org = favorite.ai_org,
            ai_region = favorite.ai_region,
            ai_tag = favorite.ai_tag,
            article_id = favorite.article_id,
            category = favorite.category.split(",").filter { it.isNotEmpty() },
            content = favorite.content,
            country = favorite.country.split(",").filter { it.isNotEmpty() },
            creator = favorite.creator.split(",").filter { it.isNotEmpty() },
            description = favorite.description,
            duplicate = favorite.duplicate,
            image_url = favorite.image_url,
            keywords = favorite.keywords.split(",").filter { it.isNotEmpty() },
            language = favorite.language,
            link = favorite.link,
            pubDate = favorite.pubDate,
            pubDateTZ = favorite.pubDateTZ,
            sentiment = favorite.sentiment,
            sentiment_stats = favorite.sentiment_stats,
            source_icon = favorite.source_icon,
            source_id = favorite.source_id,
            source_name = favorite.source_name,
            source_priority = favorite.source_priority,
            source_url = favorite.source_url,
            title = favorite.title,
            video_url = favorite.video_url
        )
    }

    val newsList = NewsList(
        nextPage = "",
        status = "ok",
        totalResults = favoriteNews.size,
        results = newsListResults
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopNavView(navController = navController, title = "Newz")
        },
        bottomBar = {
            BottomNavView(navController = navController)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (favoriteNews.isNotEmpty()) {
                NewsListScreen(newsList = newsList, mainViewModel = mainViewModel, auth = auth, isFavoriteNews = isFavorite, navController = navController)
            } else {
                Text(
                    text = "No favorite news found",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}