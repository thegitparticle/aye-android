package com.example.toastgoand.home.myprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.uibits.SqaureRoundedIcon
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit
import compose.icons.feathericons.Home
import compose.icons.feathericons.Layers
import compose.icons.feathericons.Settings

@Composable
fun ButtonsList(
    clubsNumber: String,
    framesNumber: String,
    editOnPressed: () -> Unit = { },
    settingsOnPressed: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OneButtonHereWithInfo(
            "Clans",
            "you are in ${clubsNumber} clans",
            FeatherIcons.Home,
            color = AyeTheme.colors.brandVariant,
            info = clubsNumber,
        )
        Spacer(modifier = Modifier.size(20.dp))
        OneButtonHereWithInfo(
            "Frames",
            "you have participated in ${framesNumber} frames",
            FeatherIcons.Layers,
            color = AyeTheme.colors.appLead,
            info = framesNumber
        )
        Spacer(modifier = Modifier.size(20.dp))
        OneButtonHere(
            "Edit Profile",
            FeatherIcons.Edit,
            color = AyeTheme.colors.success,
            onPressed = { editOnPressed() }
        )
        Spacer(modifier = Modifier.size(20.dp))
        OneButtonHere(
            "Settings",
            FeatherIcons.Settings,
            color = AyeTheme.colors.error,
            onPressed = { settingsOnPressed() }
        )
    }

}

@Composable
fun OneButtonHere(title: String, icon: ImageVector, color: Color, onPressed: () -> Unit = { }) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(60.dp)
            .padding(vertical = 5.dp)
            .clickable { onPressed() }
            .clip(RoundedCornerShape(10.dp)),
        backgroundColor = AyeTheme.colors.uiSurface,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SqaureRoundedIcon(icon, color = color, modifier = Modifier)
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = color,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun OneButtonHereWithInfo(
    title: String,
    dialogText: String,
    icon: ImageVector,
    color: Color,
    info: String,
    onPressed: () -> Unit = { }
) {
    val openDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(60.dp)
            .padding(vertical = 5.dp)
            .clickable { onPressed(); openDialog.value = true }
            .clip(RoundedCornerShape(10.dp)),
        backgroundColor = AyeTheme.colors.uiSurface,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column() {
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(
                            text = dialogText,
                            color = AyeTheme.colors.textSecondary,
                            style = MaterialTheme.typography.subtitle1
                        )
                    },
                    confirmButton = {
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .background(AyeTheme.colors.uiSurface)
                                .clickable {
                                    openDialog.value = false
                                }
                                .width(100.dp)
                                .height(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ok",
                                style = MaterialTheme.typography.subtitle2,
                                color = AyeTheme.colors.textSecondary
                            )
                        }
                    },
                    dismissButton = {
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .background(AyeTheme.colors.uiSurface.copy(0.0f))
                                .clickable {
                                    openDialog.value = false
                                }
                                .width(100.dp)
                                .height(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "no.. no..",
                                style = MaterialTheme.typography.subtitle2,
                                color = AyeTheme.colors.textSecondary.copy(0.0f)
                            )
                        }
                    },
                    backgroundColor = AyeTheme.colors.uiBackground,
                    contentColor = AyeTheme.colors.textSecondary
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SqaureRoundedIcon(
                    icon,
                    color = color,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    color = color,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            Text(
                text = info,
                style = MaterialTheme.typography.subtitle1,
                color = AyeTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }
}