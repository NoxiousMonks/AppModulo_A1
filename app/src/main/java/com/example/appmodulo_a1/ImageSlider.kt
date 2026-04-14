package com.example.appmodulo_a1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(images: List<Int>) {

    val pagerState = rememberPagerState(pageCount = { images.size })

    Box {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Индикаторы поверх картинок
        Row(
            modifier = Modifier
//                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
                .clip(shape = CircleShape)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(images.size) { index ->
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .padding(2.dp)
                        .background(
                            if (pagerState.currentPage == index)
                                Color.Black
                            else Color.White,
                            shape = CircleShape
                        )
                )
            }
        }

    }
}