package com.su.mmovie.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.su.model.MovieItem
import com.su.mmovie.presentation.ui.theme.DeepOrange
import com.su.mmovie.presentation.ui.theme.DeepOrangePrimary
import com.su.mmovie.presentation.ui.theme.Orange


@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            HorizontalPagerBoxWithIndicator(viewModel.state.movieLists?.upcoming)

            Spacer(modifier = Modifier.height(20.dp))

            MovieItemsBox(title = "New Arrivals",
                movieItems = viewModel.state.movieLists?.newArrivals,
                onShowMore = {})

            Spacer(modifier = Modifier.height(20.dp))

            MovieItemsBox(title = "Trending",
                movieItems = viewModel.state.movieLists?.trending,
                onShowMore = {})
        }

        if (viewModel.state.isLoading) {
            println("profile: loading")
            CircularProgressIndicator(
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        viewModel.state.errorMessage?.let {
            println("profile: error")
            Text(text = it)
        }
    }
}

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerBoxWithIndicator(
    movieItems: List<com.su.model.MovieItem>? = listOf()
) {
    if (movieItems.isNullOrEmpty()) return

    Box(
        Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
    ) {
        val pagerState = rememberPagerState(pageCount = { movieItems.size })

        HorizontalPager(
            state = pagerState,
        ) { page ->
            val item = movieItems[page]
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${item.posterPath}",
                    contentDescription = "poster",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 32.dp)
                        .background(Color.Black.copy(0.2f), RoundedCornerShape(16.dp))
                        .align(Alignment.BottomStart)
                ) {
                    Column(
                        Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = item.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = item.releaseDate,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(end = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(movieItems.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Orange else Color.White.copy(alpha = 0.5f)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)

                )
            }
        }
    }
}

@Composable
fun MovieItemsBox(
    title: String,
    movieItems: List<com.su.model.MovieItem>? = emptyList(),
    onShowMore: () -> Unit
) {

    if (movieItems.isNullOrEmpty()) return

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Box(
                Modifier
                    .clickable { onShowMore() }
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                DeepOrangePrimary,
                                DeepOrange
                            )
                        ), RoundedCornerShape(16.dp)
                    )) {
                Text(
                    text = "Show more",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        LazyRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(movieItems.size) { item ->
                val movie = movieItems[item]
                Box(modifier = Modifier.padding(8.dp)) {
                    Column {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                            contentDescription = "poster",
                            modifier = Modifier
                                .width(140.dp)
                                .height(180.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = movie.title,
                            maxLines = 1,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
            }
        }
    }

}
