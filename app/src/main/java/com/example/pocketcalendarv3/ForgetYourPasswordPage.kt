package com.example.pocketcalendarv3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.R
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import com.example.pocketcalendarv3.ui.theme.fontFamily


@Composable
fun ForgetYourPasswordView(modifier: Modifier = Modifier, navController: NavController) {
    var forgetYourPasswordEmail by remember {
        mutableStateOf("")
    }
    Row (modifier = Modifier.height(32.dp)){
        IconButton(onClick = { navController.popBackStack() },) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "")
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()

            .padding(PaddingValues(top = 144.dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Text(
            text = "POCKET-CALANDER",
            style = TextStyle(
                color = DefaultBlue,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily

            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Task Managament App",
            fontFamily = FontFamily.Default,
            color = Color(0xFF9A9A9A),
            fontWeight = FontWeight.Medium,


            )
        Row(modifier = Modifier.padding(PaddingValues(top = 72.dp))) {
            Text(
                text = "-Forget your password?-",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )

        }

        TextField(
            value = forgetYourPasswordEmail, onValueChange = { forgetYourPasswordEmail = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            label = { Text(text = "Email", color = TextFieldGray) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = "",
                    tint = DefaultBlue
                )
            },


            )



        FilledTonalButton(
            onClick = { /*TODO: Kullanıcı bilgileri kontrol edilip ona göre yönlendirme işlemi yapılacak*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DefaultBlue)


        ) {
            Text(text = "SendCode")
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(id = R.drawable.logo_foreground),
                contentDescription = "",
                alignment = Alignment.BottomCenter,
                modifier = Modifier.clip(CircleShape)
            )
        }

    }

}