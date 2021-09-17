package com.example.toastgoand.home.clantalk.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import compose.icons.FeatherIcons
import compose.icons.feathericons.Send

@Composable
fun TextInputPart (modifier: Modifier, userid: String, channelid: String, defaultRecos: List<DefaultRecosDataClass>) {
    val typedText = remember { mutableStateOf(TextFieldValue()) }

    val pnConfiguration = PNConfiguration().apply {
        subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
        publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
        secure = true
        uuid = userid
    }

    val pubnub = PubNub(pnConfiguration)

    Log.i("current_typing", typedText.value.text)

    ProvideWindowInsets() {
        Column {
           RecoOverlay(defaultRecos)
            Row(
                modifier = Modifier.background(MaterialTheme.colors.surface),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
//                    .focusRequester(
//                        focusTextInputRequester
//                    )
                        .navigationBarsWithImePadding()
                        .fillMaxWidth(0.9F),
                    value = typedText.value,
                    onValueChange = { typedText.value = it },
                    placeholder = { Text(text = "type fun stuff...") },
                )
                Icon(
                    imageVector = FeatherIcons.Send,
                    contentDescription = "last month",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            pubnub
                                .publish(
                                    message = typedText.value.text,
                                    channel = channelid
                                )
                                .async { result, status ->
                                    if (status.error) {
                                        Log.i("messagesendingfail", status.toString())
                                    } else {
                                        Log.i("messagesendingsuccess", result.toString())
                                    }
                                }
                        }
                )
            }   
        }
    }
}

