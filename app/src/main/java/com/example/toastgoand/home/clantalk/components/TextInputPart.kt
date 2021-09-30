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
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.inflate
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.graphics.alpha
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.TalktypeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@SuppressLint("ServiceCast", "ClickableViewAccessibility")
@Composable
fun TextInputPart(
    userid: String,
    channelid: String,
    defaultRecos: List<DefaultRecosDataClass>,
    clubName: String,
    directornot: Boolean
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
                .clickable { Log.i("defaultreocs", "image selected") }
        )
    }

    @Composable
    fun RecoOverlay(defaultRecos: List<DefaultRecosDataClass>) {

        Log.i("defaultrecos", defaultRecos.toString())

        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .horizontalScroll(enabled = true, state = rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (defaultRecos.isNotEmpty()) {
                Row(modifier = Modifier.fillMaxWidth()) {
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

//    val clipboardManager: ClipboardManager = ClipboardManager
//    var x_here = clipboardManager.getText()

//    val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//    var clipData: ClipData


//    addPrimaryClipChangedListener(object : android.content.ClipboardManager.OnPrimaryClipChangedListener() {
//        fun onPrimaryClipChanged() {
//            val a: String = clipboard.getText().toString()
//            Toast.makeText(context, "Copy:\n$a", Toast.LENGTH_LONG).show()
//        }
//    })

    ProvideWindowInsets() {
        Column(
//            modifier = Modifier.navigationBarsWithImePadding()
        ) {
            RecoOverlay(defaultRecos)
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                SelectionContainer {
//                    OutlinedTextField(
//                        modifier = Modifier
//                            .onFocusChanged { focusState ->
//                                if (focusState.isFocused) {
//                                    val text = typedText.value.text
//                                    typedText.value = typedText.value.copy(
//                                        selection = TextRange(0, text.length)
//                                    )
//                                }
//                            }
////                    .focusRequester(
////                        focusTextInputRequester
////                    )
//                            .navigationBarsWithImePadding()
//                            .fillMaxWidth(0.9F),
//                        value = typedText.value,
//                        onValueChange = { typedText.value = it },
//                        placeholder = { Text(text = "type fun stuff...") },
//                        textStyle = MaterialTheme.typography.body2,
//                    )
//                }


//                AndroidView(
//                    factory = { cxt ->
//                        TextInputLayout(cxt).apply {
//                            id = "messageInputBar"
//                            boxBackgroundColor = Color.GRAY
//                            placeholderText = "type fun stuff"
//                        }
//                    }
//                )

                var typedTextEdit = remember { mutableStateOf("") }

                var textInputTextHere: TextInputLayout = TextInputLayout(context)

                val selectedReco: String = ""

                class SelectedTextCallBack : ActionMode.Callback {
                    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        removeItemIfNeed(menu, android.R.id.copy)
                        removeItemIfNeed(menu, android.R.id.cut)
                        return true
                    }

                    private fun removeItemIfNeed(menu: Menu?, id: Int) {
                        if (menu?.findItem(id) != null) {
                            menu.removeItem(id)
                        }
                    }

                    override fun onActionItemClicked(mode: ActionMode?, menu: MenuItem?) = false

                    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = false

                    override fun onDestroyActionMode(mode: ActionMode?) = Unit
                }


                AndroidViewBinding(TalktypeBinding::inflate) {
                    textInputTextHere = textInputTalk

                    textInputTalkEdit.setTextIsSelectable(true)

                    textInputTalkEdit.setOnTouchListener { view, motionEvent ->
                        Log.i("textinput touch", textInputTalkEdit.selectionStart.toString())
                        Log.i("textinput touch", textInputTalkEdit.selectionEnd.toString())
                        Log.i(
                            "textinput touch",
                            textInputTalkEdit.text.toString().slice(
                                textInputTalkEdit.selectionStart..
                                        textInputTalkEdit.selectionEnd
                            )
                        )
                        true
                    }

                    textInputTalkEdit.setOnClickListener {
                        Log.i("textinput", textInputTalkEdit.selectionStart.toString())
                        Log.i("textinput", textInputTalkEdit.selectionEnd.toString())
                        Log.i(
                            "textinput",
                            textInputTalkEdit.text.toString().slice(
                                textInputTalkEdit.selectionStart..
                                textInputTalkEdit.selectionEnd
                            )
                        )
                    }


//                    customInsertionActionModeCallback = object : ActionMode.Callback {
//                    }

//                    textInputTalkHere.setOnLongClickListener(object : View.OnLongClickListener {
//                        override fun onLongClick(v: View?): Boolean {
//                            Log.i("textinput", "text selected on long press")
//                            Toast.makeText(context, "selected", Toast.LENGTH_SHORT)
//                            return false
//                        }
//                    })

//                    textInputTalk.setBackgroundColor(Color.GRAY)
//                    typedTextEdit.value = textInputTalk.editText?.text.toString()
                }

                Icon(
                    imageVector = FeatherIcons.Send,
                    contentDescription = "last month",
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = AyeTheme.colors.brand)
                        .clickable {
                            Log.i("textinput", textInputTextHere.editText?.text.toString())
                            Log.i("textinput", "got clicked")
//                            pubnub
//                                .publish(
//                                    message = typedText.value.text,
//                                    channel = channelid
//                                )
//                                .async { result, status ->
//                                    if (status.error) {
//                                        Log.i("messagesendingfail", status.toString())
//                                    } else {
//                                        pubnub
//                                            .publish(
//                                                message = payloadHere,
//                                                channel = channelid + "_push"
//                                            )
//                                            .async { result, status ->
//                                                if (!status.error) {
//                                                    Log.i(
//                                                        "messagesendingapicall notif",
//                                                        "notif it worked"
//                                                    )
//                                                } else {
//                                                    Log.i(
//                                                        "messagesendingapicall notif",
//                                                        status.toString()
//                                                    )
//                                                }
//                                            }
//                                        Log.i("messagesendingsuccess", result.toString())
//                                    }
//                                }
                        }
                )
            }
        }
    }
}

