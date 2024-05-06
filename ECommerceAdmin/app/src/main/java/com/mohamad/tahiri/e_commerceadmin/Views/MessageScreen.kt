package com.mohamad.tahiri.e_commerceadmin.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamad.tahiri.e_commerceadmin.R
import com.mohamad.tahiri.e_commerceadmin.Screen
import com.mohamad.tahiri.e_commerceadmin.Views.*
import com.mohamad.tahiri.e_commerceadmin.ui.theme.fontdekko

@Composable
fun MessageScreen(onNavigate: (Screen) -> Unit) {
    var message ="by"
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        TopAppBar(
            title = {
                Text(
                    "E-Commerce",
                    style = TextStyle(fontSize = 20.sp,
                        color = MaterialTheme.colors.background,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko),
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onNavigate(Screen.Home)
                }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
                }
            },
            modifier = Modifier
                .background(MaterialTheme.colors.background).padding(5.dp).clip(shape = RoundedCornerShape(5.dp)),
            elevation = 30.dp,
            backgroundColor = colorResource(id = R.color.yellow)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 0.85f, fill = true),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            reverseLayout = true
        ) {
            items((1..10).toList()) { message ->
                Card_Message(message)
            }
        }
        OutlinedTextField(
            value = message,
            onValueChange = {
                message = it
            },
            label = {
                Text(
                    "Type Your Message"
                )
            },
            maxLines = 1,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 1.dp)
                .fillMaxWidth()
                .weight(weight = 0.09f, fill = true),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send Button"
                    )
                }
            }
        )
    }
}

@Composable
fun Card_Message(message: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            text = message.toString(),
            textAlign =
            TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}
