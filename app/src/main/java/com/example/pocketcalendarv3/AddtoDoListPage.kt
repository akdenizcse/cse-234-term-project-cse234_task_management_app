package com.example.pocketcalendarv3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pocketcalendarv3.ui.theme.DefaultBlue
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun AddtoDoListPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    loggedInUserEmail: String?,
    title: String?
) {

    val db = Firebase.firestore

    val context = LocalContext.current

    var typeSomething by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(300.dp))



        TextField(
            value = typeSomething,
            onValueChange = { typeSomething = it },
            placeholder = { Text("Type something...") },
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
        )
        Button(onClick = {
            db.collection("longterm").whereEqualTo("title", title)
                .whereEqualTo("email", loggedInUserEmail).get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        db.collection("longterm").document(document.id).get()
                            .addOnSuccessListener { document ->
                                val arrayList = ArrayList<String>()
                                val toDoList = document.data?.get("toDoList") as List<*>

                                for (item in toDoList) {

                                    arrayList.add(item.toString())


                                }

                                arrayList.add(typeSomething)
                                db.collection("longterm").document(document.id)
                                    .update("toDoList", arrayList)


                                navController.popBackStack()

                            }

                    }
                }
        }


        ,


            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp)
            .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DefaultBlue)


        ) {
            Text(text = "Done", color = Color.White)
        }
        Button(
            onClick = { navController.popBackStack() }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DefaultBlue)


        ) {
            Text(text = "Cancel", color = Color.White)
        }

    }


}

