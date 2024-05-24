package com.example.pocketcalendarv3

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.example.pocketcalendarv3.ui.theme.TextFieldGray
import com.example.pocketcalendarv3.ui.theme.fontFamily
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun RegisterPageView(modifier: Modifier = Modifier, navController: NavController){

    val db = Firebase.firestore

    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }
    var registerEmail by remember {
        mutableStateOf("")
    }
    var registerPassword by remember {
        mutableStateOf("")
    }
    var registerPasswordAgain by remember {
        mutableStateOf("")
    }


    val user = hashMapOf(
        "username" to username,
        "email" to registerEmail,
        "password" to registerPassword,
    )




    Row(modifier = Modifier.height(32.dp)) {
        IconButton(onClick = { navController.popBackStack() }) {
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
                text = "-Create your account-",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )

        }
        TextField(
            value = username, onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            label = { Text(text = "Username", color = TextFieldGray) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "",
                    tint = DefaultBlue
                )
            },


            )

        TextField(
            value = registerEmail, onValueChange = { registerEmail = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
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

        TextField(
            value = registerPassword, onValueChange = { registerPassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),


            label = { Text(text = "Password", color = TextFieldGray) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "", tint = DefaultBlue) },


            )
        TextField(
            value = registerPasswordAgain, onValueChange = { registerPasswordAgain = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(BorderStroke(1.dp, DefaultBlue), RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),


            label = { Text(text = "Confirm Password", color = TextFieldGray) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "", tint = DefaultBlue) },


            )

        FilledTonalButton(
            onClick = {
                if(username =="")
                    Toast.makeText(context, "Username can not be null", Toast.LENGTH_SHORT).show()
                else if(registerEmail=="")
                    Toast.makeText(context, "Email can not be null", Toast.LENGTH_SHORT).show()
                else if (registerPassword=="")
                    Toast.makeText(context, "Password can not be null", Toast.LENGTH_SHORT).show()
                else{
                    db.collection("users").whereEqualTo("email", registerEmail)
                        .get()
                        .addOnSuccessListener { documents ->

                            if (!documents.isEmpty) {
                                Toast.makeText(context, "Email is taken", Toast.LENGTH_SHORT).show()


                            } else {
                                if (registerPassword != registerPasswordAgain) {
                                    registerPasswordAgain = ""

                                    Toast.makeText(
                                        context,
                                        "Passwords do not match.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    db.collection("users")
                                        .add(user)
                                        .addOnSuccessListener {
                                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT)
                                                .show()
                                            registerEmail = ""
                                            registerPassword = ""
                                            registerPasswordAgain = ""
                                            username = ""

                                            navController.popBackStack()
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(
                                                context,
                                                "Something went wrong.",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                            registerEmail = ""
                                            registerPassword = ""
                                            registerPasswordAgain = ""
                                            username = ""
                                        }
                                }
                            }


                        }.addOnFailureListener { e ->
                            Log.w("TAG", "Error reading data", e)
                        }
                }



            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
                .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DefaultBlue)


        ){
            Text(text = "Register", color = Color.White)
        }
    }
}