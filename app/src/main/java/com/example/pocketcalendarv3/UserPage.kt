package com.example.pocketcalendarv3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.CloudBlue
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import androidx.compose.foundation.layout.Arrangement.End as ArrangementEnd


@Composable
fun UserPageView(navController: NavController, loggedInUserEmail: String?) {
    val db = Firebase.firestore
    var username by remember {
        mutableStateOf("")
    }
    db.collection("users").get().addOnSuccessListener { document ->
        for (doc in document) {
            if (doc.data["email"] == loggedInUserEmail) {
                username = doc.data["username"].toString()

            }
        }
    }

    NavigationBar(contentColor = Color(0xFFABCEF5)) {
        Column(
            modifier = Modifier
                .background(DefaultBlue)
                .fillMaxSize()

        ) {
            Text(
                text = "Profile",
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {


                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "User Profile",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                        .border(0.dp, Color.White, CircleShape)
                )
                Text(text = username)

            }

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

            Column(modifier = Modifier.size(250.dp)) {

            }

            Row(

                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.run {
                    fillMaxWidth()
                        .height(198.dp)
                        .background(color = Color.White)

                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "",
                    modifier = Modifier.size(50.dp),


                    )
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "",
                    modifier = Modifier.size(50.dp),


                    )
                Icon(
                    Icons.Filled.Person, contentDescription = "", modifier = Modifier.size(50.dp),
                    DefaultBlue

                )


            }


        }

    }


}
