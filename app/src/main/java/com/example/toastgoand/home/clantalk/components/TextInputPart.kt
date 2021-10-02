package com.example.toastgoand.home.clantalk.components

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
//import android.content.ClipboardManager
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.home.notifications.ChannelDataNewMessage
import com.example.toastgoand.home.notifications.NewMessageNotifPayloadDataClass
import com.example.toastgoand.home.notifications.NotificationDataNewMessage
import com.example.toastgoand.home.notifications.PayloadNewMessage
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import compose.icons.FeatherIcons
import compose.icons.feathericons.Send
import android.widget.Toast

import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.Color
import android.graphics.Color.GRAY
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.inflate
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.graphics.alpha
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.TalktypeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.BindingAdapter


@SuppressLint("ServiceCast", "ClickableViewAccessibility")
@Composable
fun TextInputPart(
    userid: String,
    channelid: String,
    defaultRecos: List<DefaultRecosDataClass>,
    clubName: String,
    directornot: Boolean,
    userdp: String
) {
    val typedText = remember { mutableStateOf(TextFieldValue()) }

    val pnConfiguration = PNConfiguration().apply {
        subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
        publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
        secure = true
        uuid = userid
    }

    Log.i("recos", defaultRecos.toString())

    val pubnub = PubNub(pnConfiguration)

    var selectedReco by remember { mutableStateOf("") }

    @Composable
    fun RecoImage(imageLink: String) {
        val painter = rememberImagePainter(data = imageLink)

        Image(
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(width = 100.dp, height = 50.dp)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .clickable {
                    selectedReco = imageLink
                }
        )
    }

    @Composable
    fun RecoImageSelected(imageLink: String) {
        val painter = rememberImagePainter(data = imageLink)

        Image(
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(width = 100.dp, height = 50.dp)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .clickable {
                    selectedReco = imageLink
                }
                .border(
                    width = 2.dp,
                    color = AyeTheme.colors.brandVariant,
                    shape = RoundedCornerShape(10.dp)
                )
        )
    }

    @Composable
    fun RecoOverlay(defaultRecos: List<DefaultRecosDataClass>) {

        Log.i("defaultrecos", defaultRecos.toString())

        Row(
            modifier = Modifier
                .background(AyeTheme.colors.uiBackground)
//                .horizontalScroll(enabled = true, state = rememberScrollState())
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (defaultRecos.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (selectedReco.length > 0) {
                        RecoImageSelected(selectedReco)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(enabled = true, state = rememberScrollState())
                    ) {
                        RecoImage(defaultRecos[0].links[0])
                        RecoImage(defaultRecos[0].links[1])
                        RecoImage(defaultRecos[0].links[2])
                        RecoImage(defaultRecos[0].links[3])
                        RecoImage(defaultRecos[0].links[4])
                        RecoImage(defaultRecos[0].links[5])
                        RecoImage(defaultRecos[0].links[6])
                    }
                }
            }
        }

    }

    val channelData1: ChannelDataNewMessage = ChannelDataNewMessage(channel = channelid)
    val notificationData1: NotificationDataNewMessage = NotificationDataNewMessage(
        title = clubName,
        body = "new message for you",
        sound = "default"
    )

    val p1: PayloadNewMessage =
        PayloadNewMessage(data = channelData1, notification = notificationData1)

    val payloadHere: NewMessageNotifPayloadDataClass = NewMessageNotifPayloadDataClass(pc_gcm = p1)

    val context = LocalContext.current

    var clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    Log.i("textinput", clipboard.getText().toString())

    var bindingOfTextEdit: EditText? = null

    @Composable
    fun AndroidViewBindingExample() {
        val context = LocalContext.current

        AndroidViewBinding(TalktypeBinding::inflate) {
            bindingOfTextEdit = textInputTalk
            textInputTalk.requestFocus()
        }
    }


    ProvideWindowInsets() {
        Column(modifier = Modifier.background(AyeTheme.colors.uiBackground)) {
            RecoOverlay(defaultRecos)
            Row(
                modifier = Modifier
                    .background(AyeTheme.colors.uiBackground)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .background(color = AyeTheme.colors.uiBackground),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AndroidViewBindingExample()
                }

                Icon(
                    FeatherIcons.Send,
                    "invite contacts to aye",
                    tint = AyeTheme.colors.iconVector,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            var messageFinal: String = bindingOfTextEdit?.text.toString()
                            pubnub
                                .publish(
                                    message = messageFinal,
                                    channel = channelid,
                                    meta = HMessageMetaDataClass(
                                        type = "h",
                                        image_url = selectedReco,
                                        user_dp = userdp
                                    )
                                )
                                .async { result, status ->
                                    if (status.error) {
                                        Log.i("messagesendingfail", status.toString())
                                    } else {
                                        pubnub
                                            .publish(
                                                message = payloadHere,
                                                channel = channelid + "_push"
                                            )
                                            .async { result, status ->
                                                if (!status.error) {
                                                    Log.i(
                                                        "messagesendingapicall notif",
                                                        "notif it worked"
                                                    )
                                                } else {
                                                    Log.i(
                                                        "messagesendingapicall notif",
                                                        status.toString()
                                                    )
                                                }
                                            }
                                        Log.i("messagesendingsuccess", result.toString())
                                    }
                                }

                        }
                )
            }
        }
    }

}


