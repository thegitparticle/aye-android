package com.example.toastgoand.home.invitepeopledirectly

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme

@Composable
fun ContactItemRender (item: InvitePeopleDirectlyActivity.ContactItem) {
    val selected = remember { mutableStateOf(false) }

    AyeTheme() {
        Row (modifier = Modifier.padding(horizontal = 20.dp)) {
            Checkbox(
                checked = selected.value,
                onCheckedChange = { selected.value = it }
            )
            Column(modifier = Modifier.padding(horizontal = 10.dp), verticalArrangement = Arrangement.Center) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    text = item.phone,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground.copy(0.5f)
                )
            }
        }
    }
}