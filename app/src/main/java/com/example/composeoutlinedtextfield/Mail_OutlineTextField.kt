package com.example.composeoutlinedtextfield

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextOutPut() {

    var inputOutlineText by remember { mutableStateOf("") }
    var charCount by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(8.dp))
    {
        OutlinedTextField(
            value = inputOutlineText, onValueChange = { inputOutlineText = it },
            modifier = Modifier.padding(55.dp, 25.dp, 55.dp, 0.dp),
            shape = RoundedCornerShape(16.dp),
            textStyle = TextStyle(fontSize = 20.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue, // цвет при получении фокуса
                unfocusedBorderColor = Color.LightGray  // цвет при отсутствии фокуса
            ),
            label = { Text(text = "@mail.ru", color = Color.Blue, fontSize = 18.sp) },
            placeholder = { Text(text = "Enter you Email") },
            leadingIcon = {
                Icon(Icons.Filled.Email, contentDescription = "Mail", tint = Color.Blue)
            },
            trailingIcon = {
                if (inputOutlineText.isNotEmpty()) {
                    IconButton(onClick = { inputOutlineText = "" }) {
                        Icon(Icons.Filled.Close, contentDescription = "Close", tint = Color.Red)
                    }
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

            isError = when {
                inputOutlineText.endsWith("@mail.ru") -> false
                inputOutlineText.length in 1..15 -> true
                inputOutlineText.length > 15 -> true
                else -> false
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            charCount = inputOutlineText.length
            when {
                inputOutlineText.isEmpty() -> CorrectInput(
                    text = "Field cannot be empty",
                    Color.Blue
                )
                inputOutlineText.length in 1 until 15 -> CorrectInput(
                    text = "You must enter 7 characters +@mail.ru  to enter your account",
                    Color.Unspecified
                )
                inputOutlineText.endsWith("@mail.ru") -> CorrectInput(
                    text = "Data correctly entered",//"Not correct log in.Login must start with a character '@'"
                    colors.primaryVariant

                )
                inputOutlineText.length > 15 -> CorrectInput(
                    text = "The entered data is more than 15, delete the excess"
                )
                else -> CorrectInput(
                    text = "Data not entered correctly",
                    colors.error
                )
            }
            Text(
                text = "$charCount /15",

                )
        }
    }
}

@Composable
fun CorrectInput(text: String, color: Color = MaterialTheme.colors.error) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.caption,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
            .padding(end = 10.dp),
        fontSize = 14.sp
    )
}