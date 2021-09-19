package com.example.toastgoand.home.myprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.toastgoand.uibits.SqaureRoundedIcon
import compose.icons.FeatherIcons
import compose.icons.feathericons.*

@Composable
fun ButtonsList(clubsNumber: String, framesNumber: String) {

    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OneButtonHereWithInfo("Clubs", FeatherIcons.Home, color = MaterialTheme.colors.primary, info = clubsNumber)
        OneButtonHereWithInfo("Frames", FeatherIcons.Layers, color = MaterialTheme.colors.primary, info = framesNumber)
        OneButtonHere("Edit Profile", FeatherIcons.Edit, color = MaterialTheme.colors.primary)
        OneButtonHere("Logout", FeatherIcons.LogOut, color = MaterialTheme.colors.primary)
    }

}

@Composable
fun OneButtonHere(title: String, icon: ImageVector, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(50.dp)
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SqaureRoundedIcon(icon, color = color)
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = color,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun OneButtonHereWithInfo(title: String, icon: ImageVector, color: Color, info: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(50.dp)
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            SqaureRoundedIcon(icon, color = color)
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
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}