package com.example.toastgoand.home.myprofile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.viewbinding.ViewBinding
import coil.compose.rememberImagePainter
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityEditProfileBinding
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.example.toastgoand.uibits.SqaureRoundedIcon
import com.google.accompanist.insets.ProvideWindowInsets
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit

class EditProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityEditProfileBinding

        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {

            imageUri = intent.getStringExtra("olddp")?.toUri()

            AyeTheme {
                ProvideWindowInsets() {
                    Scaffold(
                        topBar = {
                            HeaderOtherScreens(
                                modifier = Modifier.fillMaxWidth(),
                                title = "",
                                onBackIconPressed = { onBackPressedHere() }
                            )
                        }
                    ) { contentPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(AyeTheme.colors.uiBackground),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(100.dp)
                                    .padding(top = 10.dp, bottom = 30.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                SqaureRoundedIcon(
                                    FeatherIcons.Edit,
                                    color = AyeTheme.colors.success,
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                )
                                Text(
                                    text = "Edit Profile",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = AyeTheme.colors.success,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                )
                            }
                            val painter = rememberImagePainter(data = imageUri)
                            Image(
                                painter = painter,
                                contentDescription = "Forest Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(corner = CornerSize(50.dp)))
                                    .clickable {
                                        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                                        startActivityForResult(gallery, pickImage)
                                    }

                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = {
//                                        context.startActivity(
//                                            Intent(
//                                                context,
//                                                StartClanActivity::class.java
//                                            ).apply { })
                                    },
                                    colors = ButtonDefaults.textButtonColors(
                                        backgroundColor = AyeTheme.colors.success,
                                    ),
                                    shape = RoundedCornerShape(30.dp),
                                    modifier = Modifier
                                        .padding(vertical = 30.dp)
                                        .height(60.dp)
                                        .width(160.dp),
                                ) {
                                    Text(
                                        "save changes",
                                        style = MaterialTheme.typography.body1,
                                        color = AyeTheme.colors.uiBackground
                                    )
                                }
                            }

                        }

                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            Log.i("editprofilelogs", imageUri.toString())
        }
    }

    override fun binding(): ViewBinding {
        return ActivityEditProfileBinding.inflate(layoutInflater)
    }
}