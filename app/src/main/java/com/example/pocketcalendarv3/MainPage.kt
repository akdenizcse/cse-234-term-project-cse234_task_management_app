package com.example.pocketcalendarv3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPage(navController: NavController , loggedInUserEmail : String?) {
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
    }
}