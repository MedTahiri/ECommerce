package com.mohamad.tahiri.e_commerce.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamad.tahiri.e_commerce.R
import com.mohamad.tahiri.e_commerce.Screen
import com.mohamad.tahiri.e_commerce.base64ToBitmap
import com.mohamad.tahiri.e_commerce.products
import com.mohamad.tahiri.e_commerce.ui.theme.ECommerceTheme
import com.mohamad.tahiri.e_commerce.ui.theme.fontdekko
import com.mohamad.tahiri.e_commerceadmin.Views.HomeScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onNavigate: (Screen) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
) {

    val products: List<products> by homeScreenViewModel.products.observeAsState(
        initial = emptyList<products>().toMutableList()
    )
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var search by remember {
        mutableStateOf(false)
    }
    ECommerceTheme() {
        ModalBottomSheetLayout(sheetContent = {

            Card(modifier = Modifier.padding(10.dp)) {
                Column() {
                    ListItem(
                        text = {
                            Text("Settings", style = androidx.compose.ui.text.TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontdekko
                            ))
                        },
                        icon = {
                            Icon(Icons.Filled.Settings, contentDescription = "Settings")
                        }, modifier = Modifier.clickable { onNavigate(Screen.Settings) })
                    ListItem(
                        text = {
                            Text("Messages", style = androidx.compose.ui.text.TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontdekko
                            ))
                        },
                        icon = {
                            Icon(Icons.Filled.Email, contentDescription = "Email")
                        }, modifier = Modifier.clickable { onNavigate(Screen.Message) })
                }
            }
        }, sheetState = state) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)) {

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
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .padding(5.dp)
                            .clip(shape = RoundedCornerShape(5.dp)),
                        elevation = 30.dp,
                        actions = {
                            IconButton(onClick = {
                                search = !search
                            }

                            ) {
                                if (!search) {
                                    Icon(Icons.Filled.Search, contentDescription = "Search")
                                } else {
                                    Icon(Icons.Filled.Clear, contentDescription = "Search")
                                }

                            }
                            IconButton(onClick = {
                                scope.launch { state.show() }
                            }
                            ) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        },
                        backgroundColor = colorResource(id = R.color.blue)
                    )
                    AnimatedVisibility(visible = search) {
                        searchview()
                    }
                    if (products.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "No Products",
                                modifier = Modifier.align(Alignment.Center),
                                textAlign = TextAlign.Center,

                                style = TextStyle(fontSize = 30.sp,
                                    color = colorResource(id = R.color.blue),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = fontdekko))
                        }
                    } else {

                        LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
                            items(products){
                                Card_Item(it, onNavigate)

                            }
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun Card_Item(
    product: products,
    onNavigate: (Screen) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
) {
    var openDialogViewData by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .padding(10.dp)
        .combinedClickable(onClick = { openDialogViewData = true })) {
        Card(shape = RoundedCornerShape(16.dp),
            elevation = 30.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                base64ToBitmap(product.image)?.let {
                    Image(bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Text(product.title, style = androidx.compose.ui.text.TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontFamily = fontdekko
        ), modifier = Modifier
            .fillMaxWidth()
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Text("${product.number_of_products} An item",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp, fontFamily = fontdekko
                ),
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text("${product.price} $",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp, fontFamily = fontdekko
                ),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
    if (openDialogViewData) {
        Dialog(onDismissRequest = { openDialogViewData = false }) {
            Card(shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .padding(10.dp)
                ) {
                    Card(shape = RoundedCornerShape(16.dp),
                        elevation = 30.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(350.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            base64ToBitmap(product.image)?.let {
                                Image(bitmap = it.asImageBitmap(),
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(product.title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp, fontFamily = fontdekko
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text("${product.number_of_products} An item",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp, fontFamily = fontdekko
                            ),
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                        Text("${product.price} $",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp, fontFamily = fontdekko
                            ),
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Text("${product.description}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp, fontFamily = fontdekko
                        ))
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)) {
                        Text(text = "${product.price}$", style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontdekko
                        ))
                    }
                }
            }
        }
    }
}

@Composable
fun searchview(homeScreenViewModel: HomeScreenViewModel = viewModel()) {
    var query: String by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = { onQueryChanged ->
            query = onQueryChanged
            homeScreenViewModel.SearchProduct(query)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (true) {
                IconButton(onClick = {
                    query = ""
                    homeScreenViewModel.getProducts()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Clear icon"
                    )
                }
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = {
            Text(text = "search", style = androidx.compose.ui.text.TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = fontdekko
            ))
        },
        textStyle = androidx.compose.ui.text.TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = fontdekko
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RoundedCornerShape(16.dp))
            .padding(10.dp)
    )
}
