package com.example.toastgoand.home.startclan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight

@Composable
fun ContactsListPage(contactsList: String, backHandle: Unit, navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    AyeTheme() {
        ProvideWindowInsets() {
            Scaffold(
                topBar = {
                    HeaderOtherScreens(
                        modifier = Modifier.fillMaxWidth(),
                        title = "",
                        onBackIconPressed = { backHandle }
                    )
                },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navController.navigate("nameclan") },
                        modifier = Modifier
                            .padding(horizontal = 25.dp)
                            .size(60.dp),
                        backgroundColor = Color.Cyan,
                    ) {
                        Icon(
                            imageVector = FeatherIcons.ArrowRight,
                            contentDescription = "last month",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            ) { contentPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(AyeTheme.colors.uiBackground),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        modifier = Modifier
                            .background(MaterialTheme.colors.surface)
                            .clip(
                                RoundedCornerShape(corner = CornerSize(10.dp))
                            )
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(0.9f),
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        textStyle = MaterialTheme.typography.body2,
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.body2
                            )
                        },
                    )
                    Text("The textfield has this text: " + textState.value.text)
                    LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
//                                val contactsParsed = Gson().fromJson<List<ContactItem>>(
//                                    deetsHere.user.contact_list,
//                                    ContactItem::class.java
//                                )

//                                val simpleContactsString =
//                                    deetsHere.user.contact_list.drop(1).dropLast(1)

//                        if (contactsString.length > 10) {
//                            Log.i("invitepeople", contactsString?.slice(0..10))
////                                    val parserHere = Json {
////                                        isLenient = true; ignoreUnknownKeys = true; encodeDefaults =
////                                        false;
////                                    }
////                                    val contactsListHere =
////                                        parserHere.decodeFromString<ArrayList<ContactItem>>(
////                                            contactsString
////                                        )
////                                    decodeFromString<ArrayList<ContactItem>>(deetsHere.user.contact_list)
////                                    Log.i("invitepeople list", contactsListHere.toString())
//                        }
//
//                        Log.i("invitepeople", contactsString)
//
////                                items(
////                                    items = contactsListHere,
////                                    itemContent = {
////                                        ContactItemRender(Json.decodeFromString(it))
////                                    })
                    }
                }
            }
        }
    }
}