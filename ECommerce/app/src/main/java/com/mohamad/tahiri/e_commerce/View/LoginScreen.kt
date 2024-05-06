package com.mohamad.tahiri.e_commerce.View

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamad.tahiri.e_commerce.Screen
import com.mohamad.tahiri.e_commerce.User
import com.mohamad.tahiri.e_commerce.bitmapToBase64
import com.mohamad.tahiri.e_commerce.ui.theme.ECommerceTheme
import com.mohamad.tahiri.e_commerce.ui.theme.Purple700

@Composable
fun LoginScreen(onNavigate: (Screen) -> Unit) {
    val context = LocalContext.current
    var InorUp by remember { mutableStateOf(true) }
    var text by remember { mutableStateOf("Sign up") }
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("$text here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = {
                InorUp = !InorUp
                if (InorUp) {
                    text = "Sign up"
                } else {
                    text = "Sign in"
                }
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    if (InorUp) {
        SignInPage(onNavigate)
    } else {
        SignUpPage(onNavigate)
    }
}

@Composable
fun SignInPage(onNavigate: (Screen) -> Unit) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val gmail = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Gmail") },
            value = gmail.value,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { gmail.value = it })

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Password") },
            value = password.value,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { onNavigate(Screen.Home) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }
}

@Composable
fun SignUpPage(
    onNavigate: (Screen) -> Unit,
    loginScreenViewModel: LoginScreenViewModel = viewModel(),
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val bitmap = remember {
            mutableStateOf<Bitmap?>(null)
        }
        val launcher = rememberLauncherForActivityResult(contract =
        ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val gmail = remember { mutableStateOf("") }
        val cpassword = remember { mutableStateOf("") }
        var image by remember { mutableStateOf("") }

        Text(text = "Create account",
            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))
        Spacer(modifier = Modifier.height(20.dp))
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Card(shape = RoundedCornerShape(20.dp),
                    elevation = 10.dp,
                    modifier = Modifier
                    //.align(Alignment.Center)
                ) {
                    Image(bitmap = btm.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Username") },
            value = username.value,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Gmail") },
            value = gmail.value,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { gmail.value = it })

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Password") },
            value = password.value,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Confirm your Password") },
            value = cpassword.value,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { cpassword.value = it })
        Spacer(modifier = Modifier.height(5.dp))
        ClickableText(
            text = AnnotatedString("Pick Image"),
            onClick = { launcher.launch("image/*") },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    val imagebitmap = bitmap.value
                    image = bitmapToBase64(imagebitmap!!).toString()
                    if (password.value == cpassword.value) {
                        loginScreenViewModel.addUser(User(username.value.toString(),
                            gmail.value.toString(),
                            password.value.toString(),
                            image.toString(),
                            System.currentTimeMillis().toString()))
                        onNavigate(Screen.Home)
                    } else {
                        password.value = ""
                        cpassword.value = ""
                    }
                    //loginScreenViewModel.addUser(User("hi","hi","hi","hi","hi"))
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Create account")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ECommerceTheme {
        LoginScreen(onNavigate = {})
    }
}