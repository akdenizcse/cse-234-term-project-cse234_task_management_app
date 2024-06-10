package com.example.pocketcalendarv3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pocketcalendarv3.ui.theme.CloudBlue
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import java.util.Calendar

@Composable
fun EditDailyTaskPage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedDate by remember { mutableStateOf("") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val months = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "June",
        "July", "Aug", "Sep", "Oct", "Nov", "Dec")


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
        }
        Column(
            modifier = Modifier
                .padding(16.dp),

        ) {
            ElevatedButton(modifier=Modifier, colors = ButtonDefaults.buttonColors(TextFieldGray),onClick = {
                DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    selectedDate = "${months[selectedMonth]}-$selectedDayOfMonth-$selectedYear"
                }, year, month, day).show()
            }) {

                Text(text = if (selectedDate.isEmpty()) "Pick a Date" else " $selectedDate")
            }
        }
    }

}




