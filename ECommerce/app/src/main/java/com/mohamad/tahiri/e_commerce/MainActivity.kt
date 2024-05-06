package com.mohamad.tahiri.e_commerce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.mohamad.tahiri.e_commerce.View.*
import com.mohamad.tahiri.e_commerce.ui.theme.ECommerceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ECommerceTheme {
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
        Screen.Login -> LoginScreen(onNavigate = { screen = it })
        Screen.Settings -> SettingScreen(onNavigate = { screen = it })
        Screen.Message -> MessageScreen(onNavigate = { screen = it })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ECommerceTheme {
    }
}