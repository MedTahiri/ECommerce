package com.mohamad.tahiri.e_commerce.View

import android.os.Handler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamad.tahiri.e_commerce.R
import com.mohamad.tahiri.e_commerce.Screen
import com.mohamad.tahiri.e_commerce.ui.theme.ECommerceTheme
import com.mohamad.tahiri.e_commerce.ui.theme.fontdekko
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: (Screen) -> Unit) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.blue))) {

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = colorResource(id = R.color.white))) {
                append("E-")
            }
            append("Commerce")
        },
            textAlign = TextAlign.Center,

            style = TextStyle(fontSize = 60.sp, color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold, fontFamily = fontdekko),
            modifier = Modifier.align(Alignment.Center))

        val circles = listOf(
            remember {
                Animatable(initialValue = 0.3f)
            },
            remember {
                Animatable(initialValue = 0.3f)
            },
            remember {
                Animatable(initialValue = 0.3f)
            }
        )
        circles.forEachIndexed { index, animatable ->
            LaunchedEffect(Unit) {
                delay(timeMillis = (400 / circles.size).toLong() * index)
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 400
                        ),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            circles.forEachIndexed { index, animatable ->
                if (index != 0) {
                    Spacer(modifier = Modifier.width(width = 6.dp))
                }
                Box(
                    modifier = Modifier
                        .size(size = 15.dp)
                        .clip(shape = CircleShape)
                        .background(
                            color = colorResource(id = R.color.blue)
                                .copy(alpha = animatable.value)
                        )
                ) {
                }
            }
        }
    }
    Handler().postDelayed({
        onNavigate(Screen.Login)
    }, 3000)
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ECommerceTheme {
        SplashScreen(onNavigate = {})
    }
}