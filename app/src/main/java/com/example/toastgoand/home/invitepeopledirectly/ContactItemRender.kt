package com.example.toastgoand.home.invitepeopledirectly

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.invitepeopledirectly.network.ContactsListItemDataClass
import com.example.toastgoand.network.myfriends.MyFriendsDataClass

@Composable
fun ContactItemRender(
    item: ContactsListItemDataClass,
    addFunction: (selectedItem: ContactsListItemDataClass) -> Unit,
    removeFunction: (removedItem: ContactsListItemDataClass) -> Unit
) {
    val selected = remember { mutableStateOf(false) }

    AyeTheme() {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(0.9f)
                .clickable {
                    selected.value = (!selected.value)
                    if (!selected.value) {
                        addFunction(item)
                    } else {
                        removeFunction(item)
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = selected.value,
                onCheckedChange = {
                    selected.value = it
                    if (it) {
                        addFunction(item)
                    } else {
                        removeFunction(item)
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = AyeTheme.colors.appLeadVariant,
                    uncheckedColor = AyeTheme.colors.textSecondary.copy(0.5f),
                    checkmarkColor = AyeTheme.colors.uiBackground
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.subtitle2,
                    color = AyeTheme.colors.textPrimary
                )
                Text(
                    text = item.phone,
                    style = MaterialTheme.typography.caption,
                    color = AyeTheme.colors.textPrimary.copy(0.5f)
                )
            }
        }
    }
}