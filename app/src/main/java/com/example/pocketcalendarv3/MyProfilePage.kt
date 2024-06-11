package com.example.pocketcalendarv3

import android.app.DatePickerDialog
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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import java.util.Calendar


@Composable
fun MyProfilePageView(
    modifier: Modifier = Modifier,
    navController: NavController,
    loggedInUserEmail: String?


) {


    var selectedDate by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val months = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "June",
        "July", "Aug", "Sep", "Oct", "Nov", "Dec"
    )


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

    LaunchedEffect(key1 = loggedInUserEmail ){
        db.collection("users").whereEqualTo("email", loggedInUserEmail).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    username = document.data["username"].toString()

                    profession = document.data["profession"].toString()

                    dateOfBirth = document.data["dateOfBirth"].toString()

                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error getting documents: $exception", Toast.LENGTH_SHORT)
                    .show()
            }
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
                text = "Username", modifier = Modifier.padding(start = 16.dp),
                DefaultBlue,
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(0.dp, Color(0xffcae1ff)), RoundedCornerShape(15.dp)),
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


            OutlinedCard (modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),border = BorderStroke(0.dp, Color(0xffcae1ff))){
                TextField(
                    value = profession,
                    onValueChange = { profession = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .padding(horizontal = 16.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    )
                )
            }
            Text(
                text = "Date of Birth", modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                DefaultBlue,
                fontFamily = fontForDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            OutlinedCard(
                modifier = Modifier
                    .clickable {
                        DatePickerDialog(
                            context,
                            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                                selectedDate =
                                    "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"

                            },
                            year,
                            month,
                            day
                        ).show()




                    }
                    .padding(horizontal = 16.dp), colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ), border = BorderStroke(0.dp, Color(0xffcae1ff))
            ) {
                Row(modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .fillMaxWidth()) {
                    Icon(
                        Icons.Filled.CalendarToday,
                        contentDescription = "",
                        tint = DefaultBlue
                    )

                    Text(
                        text = if (selectedDate.isEmpty()) dateOfBirth else " $selectedDate",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontFamily = fontForDate,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = {
                    dateOfBirth = selectedDate
                    db.collection("users").whereEqualTo("email", loggedInUserEmail).get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                db.collection("users").document(document.id).update(
                                    mapOf(
                                        "username" to username,
                                        "profession" to profession,
                                        "dateOfBirth" to dateOfBirth
                                    )
                                )
                            }
                        }

                    navController.popBackStack()


                }, modifier = Modifier
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

    }
}


