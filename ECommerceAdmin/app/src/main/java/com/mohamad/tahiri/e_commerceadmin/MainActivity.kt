package com.mohamad.tahiri.e_commerceadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamad.tahiri.e_commerceadmin.Views.*
import com.mohamad.tahiri.e_commerceadmin.ui.theme.ECommerceAdminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceAdminTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen() {
    var screen by remember { mutableStateOf(Screen.Splash) }
    when (screen) {
        Screen.Splash -> SplashScreen(onNavigate = { screen = it })
        Screen.Home -> HomeScreen(onNavigate = { screen = it })
        Screen.Add -> AddScreen(onNavigate = { screen = it })
        Screen.Settings -> SettingScreen(onNavigate = { screen = it })
        Screen.MessageContact -> MessageContactScreen(onNavigate = { screen = it })
        Screen.Message -> MessageScreen(onNavigate = { screen = it })
    }
}
