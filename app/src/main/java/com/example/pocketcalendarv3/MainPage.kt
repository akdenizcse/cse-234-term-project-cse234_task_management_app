package com.example.pocketcalendarv3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import com.example.pocketcalendarv3.ui.theme.fontFamily
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPage(navController: NavController, loggedInUserEmail: String?) {

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



    Column(modifier = Modifier.fillMaxWidth()) {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy")
        val formattedDate = currentDate.format(formatter)

        Text(
            text = formattedDate,
            modifier = Modifier.padding(top = 50.dp, start = 16.dp),
            color = Color(0xFF474747),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fontForDate
        )
        Text(
            text = "Welcome $username,",
            modifier = Modifier.padding(top = 50.dp, start = 16.dp),
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontForDate
        )
        Text(
            text = "Have a nice day !",
            modifier = Modifier.padding(start = 16.dp),
            color = Color(0xFF474747),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fontForDate)

        Text(text = "Long Term Tasks",
            modifier = Modifier.padding(top = 40.dp, start = 16.dp),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontForDate
        )
    }
}