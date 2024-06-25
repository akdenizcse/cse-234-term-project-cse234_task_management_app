package com.example.pocketcalendarv3

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@Composable
fun AddPriorityTask(modifier: Modifier = Modifier, navController: NavController,loggedInUserEmail:String?) {

    var selectedEndDate by remember { mutableStateOf("") }
    var selectedStartDate by remember { mutableStateOf("") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val months = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "June",
        "July", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    val db = Firebase.firestore

    var outputDateStrStart by remember { mutableStateOf("") }
    var outputDateStrEnd by remember { mutableStateOf("") }
    var outputDateStrToday by remember { mutableStateOf("") }

    var today = Date()

    var name by remember {
        mutableStateOf("")
    }

    var title by remember {
        mutableStateOf("")
    }

    var task by remember {
        mutableStateOf(
            LongTermTask(
                "",
                "",
                "",
                "",
                ArrayList(),
                "",
                ArrayList()
            )
        )
    }

    db.collection("longterm").whereEqualTo("title", title).whereEqualTo("email", loggedInUserEmail)
        .get().addOnSuccessListener {
            for (document in it) {
                val arrayList = ArrayList<String>()
                val arrayListChecked = ArrayList<String>()


                val start = document.getString("startDate").toString()
                val end = document.getString("endDate").toString()
                val description = document.getString("description").toString()


                val toDoListChecked = document.data["toDoListChecked"] as List<*>

                for (item in toDoListChecked) {

                    arrayListChecked.add(item.toString())


                }

                val toDoList = document.data["toDoList"] as List<*>

                for (item in toDoList) {

                    arrayList.add(item.toString())


                }
                val color = document.getString("color").toString()

                val longTask =
                    LongTermTask(title, description, start, end, arrayList, color, arrayListChecked)

                task = longTask


                val format = SimpleDateFormat("yyyy-MM-dd")
                val startDate = format.parse(start)
                val endDate = format.parse(end)


                outputDateStrStart = SimpleDateFormat("dd MMM yyyy").format(startDate!!)
                outputDateStrEnd = SimpleDateFormat("dd MMM yyyy").format(endDate!!)
                outputDateStrToday = SimpleDateFormat("dd MMM yyyy").format(today)

            }
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
                text = "Add Task",
                modifier = Modifier
                    .wrapContentHeight(),
                fontSize = 20.sp,
                color = DefaultBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))





        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {

            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Start",
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontForDate,
                    color = DefaultBlue,
                    fontSize = 17.sp
                )
                Card(
                    modifier = Modifier.clickable (enabled = true){


                        DatePickerDialog(
                            context,
                            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                                selectedStartDate =
                                    "$selectedDayOfMonth ${months[selectedMonth]} $selectedYear"
                            },
                            year,
                            month,
                            day
                        ).show()
                    } , colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEEF5FD),
                    ), border = BorderStroke(0.dp, Color(0xffcae1ff))
                ) {
                    Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
                        Icon(Icons.Filled.CalendarToday, contentDescription = "" , tint = DefaultBlue)
                        Text(
                            text = if (selectedStartDate.isEmpty()) outputDateStrToday else " $selectedStartDate",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontFamily = fontForDate,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Ends",
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontForDate,
                    color = DefaultBlue,
                    fontSize = 17.sp
                )

                OutlinedCard(
                    modifier = Modifier.clickable {
                        DatePickerDialog(
                            context,
                            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                                selectedEndDate =
                                    "$selectedDayOfMonth ${months[selectedMonth]} $selectedYear"
                            },
                            year,
                            month,
                            day
                        ).show()
                    }, colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ), border = BorderStroke(0.dp, Color(0xffcae1ff))
                ) {
                    Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
                        Icon(Icons.Filled.CalendarToday, contentDescription = "" , tint = DefaultBlue)
                        Text(
                            text = if (selectedEndDate.isEmpty()) outputDateStrToday else " $selectedEndDate",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontFamily = fontForDate,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                }
            }


        }


        Spacer(modifier = Modifier.height(30.dp))

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
                .size(80.dp)
                .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color(0xffcae1ff))
        ) {

            TextField(
                value = title, onValueChange = { title = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Type Something...")
                }
            )

        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Category", modifier = Modifier.padding(start = 30.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontForDate,
            color = DefaultBlue
        )


        Row {


            OutlinedCard(
                modifier = Modifier.weight(1f)
                    .size(100.dp)
                    .padding(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DefaultBlue,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color(0xffcae1ff))
            ){
                IconButton(onClick = { /*TODO*/ },modifier =Modifier.fillMaxSize()) {
                    Text(text = "Priority Task")
                }
            }

            OutlinedCard(
                modifier = Modifier.weight(1f)
                    .size(100.dp)
                    .padding(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = DefaultBlue
                ),
                border = BorderStroke(2.dp, Color(0xffcae1ff))
            ){
                IconButton(onClick = {  navController.navigate("AddDailyTask/$loggedInUserEmail") },modifier =Modifier.fillMaxSize()) {
                    Text(text = "Daily Task")
                }
            }



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
                    Text("")
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
            onClick = { /*TODO*/ },
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
                .padding(8.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = DefaultBlue,
                contentColor = Color.White,

                )
        ) {
            Text(
                text = "Create Task",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
        }

    }

}