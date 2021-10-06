package com.example.toastgoand.home.aye

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityTheAyeBinding
import com.example.toastgoand.home.directs.network.NudgeToStartDirectApi
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.google.accompanist.insets.ProvideWindowInsets
import compose.icons.FeatherIcons
import compose.icons.feathericons.X
import kotlinx.coroutines.launch
import kotlin.random.Random

class TheAyeActivity : BaseActivity() {
    private lateinit var binding: ActivityTheAyeBinding

    private val viewModel: TheAyeViewModel by viewModels {
        TheAyeViewModelFactory(
            (this.application as ToastgoApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityTheAyeBinding

        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {
            AyeTheme() {
                val deetsHere: UserDetailsDataClass by viewModel.deets.observeAsState(
                    UserDetailsDataClass(
                        bio = "", image = "", user = User(
                            phone = "",
                            full_name = "",
                            id = 0,
                            clubs_joined_by_user = "",
                            number_of_clubs_joined = 0,
                            contact_list = "",
                            total_frames_participation = 0,
                            country_code_of_user = "",
                            contact_list_sync_status = false,
                            username = ""
                        ), id = 0
                    )
                )

                val composableScope = rememberCoroutineScope()

                ProvideWindowInsets() {
                    Scaffold(
                    ) { contentPadding ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            val painter = painterResource(id = R.drawable.aye_bg_1)
                            Image(
                                painter = painter,
                                contentDescription = "background image",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 0.dp, vertical = 50.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(70.dp)
                                            .background(AyeTheme.colors.iconBackground.copy(0.0f))
                                            .clickable(onClick = { onBackPressedHere() }),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(35.dp)
                                                .clip(CircleShape)
                                                .background(AyeTheme.colors.iconBackground),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                FeatherIcons.X,
                                                "circle icon",
                                                tint = AyeTheme.colors.iconVector,
                                                modifier = Modifier.size(17.dp),
                                            )
                                        }
                                    }
                                }
                                val painter = painterResource(id = R.drawable.aye_logo)
                                Image(
                                    painter = painter,
                                    contentDescription = "aye logo",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(200.dp)
                                )
                                Button(
                                    onClick = {
                                        val randomValue = Random.nextInt(1, 3)
                                        val teamuserid = if (randomValue == 1) {"81"} else {"82"}

                                        composableScope.launch {
                                            try {
                                                NudgeToStartDirectApi.retrofitService.startANewDirect(
                                                    mainuserid = deetsHere.user.id.toString(),
                                                    otheruserid = teamuserid,
                                                    directid = deetsHere.user.id.toString() + "_" + teamuserid + "_" + "d"
                                                )
                                            } catch (e: Exception) {
                                                Log.i("startdirect", e.toString())
                                            }
                                        }
                                        onBackPressed()
                                    },
                                    colors = ButtonDefaults.textButtonColors(
                                        backgroundColor = AyeTheme.colors.brand.copy(0.5f),
                                    ),
                                    shape = RoundedCornerShape(30.dp),
                                    modifier = Modifier
                                        .padding(vertical = 25.dp)
                                        .height(50.dp)
                                        .width(200.dp),
                                ) {
                                    Text(
                                        "talk to founder",
                                        style = MaterialTheme.typography.subtitle1,
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

    override fun binding(): ViewBinding {
        return ActivityTheAyeBinding.inflate(layoutInflater)
    }

    @Composable
    private fun CircleIconHere (onIconPressed: () -> Unit = { }, iconName: ImageVector, modifier: Modifier) {

        @Composable
        fun BackgroundPlusIcon (shape: Shape) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(shape)
                    .background(MaterialTheme.colors.background.copy(0.3f))
                    .clickable(onClick = onIconPressed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = iconName,
                    contentDescription = "last month",
                    modifier = Modifier.size(17.dp)
                )
            }
        }

        BackgroundPlusIcon(shape = CircleShape)
    }

}