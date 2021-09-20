package com.example.toastgoand.home.clantalk.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import compose.icons.FeatherIcons
import compose.icons.feathericons.Send

@Composable
fun TextInputPart(userid: String, channelid: String, defaultRecos: List<DefaultRecosDataClass>) {
    val typedText = remember { mutableStateOf(TextFieldValue()) }

    val pnConfiguration = PNConfiguration().apply {
        subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
        publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
        secure = true
        uuid = userid
    }

    Log.i("recos", defaultRecos.toString())

    val pubnub = PubNub(pnConfiguration)

    @Composable
    fun RecoImage(imageLink: String) {
        val painter = rememberImagePainter(data = imageLink)
        Image(
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(width = 50.dp, height = 30.dp)
                .clip(RoundedCornerShape(corner = CornerSize(5.dp)))
        )
    }

    @Composable
    fun RecoOverlay(defaultRecos: List<DefaultRecosDataClass>) {

        Log.i("defaultrecos", defaultRecos.toString())

        Row(
            modifier = Modifier.background(MaterialTheme.colors.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (defaultRecos.isNotEmpty()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    RecoImage(defaultRecos[0].a)
                    RecoImage(defaultRecos[0].b)
                    RecoImage(defaultRecos[0].c)
                    RecoImage(defaultRecos[0].d)
                    RecoImage(defaultRecos[0].e)
                    RecoImage(defaultRecos[0].f)
                    RecoImage(defaultRecos[0].g)
                }
            }
        }

    }



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

