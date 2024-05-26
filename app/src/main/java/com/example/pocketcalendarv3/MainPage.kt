package com.example.pocketcalendarv3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
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
                    val toDoListChecked = doc.data["toDoListChecked"] as List<*>
                    val arrayListChecked = ArrayList<String>()
                    for (item in toDoListChecked) {
                        arrayListChecked.add(item.toString())
                    }

                    val task = LongTermTask(
                        title,
                        doc.data["description"].toString(),
                        doc.data["startDate"].toString(),
                        doc.data["endDate"].toString(),
                        arrayList,
                        doc.data["color"].toString(),
                        arrayListChecked
                    )
                    tasks += task
                }
            }
        }
    }



    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(0.9f)) {
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
                            .width(137.dp)
                            .clickable { navController.navigate("DetailPriorityTask") },

                        ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                        ) {

                            val date: Date? = SimpleDateFormat("yyyy-MM-dd").parse(task.endDate)
                            val diffInMillies = date!!.time - System.currentTimeMillis()
                            val diffInDays =
                                TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)

                            var progress = task.progress

                            Text(
                                text = diffInDays.toString() + " days",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.End)
                                    .clip(RoundedCornerShape(15.dp))
                                    .border(
                                        BorderStroke(0.dp, Color.White),
                                        RoundedCornerShape(15.dp)
                                    )
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
                                    modifier = Modifier.padding(start = 8.dp),
                                    color = Color.Black,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = fontForDate
                                )

                                LinearProgressIndicator(
                                    progress = progress,
                                    color = Color(0xFF004797),
                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                    strokeCap = StrokeCap.Round,
                                    trackColor = TextFieldGray
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(

                                        text = String.format("%.1f", progress * 100) + "%",
                                        color = Color.Black,
                                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = fontForDate,

                                        )
                                }


                            }


                        }
                    }
                }
            }
            Text(
                text = "Daily Tasks",
                modifier = Modifier.padding(16.dp),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontForDate
            )
            var dailyTasks by remember {
                mutableStateOf(listOf<DailyTask>())
            }
            db.collection("daily").get().addOnSuccessListener { document ->

                for (doc in document) {
                    if (doc.data["email"] == loggedInUserEmail) {

                        val title = doc.data["title"].toString()
                        if (dailyTasks.none { it.title == title }) {

                            val description = doc.data["description"].toString()
                            val checked = doc.data["checked"] as Boolean

                            val task = DailyTask(title, description, checked)
                            dailyTasks += task
                        }


                    }
                }
            }



            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                items(dailyTasks) { task ->
                    var isChecked by remember {
                        mutableStateOf(task.completed)
                    }
                    var color by remember {
                        mutableStateOf(Color.Black)
                    }
                    if (isChecked) {
                        color = Color(0xFF006EE9)
                    } else {
                        color = Color.Black
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
                            text = task.title,
                            color = color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = fontForDate
                        )

                        Checkbox(checked = isChecked, onCheckedChange = {
                            isChecked = it


                            db.collection("daily").whereEqualTo("email", loggedInUserEmail)
                                .whereEqualTo("title", task.title).get()
                                .addOnSuccessListener { document ->
                                    for (doc in document) {
                                        db.collection("daily").document(doc.id)
                                            .update("checked", isChecked)
                                    }
                                }


                        })
                    }
                    Spacer(modifier = Modifier.height(8.dp))


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
                modifier = Modifier.fillMaxSize().shadow(15.dp, RoundedCornerShape(15.dp), spotColor = Color(0xFFABCEF5))
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Home, contentDescription = "", tint = Color(0XFF006EE9), modifier = Modifier.scale(1.8f))
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Add, contentDescription = "",modifier = Modifier
                            .scale(1.8f))
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Person, contentDescription = "",modifier = Modifier.scale(1.8f))
                    }
                }
            }
        }
    }
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))





