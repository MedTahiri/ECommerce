package com.mohamad.tahiri.e_commerceadmin.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamad.tahiri.e_commerceadmin.R
import com.mohamad.tahiri.e_commerceadmin.Screen
import com.mohamad.tahiri.e_commerceadmin.ui.theme.fontdekko

@Composable
fun MessageContactScreen(onNavigate: (Screen) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
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
        LazyColumn {
            items((1..10).toList()) {
                Card_Contact(it)
            }
        }
    }
}

@Composable
fun Card_Contact(i: Int) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageContact() {
    MessageContactScreen(onNavigate = {})
}