package com.mohamad.tahiri.e_commerce.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamad.tahiri.e_commerce.Screen
import com.mohamad.tahiri.e_commerce.ui.theme.fontdekko
import com.mohamad.tahiri.e_commerce.R

@Composable
fun SettingScreen(onNavigate: (Screen) -> Unit) {
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
            backgroundColor = colorResource(id = R.color.blue)
        )
    }
}