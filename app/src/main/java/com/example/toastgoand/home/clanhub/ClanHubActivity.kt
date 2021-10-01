package com.example.toastgoand.home.clanhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanHubBinding
import com.example.toastgoand.home.clanhub.clanaddpeople.ClanAddPeopleActivity
import com.example.toastgoand.home.clanhub.components.ClanMetrics
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.clanhub.network.QuitClanApi
import com.example.toastgoand.home.clans.ClansViewModel
import com.example.toastgoand.home.clans.ClansViewModelFactory
import com.example.toastgoand.home.invitepeopledirectly.InvitePeopleDirectlyActivity
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.launch

class ClanHubActivity : BaseActivity() {

    private lateinit var binding: ActivityClanHubBinding

    private val viewModel: ClanHubViewModel by viewModels {
        ClanHubViewModelFactory(
            (this.getApplication() as ToastgoApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanHubBinding

        val clubName = intent.getStringExtra("clubName")
        val clubid = intent.getIntExtra("clubid", 0)
        val channelid = intent.getStringExtra("channelid")
        val ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
        val startTime = intent.getStringExtra("startTime")
        val endTime = intent.getStringExtra("endTime")

        viewModel.getClanDetailsHere(clubid)


        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {
            AyeTheme {
                val context = LocalContext.current

                val clanDeets: ClanDetailsDataClass by viewModel.clubDetails.observeAsState(
                    ClanDetailsDataClass(
                        id = 0,
                        name = "",
                        member_count = 0,
                        date_created = "2021-07-14T22:45:43+05:30",
                        profile_picture = "https://aye-media-bucket.s3.amazonaws.com/media/club_images/breakingbad_2.jpg",
                        frames_total = 16,
                        members = "",
                        admin_leader = "",
                        users = listOf(
                            ClanMember(
                                user_id = 0,
                                username = "",
                                name = "",
                                display_pic = ""
                            )
                        )
                    )
                )

                val composableScope = rememberCoroutineScope()

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
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize()
                                .background(AyeTheme.colors.uiSurface),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                Spacer(modifier = Modifier.size(30.dp))
                            }
                            item {
                                ClanMetrics(clanDeets)
                            }
                            item {
                                Spacer(modifier = Modifier.size(30.dp))
                            }
                            val members = clanDeets.users
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .clip(RoundedCornerShape(10.dp)),
                                    backgroundColor = AyeTheme.colors.uiBackground,
                                    elevation = 10.dp
                                ) {
                                    Column(
                                        modifier = Modifier.padding(15.dp)
                                    ) {
                                        for (item in members) {
                                            UsersListItem(user = item)
                                        }
                                    }
                                }
                            }
                            item {
                                Row(
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Button(
                                        onClick = {
                                            val intent = Intent(
                                                context,
                                                ClanAddPeopleActivity::class.java
                                            ).apply {
                                                putExtra("clubid", clubid)
                                                putExtra ("clubname", clubName)
                                            }
                                            startActivity(intent)
                                            overridePendingTransition(
                                                R.anim.slide_up_enter,
                                                R.anim.slide_down_exit
                                            )
                                        },
                                        colors = ButtonDefaults.textButtonColors(
                                            backgroundColor = AyeTheme.colors.appLead,
                                        ),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .padding(vertical = 25.dp)
                                            .height(40.dp)
                                            .width(160.dp),
                                    ) {
                                        Text(
                                            "add friends",
                                            color = AyeTheme.colors.uiBackground,
                                            style = MaterialTheme.typography.caption,
                                        )
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            val intent = Intent(
                                                context,
                                                InvitePeopleDirectlyActivity::class.java
                                            ).apply {}
                                            startActivity(intent)
                                            overridePendingTransition(
                                                R.anim.slide_up_enter,
                                                R.anim.slide_down_exit
                                            )
                                        },
                                        colors = ButtonDefaults.textButtonColors(
                                        ),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .padding(vertical = 25.dp)
                                            .height(40.dp)
                                            .width(160.dp)
                                            .border(
                                                1.dp,
                                                AyeTheme.colors.appLead,
                                                shape = RoundedCornerShape(10.dp)
                                            ),
                                    ) {
                                        Text(
                                            "invite friends",
                                            color = AyeTheme.colors.appLead,
                                            style = MaterialTheme.typography.caption,
                                        )
                                    }
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.size(30.dp))
                            }
                            item {
                                OutlinedButton(
                                    onClick = {
                                        composableScope.launch {
                                            try {
                                                viewModel.deets.value?.id?.toString()?.let {
                                                    QuitClanApi.retrofitService.quitClan(
                                                        it,
                                                        clubid.toString()
                                                    )
                                                }
                                            } catch (e: Exception) {
                                                Log.i("startdirect", e.toString())
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.textButtonColors(
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier
                                        .padding(vertical = 25.dp)
                                        .height(40.dp)
                                        .width(160.dp)
                                        .border(
                                            1.dp,
                                            AyeTheme.colors.error,
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                ) {
                                    Text(
                                        "quit clan",
                                        color = AyeTheme.colors.error,
                                        style = MaterialTheme.typography.caption,
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
        return ActivityClanHubBinding.inflate(layoutInflater)
    }
}