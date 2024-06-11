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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
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

        Column {
            Column(
                modifier = Modifier
                    .background(color = White)
                    .fillMaxSize()
                    .weight(0.9f)

            ) {
                Row(
                    modifier = Modifier
                        .background(DefaultBlue)
                        .fillMaxWidth()
                        .padding(bottom = 25.dp)
                ) {
                    Text(
                        text = "Profile",
                        color = Color.White,
                        modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                        fontFamily = fontForDate,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                ) {


                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_launcher_foreground
                        ),
                        contentDescription = "User Profile",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .aspectRatio(1f)
                            .border(0.dp, Color.White, CircleShape)
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = username, modifier = Modifier
                            .padding(top = 7.dp), DefaultBlue, fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(text = "Student", modifier = Modifier, color = Color.Black)
                    Spacer(modifier = Modifier.height(22.dp))

                    Row {
                        Icon(
                            Icons.Filled.ShoppingBag,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            DefaultBlue
                        )
                        Text(
                            text = "... Task Completed",
                            color = Black,
                            modifier = Modifier,
                            fontSize = 15.sp
                        )


                    }


                }
                Spacer(modifier = Modifier.height(40.dp))
                Column(modifier = Modifier.background(color = White)) {


                    ElevatedButton(
                        onClick = { navController.navigate("MyProfilePageView/$loggedInUserEmail") },
                        modifier = Modifier.padding(start = 16.dp, bottom = 6.dp)
                    ) {

                        Icon(Icons.Filled.Person, contentDescription = "")

                        Text(
                            text = "My Profile",
                            color = Black,
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
                            color = Black,
                            modifier = Modifier.padding(start = 16.dp)
                        )


                    }
                    ElevatedButton(
                        onClick = { navController.navigate("ChangePasswordPage/${loggedInUserEmail}") },
                        modifier = Modifier.padding(start = 16.dp, bottom = 6.dp)
                    ) {
                        Icon(Icons.Filled.Lock, contentDescription = "")

                        Text(
                            text = "Security",
                            color = Black,
                            modifier = Modifier.padding(start = 16.dp)

                        )


                    }
                    ElevatedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(start = 16.dp)
                    ) {

                        Icon(Icons.Filled.Logout, contentDescription = "")
                        Text(
                            text = "Logout",
                            color = Black,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                }


            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.1f),
                verticalArrangement = Arrangement.Bottom,
            ) {
                NavigationBar(
                    contentColor = Color(0xFFABCEF5),
                    containerColor = Color.White,
                    modifier = Modifier.fillMaxSize()
                        .shadow(15.dp, RoundedCornerShape(15.dp), spotColor = Color(0xFFABCEF5))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        IconButton(onClick = { navController.navigate("MainPage/$loggedInUserEmail") }) {
                            Icon(
                                Icons.Rounded.Home,
                                contentDescription = "",
                                modifier = Modifier.scale(1.8f)
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                Icons.Rounded.Add, contentDescription = "", modifier = Modifier
                                    .scale(1.8f)
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Rounded.Person,
                                contentDescription = "",
                                modifier = Modifier.scale(1.8f),
                                tint = Color(0XFF006EE9)
                            )
                        }
                    }
                }
            }
        }

    }


}

