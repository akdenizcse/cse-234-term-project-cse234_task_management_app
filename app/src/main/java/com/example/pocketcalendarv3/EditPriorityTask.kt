package com.example.pocketcalendarv3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.fontForDate
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

@Composable
fun EditPriorityTask(modifier: Modifier = Modifier, navController: NavController , loggedInUserEmail: String? , title: String?) {

    var name by remember {
        mutableStateOf("")
    }
    var title by remember {
        mutableStateOf(title?:"")
    }

    val db = Firebase.firestore



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

    var outputDateStrStart by remember { mutableStateOf("") }
    var outputDateStrEnd by remember { mutableStateOf("") }



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
                    LongTermTask(title, description, start, end,  arrayList, color, arrayListChecked)

                task = longTask


                val format = SimpleDateFormat("yyyy-MM-dd")
                val startDate = format.parse(start)
                val endDate = format.parse(end)


                outputDateStrStart = SimpleDateFormat("dd MMM yyyy").format(startDate!!)
                outputDateStrEnd = SimpleDateFormat("dd MMM yyyy").format(endDate!!)





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
                text = "Edit Task",
                modifier = Modifier
                    .wrapContentHeight(),
                fontSize = 20.sp,
                color = DefaultBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier.fillMaxWidth(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .wrapContentHeight(),
                fontSize = 32.sp,
                color = DefaultBlue,
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text(
                text = "Start", modifier = Modifier.padding(start = 30.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,
                color = DefaultBlue,
                fontSize = 17.sp
            )

            Text(
                text = "Ends", modifier = Modifier.padding(start = 160.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = fontForDate,
                color = DefaultBlue,
                fontSize = 17.sp
            )
        }





        Spacer(modifier = Modifier.height(10.dp))

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
                .size(75.dp)
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
                modifier = Modifier.fillMaxWidth()
            )

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
                    Text("Write about it...")
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
                .padding(7.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xffff3030),
                contentColor = Color.White,

                )
        ) {
            Text(
                text = "Remove Task",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold

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
                text = "Edit Task",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
        }






        
        
        
        
        
    }


}


