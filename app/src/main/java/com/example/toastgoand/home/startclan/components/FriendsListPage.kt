package com.example.toastgoand.home.startclan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.toastgoand.home.clanhub.components.MyFriendItem
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight

@Composable
fun FriendsListPage(friendsList: List<MyFriendsDataClass>, backHandle: Unit) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    AyeTheme() {

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
                        onClick = {  },
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

                        items(
                            items = friendsList,
                            itemContent = {
                                MyFriendItem(it)
                            })
                    }
                }
        }
    }
}