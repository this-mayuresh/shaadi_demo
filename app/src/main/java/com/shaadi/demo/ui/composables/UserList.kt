package com.shaadi.demo.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaadi.demo.R
import com.shaadi.demo.UserViewModel
import com.shaadi.demo.utility.mapUserFromCache

@Composable
fun UserList(userViewModel: UserViewModel = hiltViewModel()) {
    val state = userViewModel.state.value

    val users = userViewModel.users.observeAsState(listOf())

    Column(modifier = Modifier.background(MaterialTheme.colors.primary)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()) {
            Text(
                text = "Welcome to Shaadi.com",
                style = MaterialTheme.typography.h2,
                color = Color.White,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )

            Image(
                painter = painterResource(id = R.drawable.refresh),
                contentDescription = "refresh",
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterVertically)
                    .background(color = Color.Transparent)
                    // .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                    .clickable {
                        userViewModel.getUserData()
                    }
            )


        }


        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        if (users.value.isEmpty()) {
            Text(
                text = "No data found",
                color = Color.White,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        LazyColumn(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8595F),
                            Color(0xFFFFB8B8)
                        )
                    )
                )
                .fillMaxSize()
        ) {
            itemsIndexed(users.value.reversed()) { _, user ->
                ProfileCard(mapUserFromCache(user))
            }
        }

    }

}