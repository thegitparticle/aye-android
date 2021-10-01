package com.example.toastgoand.home.clanhub.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanhub.ClanMember

@Composable
fun UsersListItem(user: ClanMember, removeUser: () -> Unit = { }) {
    AyeTheme() {
        val openOptionsDialog = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.clickable { openOptionsDialog.value = true }.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserDP(user.display_pic)
            Column(Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black
                )
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray
                )
            }
        }

        if (openOptionsDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openOptionsDialog.value = false
                },
                title = {
                    Text(text = "", style = MaterialTheme.typography.body2,
                        color = AyeTheme.colors.textPrimary)
                },
                buttons = {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.8f)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = removeUser
                        ) {
                            Text(
                                text = "remove from clan",
                                style = MaterialTheme.typography.subtitle1,
                                color = AyeTheme.colors.error
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.3f)
                    .background(color = AyeTheme.colors.uiBackground),
                backgroundColor = AyeTheme.colors.uiBackground
            )
        }
    }
}

@Composable
fun UserDP(dp: String) {
    val painter = rememberImagePainter(data = dp)
    Image(
        painter = painter,
        contentDescription = "clan hub dp thumbnail",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(25.dp)))
    )
}