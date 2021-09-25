package com.shaadi.demo.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaadi.demo.UserViewModel
import com.shaadi.demo.data.model.User
import com.shaadi.demo.utility.DEFAULT_RECIPE_IMAGE
import com.shaadi.demo.utility.loadPicture

@Composable
fun ProfileCard(user: User, userViewModel: UserViewModel = hiltViewModel()) {


    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 7.dp)
            .fillMaxWidth(),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center

        ) {

            Spacer(modifier = Modifier.height(10.dp))
            val image = loadPicture(url = user.image, defaultImage = DEFAULT_RECIPE_IMAGE).value

            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "Contact profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.CenterHorizontally)
                        .border(width = 5.dp, color = Color(0xffd4d2d2), shape = CircleShape)
                )
            }


            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = user.name,
                style = MaterialTheme.typography.h1,
                color = Color(0xFF838383),
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = user.bio,
                style = MaterialTheme.typography.h3,
                color = Color(0xFF646464),
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.width(15.dp))

            if (user.status == 0) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column {
                        Image(
                            painter = painterResource(com.shaadi.demo.R.drawable.close),
                            contentDescription = "reject button",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .align(alignment = Alignment.CenterHorizontally)
                                .background(color = Color.Red)
                                // .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                                .clickable {
                                    user.status = 1
                                    userViewModel.updateUserStatus(user)
                                }
                        )

                        Text(
                            text = "Reject",
                            style = MaterialTheme.typography.h2,
                            color = Color.Red,
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        )
                    }

                    Column {
                        Image(
                            painter = painterResource(com.shaadi.demo.R.drawable.check),
                            contentDescription = "accept button",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .align(alignment = Alignment.CenterHorizontally)
                                .background(color = Color(0xff178207))
                                //.border(width = 2.dp, color = Color(0xff178207), shape = CircleShape)
                                .clickable {
                                    user.status = 2
                                    userViewModel.updateUserStatus(user)
                                }
                        )

                        Text(
                            text = "Accept",
                            style = MaterialTheme.typography.h2,
                            color = Color(0xff178207),
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        )
                    }
                }
            } else {
                var color = Color.White
                var status = ""
                when (user.status) {
                    1 -> {
                        color = Color.Red
                        status = "Rejected"
                    }
                    2 -> {
                        color = Color(0xff178207)
                        status = "Accepted"
                    }
                    else -> {
                        Color.White
                    }
                }
                Card(
                    shape = RoundedCornerShape(0.dp),
                    backgroundColor = color,
                    modifier = Modifier
                        //   .padding(horizontal = 10.dp, vertical = 7.dp)
                        .fillMaxWidth(),
                    elevation = 0.dp
                ) {

                    Text(
                        text = status,
                        style = MaterialTheme.typography.h2,
                        color = Color.White,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}