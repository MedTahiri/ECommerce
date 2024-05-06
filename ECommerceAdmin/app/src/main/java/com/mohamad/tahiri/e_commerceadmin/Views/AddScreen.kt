package com.mohamad.tahiri.e_commerceadmin.Views

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamad.tahiri.e_commerceadmin.R
import com.mohamad.tahiri.e_commerceadmin.Screen
import com.mohamad.tahiri.e_commerceadmin.bitmapToBase64
import com.mohamad.tahiri.e_commerceadmin.products
import com.mohamad.tahiri.e_commerceadmin.ui.theme.fontdekko

@Composable
fun AddScreen(
    onNavigate: (Screen) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    var title by remember { mutableStateOf("") }
    var Description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var productnum by remember { mutableStateOf("") }
    var image by remember {
        mutableStateOf("")
    }
    /*if (Constants.isEdit) {
        title = Constants.ProductEdi.title
        Description = Constants.ProductEdi.description
        price = Constants.ProductEdi.price
        productnum = Constants.ProductEdi.number_of_products
        image = Constants.ProductEdi.image
        bitmap.value = base64ToBitmap(image)
        imageUri = getImageUriFromBitmap(context,bitmap.value!!)
    }*/
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }
    Column(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .fillMaxSize()) {
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
            actions = {
                IconButton(onClick = {
                    openDialog.value = true
                }
                ) {
                    Icon(Icons.Filled.Done, contentDescription = "Done")
                }
            },
            backgroundColor = colorResource(id = R.color.yellow)
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
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
                        Card(shape = RoundedCornerShape(16.dp),
                            elevation = 30.dp,
                            modifier = Modifier
                                .align(Alignment.Center)
                        ) {
                            Image(bitmap = btm.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(350.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontdekko
                ),
                label = {
                    Text("Title", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko
                    ))
                },
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = Description,
                onValueChange = { Description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                label = {
                    Text("Description", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko
                    ))
                },
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontdekko
                ),
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = price,
                onValueChange = { price = it },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ), label = {
                    Text("Price", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko
                    ))
                },
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontdekko
                ),
                shape = RoundedCornerShape(16.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = productnum,
                onValueChange = { productnum = it },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ), label = {
                    Text("Number of products available", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko
                    ))
                },
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontdekko
                ),
                shape = RoundedCornerShape(16.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                launcher.launch("image/*")
            },
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                Text(text = "Pick image", style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontdekko
                ))
            }
        }
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Add Product", style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontdekko
                ))
            },
            text = {
                Text(
                    "Are you sure", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        val imagebitmap = bitmap.value
                        image = bitmapToBase64(imagebitmap!!).toString()
                        /* if (Constants.isEdit) {
                             homeScreenViewModel.addProduct(
                                 products(title,
                                     Description,
                                     price,
                                     productnum,
                                     image,
                                     Constants.ProductEdi.add_on),
                             )
                         } else {*/
                        homeScreenViewModel.addProduct(
                            products(title,
                                Description,
                                price,
                                productnum,
                                image,
                                System.currentTimeMillis().toString()),
                        )
                        /*}*/
                        onNavigate(Screen.Home)
                        //Constants.isEdit = false
                        //Constants.ProductEdi=products("","","","","","")
                    }
                ) {
                    Text("Confirm", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko
                    ))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onNavigate(Screen.Home)
                    }
                ) {
                    Text("Dismiss", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontdekko
                    ))
                }
            }, shape = RoundedCornerShape(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdd() {
    AddScreen(onNavigate = {})
}