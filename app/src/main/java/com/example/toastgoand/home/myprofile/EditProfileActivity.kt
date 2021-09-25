package com.example.toastgoand.home.myprofile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityEditProfileBinding

        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {

            val olddp = intent.getStringExtra("olddp")



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
                                .background(MaterialTheme.colors.background),
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
                                    color = MaterialTheme.colors.secondary,
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                )
                                Text(
                                    text = "Edit Profile",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.secondary,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                )
                            }
                            val painter = rememberImagePainter(data = olddp)
                            Image(
                                painter = painter,
                                contentDescription = "Forest Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(corner = CornerSize(50.dp)))

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
                                        backgroundColor = MaterialTheme.colors.secondary,
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
                                        color = MaterialTheme.colors.onSecondary
                                    )
                                }
                            }

                        }

                    }
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityEditProfileBinding.inflate(layoutInflater)
    }
}