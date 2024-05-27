package com.example.pocketcalendarv3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.fontForDate

@Composable
fun EditPriorityTask(modifier: Modifier = Modifier, navController: NavController) {

    var name by remember {
        mutableStateOf("")
    }

    Column {

        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack, contentDescription = "",
                    tint = DefaultBlue
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(10.dp)
                .border(BorderStroke(3.dp, Color(0xffcae1ff))),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Edit Task",
                modifier = Modifier
                    .wrapContentHeight(),
                fontSize = 20.sp,
                color = DefaultBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier.fillMaxWidth(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "UI Design Project",
                modifier = Modifier
                    .wrapContentHeight(),
                fontSize = 32.sp,
                color = DefaultBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text(
                text = "Start", modifier = Modifier.padding(start = 30.dp),
                fontWeight = FontWeight.Medium,
                fontFamily = fontForDate,
                color = DefaultBlue,
                fontSize = 17.sp
            )

            Text(
                text = "Ends", modifier = Modifier.padding(start = 160.dp),
                fontWeight = FontWeight.Medium,
                fontFamily = fontForDate,
                color = DefaultBlue,
                fontSize = 17.sp
            )
        }


        Row {

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .weight(1f)
                    .size(100.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xffcae1ff),
                    contentColor = Color.Black,

                    )
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = "",
                        modifier = Modifier.padding(1.dp),
                        tint = Color(0xff006EE9)
                    )

                    Text(
                        text = "May-1-2024",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontForDate,
                    )

                }
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .weight(1f)
                    .size(100.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xffcae1ff),
                    contentColor = Color.Black,

                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = "",
                        modifier = Modifier.padding(1.dp),
                        tint = Color(0xff006EE9)
                    )

                    Text(
                        text = "May-24-2024",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontForDate,
                    )

                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Title", modifier = Modifier.padding(start = 30.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontForDate,
            color = DefaultBlue
        )


        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .size(75.dp)
                .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color(0xffcae1ff))
        ) {

            TextField(
                value = name, onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Description", modifier = Modifier.padding(start = 30.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontForDate,
            color = DefaultBlue
        )

        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .size(140.dp)
                .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color(0xffcae1ff))
        ) {

            TextField(value = name, onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier.fillMaxSize(),
                placeholder = {
                    Text("Write about it...")
                }
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "To Do List", modifier = Modifier.padding(start = 30.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontForDate,
            color = DefaultBlue
        )




        Spacer(modifier = Modifier.height(50.dp))


        IconButton(
            onClick = { },
            modifier = Modifier.padding(start = 350.dp)
        ) {
            Icon(
                Icons.Filled.Add, contentDescription = "",
                modifier = Modifier
                    .border(BorderStroke(0.dp, DefaultBlue), CircleShape)
                    .clip(
                        CircleShape
                    )
                    .background(
                        DefaultBlue
                    )
                    .scale(0.8f)
                    .fillMaxSize(), tint = Color.White
            )
        }


        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xffff3030),
                contentColor = Color.White,

                )
        ) {
            Text(
                text = "Remove Task",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold

            )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = DefaultBlue,
                contentColor = Color.White,

                )
        ) {
            Text(
                text = "Edit Task",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
        }


    }


}


