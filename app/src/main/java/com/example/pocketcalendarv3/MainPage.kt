package com.example.pocketcalendarv3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.SoftBlue
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.sql.Array
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.concurrent.TimeUnit


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

    var tasks by remember {
        mutableStateOf(listOf<LongTermTask>())
    }

    db.collection("longterm").get().addOnSuccessListener { document ->
        for (doc in document) {
            if (doc.data["email"] == loggedInUserEmail) {
                val title = doc.data["title"].toString()
                if (tasks.none { it.title == title }) {
                    val toDoList = doc.data["toDoList"] as List<*>
                    val arrayList = ArrayList<String>()
                    for (item in toDoList) {
                        arrayList.add(item.toString())
                    }

                    val task = LongTermTask(
                        title,
                        doc.data["description"].toString(),
                        doc.data["startDate"].toString(),
                        doc.data["endDate"].toString(),
                        arrayList,
                        doc.data["color"].toString()
                    )
                    tasks += task
                }
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
            fontFamily = fontForDate
        )

        Text(
            text = "Long Term Tasks",
            modifier = Modifier.padding(top = 40.dp, start = 16.dp),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontForDate
        )

        LazyRow {
            items(tasks) { task ->

                val color = "#${task.color}".toColor()
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(200.dp)
                        .width(137.dp),

                    ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                    ) {

                        val date: Date? = SimpleDateFormat("yyyy-MM-dd").parse(task.endDate)
                        val diffInMillies = date!!.time - System.currentTimeMillis()
                        val diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)


                        Text(
                            text = diffInDays.toString() + " days",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.End)
                                .clip(RoundedCornerShape(15.dp))
                                .border(BorderStroke(0.dp, Color.White), RoundedCornerShape(15.dp))
                                .background(Color.White)
                                .scale(0.8f),
                            textAlign = TextAlign.Center,
                            color = Color(0xFF444444),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontForDate
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = task.title,
                            modifier = Modifier.padding(12.dp),
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontForDate,
                            textAlign = TextAlign.Center
                        )
                        Column {
                            Text(
                                text = "Progress",
                                modifier = Modifier.padding(8.dp),
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = fontForDate
                            )

                            LinearProgressIndicator(
                                progress = 0.5f,
                                color = Color(0xFF004797),
                                modifier = Modifier.padding(8.dp),
                                strokeCap = StrokeCap.Round,
                                trackColor = TextFieldGray
                            )
                        }


                    }
                }
            }
        }
    }
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))

