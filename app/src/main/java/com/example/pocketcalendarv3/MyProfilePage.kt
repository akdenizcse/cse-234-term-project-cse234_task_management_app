package com.example.pocketcalendarv3

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


@Composable
fun MyProfilePageView(
    modifier: Modifier = Modifier,
    navController: NavController,


    ) {
    val db = Firebase.firestore

    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }
    var profession by remember {
        mutableStateOf("")
    }
    var dateOfBirth by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var image by remember {
        mutableStateOf(
            R.drawable.user
        )
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DefaultBlue)
                .size(150.dp)
                .padding(start = 16.dp, top = 40.dp)
        ) {


            ElevatedButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack, contentDescription = "",
                    tint = DefaultBlue,
                )
            }
            Text(
                text = "My Profile",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(bottom = 45.dp, start = 73.dp),
                Color.White,
                fontFamily = fontForDate,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,

                )
        }
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.user
                ),
                contentDescription = "User Profile",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .border(0.dp, Color.White, CircleShape)
                    .clickable {
                        image =
                            if (image == R.drawable.user) R.drawable.user
                            else R.drawable.user
                    }
            )

            Text(
                text = "Name", modifier = Modifier.padding(start = 16.dp),
                DefaultBlue,
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("User") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Text(
                text = "Profession", modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                DefaultBlue,
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )


            TextField(
                value = profession,
                onValueChange = { profession = it },
                placeholder = { Text("Student") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )
            Text(
                text = "Date of Birth", modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                DefaultBlue,
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            TextField(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                placeholder = { Text("Jul-31-2002") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Text(
                text = "Email", modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                DefaultBlue,
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("oguz.bugra29@hotmail.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .size(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DefaultBlue,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Submit")
            } 

        

        
    }

}}


