package com.example.pocketcalendarv3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement.End as ArrangementEnd


@Composable
fun UserPageView(navController: NavController) {
    NavigationBar(contentColor = Color(0xFFABCEF5)) {
        Column(

        ) {
            Text(text = "Profile", color = Color.Red, modifier = Modifier.padding(bottom = 215.dp))

            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = 16.dp, bottom = 6.dp)
            ) {

                Icon(Icons.Filled.Person, contentDescription = "")

                Text(
                    text = "My Profile",
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp)

                )


            }
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = 16.dp, bottom = 6.dp)
            ) {
                Icon(Icons.Filled.StackedBarChart, contentDescription = "")

                Text(
                    text = "Statistic",
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp)
                )


            }
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = 16.dp, bottom = 6.dp)
            ) {
                Icon(Icons.Filled.Lock, contentDescription = "")

                Text(
                    text = "Security",
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp)

                )


            }
            ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 16.dp)) {

                Icon(Icons.Filled.Logout, contentDescription = "")
                Text(
                    text = "Logout", color = Color.Red, modifier = Modifier.padding(start = 16.dp)
                )


            }

            Row(

                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.run {
                    fillMaxWidth()
                        .height(198.dp)
                        .background(color = Color.Red)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)


                )
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)


                )
                Icon(
                    Icons.Filled.Person, contentDescription = "", modifier = Modifier.size(50.dp)

                )


            }


        }

    }


}
