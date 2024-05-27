package com.example.pocketcalendarv3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun DetailPriorityTask(modifier: Modifier = Modifier, navController: NavController) {

    val db = Firebase.firestore



    Column {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Filled.ArrowBack, contentDescription = "",
                tint = DefaultBlue
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "UI Design Project",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .wrapContentHeight(),
                fontSize = 32.sp,
                color = DefaultBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,

                )

            IconButton(
                onClick = { navController.navigate("EditPriorityTask") },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    Icons.Filled.Edit, contentDescription = "",
                    modifier = Modifier
                        .border(BorderStroke(0.dp, DefaultBlue), CircleShape)
                        .clip(
                            CircleShape
                        )
                        .background(
                            DefaultBlue
                        )
                        .scale(0.7f)
                        .fillMaxSize(), tint = Color.White
                )
            }
        }


        Row {
            Text(
                text = "start", modifier = Modifier.padding(start = 20.dp),

                fontSize = 18.sp, fontWeight = FontWeight.Medium, fontFamily = fontForDate,
            )

            Text(
                text = "end", modifier = Modifier.padding(start = 290.dp),
                fontSize = 18.sp, fontWeight = FontWeight.Medium, fontFamily = fontForDate,
            )
        }

        Row {
            Text(
                text = "01 May 2024", modifier = Modifier.padding(start = 20.dp),

                fontSize = 15.sp, fontWeight = FontWeight.Light, fontFamily = fontForDate,
            )

            Text(
                text = "21 May 2024", modifier = Modifier.padding(start = 186.dp),
                fontSize = 15.sp, fontWeight = FontWeight.Light, fontFamily = fontForDate,
            )
        }

        Spacer(modifier = Modifier.height(30.dp))


        Row {

            Card(
                modifier = Modifier
                    .weight(1f)
                    .size(150.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff006EE9),
                    contentColor = Color.White
                )

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "0",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontForDate,
                    )

                    Text(
                        text = "days",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = fontForDate,
                    )
                }
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .size(150.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff006EE9),
                    contentColor = Color.White
                )

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "2",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontForDate,
                    )

                    Text(
                        text = "days",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = fontForDate,
                    )
                }
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .size(150.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff006EE9),
                    contentColor = Color.White
                )

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "14",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontForDate,
                    )

                    Text(
                        text = "hours",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = fontForDate,
                    )

                }
            }
        }


        Spacer(modifier = Modifier.height(28.dp))


        Text(
            text = "Progress", modifier = Modifier.padding(15.dp),
            fontSize = 19.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fontForDate,
        )


        LinearProgressIndicator(

            color = Color(0xFF004797),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .height(20.dp),
            strokeCap = StrokeCap.Round,
            trackColor = TextFieldGray
        )

        Text(
            text = "To Do List", modifier = Modifier.padding(15.dp),
            fontSize = 19.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fontForDate,
        )


        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .border(
                    BorderStroke(0.dp, DefaultBlue), RoundedCornerShape(10.dp)
                )
                .padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontForDate
            )


        }


    }
}



























