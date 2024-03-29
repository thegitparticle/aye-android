package com.example.toastgoand.home.myprofile.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.myprofile.SettingsActivity
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import com.example.toastgoand.splash.SplashActivity
import com.example.toastgoand.uibits.SqaureRoundedIcon
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit3
import compose.icons.feathericons.Lock
import compose.icons.feathericons.LogOut

@Composable
fun SettingsButtons() {

    val context = LocalContext.current

    lateinit var prefHelper: PrefHelper

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonRowHere(
            title = "Privacy Policy",
            icon = FeatherIcons.Lock,
            color = AyeTheme.colors.textSecondary.copy(0.9f)
        )
        ButtonRowHere(
            title = "TnC",
            icon = FeatherIcons.Edit3,
            color = AyeTheme.colors.textSecondary.copy(0.9f)
        )
        ButtonRowHere(
            title = "Logout",
            icon = FeatherIcons.LogOut,
            color = AyeTheme.colors.error,
            onPressed = {

                val sharedPrefs = context.getSharedPreferences("sharedprefauth", Context.MODE_PRIVATE)
                var editor = sharedPrefs.edit()

                editor.clear()

                prefHelper = PrefHelper(context = context)
                prefHelper.put(Constant.PREF_IS_LOGIN, false)

                context.startActivity(
                    Intent(
                        context,
                        SplashActivity::class.java
                    ))
            }
        )
    }


}

@Composable
fun ButtonRowHere(title: String, icon: ImageVector, color: Color, onPressed: () -> Unit = { }) {
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