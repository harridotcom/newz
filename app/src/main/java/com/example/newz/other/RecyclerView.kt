package com.example.newz.other

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newz.api.NewsList
import com.example.newz.api.Result
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.newz.room.FavoriteNews
import com.example.newz.room.NewsDao
import com.example.newz.vms.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    newsList: NewsList,
    mainViewModel: MainViewModel,
    auth: FirebaseAuth,
    isFavoriteNews: Boolean,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(newsList.results) { news ->
            NewsCard(
                news = news,
                mainViewModel = mainViewModel,
                auth = auth,
                isFavoriteNews = isFavoriteNews,
                navController = navController
            )
        }
    }
}

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    news: Result,
    mainViewModel: MainViewModel,
    auth: FirebaseAuth,
    isFavoriteNews: Boolean,
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("article/${news.article_id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = news.image_url ?: "",
                    contentDescription = "News Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = news.title ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Text(
                        text = news.description ?: "",
                        fontSize = 14.sp,
                        maxLines = 3,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = news.source_name ?: "",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (isFavoriteNews){
                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                try {
                                    var currentUserId = auth.currentUser!!.uid
                                    val favoriteNews = FavoriteNews(
                                        article_id = news.article_id ?: return@launch,
                                        userId = currentUserId,
                                        ai_org = news.ai_org ?: "",
                                        ai_region = news.ai_region ?: "",
                                        ai_tag = news.ai_tag ?: "",
                                        category = news.category?.joinToString(",") ?: "",
                                        content = news.content ?: "",
                                        country = news.country?.joinToString(",") ?: "",
                                        creator = news.creator?.joinToString(",") ?: "",
                                        description = news.description ?: "",
                                        duplicate = news.duplicate ?: false,
                                        image_url = news.image_url ?: "",
                                        keywords = news.keywords?.joinToString(",") ?: "",
                                        language = news.language ?: "",
                                        link = news.link ?: "",
                                        pubDate = news.pubDate ?: "",
                                        pubDateTZ = news.pubDateTZ ?: "",
                                        sentiment = news.sentiment ?: "",
                                        sentiment_stats = news.sentiment_stats ?: "",
                                        source_icon = news.source_icon ?: "",
                                        source_id = news.source_id ?: "",
                                        source_name = news.source_name ?: "",
                                        source_priority = news.source_priority ?: 0,
                                        source_url = news.source_url ?: "",
                                        title = news.title ?: "",
                                        video_url = news.video_url?.toString() ?: ""
                                    )
                                    mainViewModel.deleteFavorite(favoriteNews)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete News",
                            tint = Color.Gray
                        )
                    }
                }else{
                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                try {
                                    var currentUserId = auth.currentUser!!.uid
                                    val favoriteNews = FavoriteNews(
                                        article_id = news.article_id ?: return@launch,
                                        userId = currentUserId,
                                        ai_org = news.ai_org ?: "",
                                        ai_region = news.ai_region ?: "",
                                        ai_tag = news.ai_tag ?: "",
                                        category = news.category?.joinToString(",") ?: "",
                                        content = news.content ?: "",
                                        country = news.country?.joinToString(",") ?: "",
                                        creator = news.creator?.joinToString(",") ?: "",
                                        description = news.description ?: "",
                                        duplicate = news.duplicate ?: false,
                                        image_url = news.image_url ?: "",
                                        keywords = news.keywords?.joinToString(",") ?: "",
                                        language = news.language ?: "",
                                        link = news.link ?: "",
                                        pubDate = news.pubDate ?: "",
                                        pubDateTZ = news.pubDateTZ ?: "",
                                        sentiment = news.sentiment ?: "",
                                        sentiment_stats = news.sentiment_stats ?: "",
                                        source_icon = news.source_icon ?: "",
                                        source_id = news.source_id ?: "",
                                        source_name = news.source_name ?: "",
                                        source_priority = news.source_priority ?: 0,
                                        source_url = news.source_url ?: "",
                                        title = news.title ?: "",
                                        video_url = news.video_url?.toString() ?: ""
                                    )
                                    mainViewModel.insertFavorite(favoriteNews)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Add to favorites",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }

}

fun OnNewsCardClicked(link: String){
    Log.d("Main", "$link")
}