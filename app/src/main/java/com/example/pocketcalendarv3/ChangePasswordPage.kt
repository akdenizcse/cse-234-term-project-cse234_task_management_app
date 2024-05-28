package com.example.pocketcalendarv3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.fontForDate
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


@Composable
fun ChangePasswordPageView(modifier: Modifier = Modifier, navController: NavController) {
    val db = Firebase.firestore

    val context = LocalContext.current

    var currentPassword by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmNewPassword by remember {
        mutableStateOf("")
    }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DefaultBlue)
                .size(150.dp)
                .padding(start = 16.dp, top = 50.dp)
        ) {


            ElevatedButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack, contentDescription = "",
                    tint = DefaultBlue,
                )
            }
            Text(
                text = "Security",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(bottom = 45.dp, start = 73.dp),
                White,
                fontFamily = fontForDate,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,

                )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            Text(
                text = "Password",
                modifier = Modifier.padding(start = 16.dp, top = 75.dp),
                DefaultBlue,
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,

                )
            TextField(
                value = currentPassword, onValueChange = { currentPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = White
                ),


                label = { Text(text = "Current Password", color = TextFieldGray) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "", tint = DefaultBlue) },


                )
            TextField(
                value = newPassword, onValueChange = { newPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = White
                ),


                label = { Text(text = "New Password", color = TextFieldGray) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "", tint = DefaultBlue) },


                )
            TextField(
                value = confirmNewPassword, onValueChange = { confirmNewPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = White
                ),


                label = { Text(text = "Confirm New Password", color = TextFieldGray) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "", tint = DefaultBlue) },


                )






            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp).size(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DefaultBlue,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Submit")
            }
        }



    }


}