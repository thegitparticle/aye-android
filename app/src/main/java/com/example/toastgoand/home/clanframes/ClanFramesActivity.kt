package com.example.toastgoand.home.clanframes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanFramesBinding
import com.example.toastgoand.home.clanframes.components.AMonth
import com.example.toastgoand.home.clanframes.components.AMonthClanViewModel
import com.example.toastgoand.home.clanhub.ClanHubActivity
import com.example.toastgoand.home.otherprofile.OtherProfileActivity
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronLeft
import compose.icons.feathericons.ChevronRight
import compose.icons.feathericons.User
import compose.icons.feathericons.Users
import kotlinx.datetime.*

class ClanFramesActivity : BaseActivity() {
    private lateinit var binding: ActivityClanFramesBinding

    private lateinit var viewModel: ClanFramesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanFramesBinding

        setContent {
            AyeTheme() {
                val clubName = intent.getStringExtra("clubName")
                val clubid = intent.getIntExtra("clubid", 0)
                val channelid = intent.getStringExtra("channelid")
                val ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
                val startTime = intent.getStringExtra("startTime")
                val endTime = intent.getStringExtra("endTime")
                val userid = intent.getStringExtra("userid")
                val directornot = intent.getBooleanExtra("directornot", false)

                fun onDirectHubPressed() {

                    val splitChannelID = channelid?.split("_")?.map { it.trim() }

                    var otherIdHere: Int = 0

                    if (userid?.toInt() == splitChannelID?.get(0)?.toInt()) {
                        otherIdHere = splitChannelID?.get(1)?.toInt()!!
                    } else {
                        otherIdHere = splitChannelID?.get(0)?.toInt()!!
                    }

                    if (otherIdHere > 0) {
                        startActivity(
                            Intent(
                                this,
                                OtherProfileActivity::class.java
                            ).apply {
                                putExtra("otheruserid", otherIdHere)
                            })
                    }
                }

                val context = LocalContext.current

                val now: Instant = Clock.System.now()
                val today: LocalDate = now.toLocalDateTime(TimeZone.currentSystemDefault()).date

                val todayDate = today.dayOfMonth
                val currentMonth = today.monthNumber
                var viewMonth by remember { mutableStateOf(currentMonth) }

                fun getMonthName(month: Int): String {
                    var monthName: String
                    if (month == 1) {
                        monthName = "JANUARY"
                        return monthName
                    } else if (month == 2) {
                        monthName = "FEBRUARY"
                        return monthName
                    } else if (month == 3) {
                        monthName = "MARCH"
                        return monthName
                    } else if (month == 4) {
                        monthName = "APRIL"
                        return monthName
                    } else if (month == 5) {
                        monthName = "MAY"
                        return monthName
                    } else if (month == 6) {
                        monthName = "JUNE"
                        return monthName
                    } else if (month == 7) {
                        monthName = "JULY"
                        return monthName
                    } else if (month == 8) {
                        monthName = "AUGUST"
                        return monthName
                    } else if (month == 9) {
                        monthName = "SEPTEMBER"
                        return monthName
                    } else if (month == 10) {
                        monthName = "OCTOBER"
                        return monthName
                    } else if (month == 11) {
                        monthName = "NOVEMBER"
                        return monthName
                    } else if (month == 12) {
                        monthName = "DECEMBER"
                        return monthName
                    } else {
                        monthName = ""
                        return monthName
                    }

                }

                var monthStringRender: String = getMonthName(viewMonth)

                var monthText by remember { mutableStateOf(monthStringRender) }

                ProvideWindowInsets() {
                    com.google.accompanist.insets.ui.Scaffold(
                        topBar = {
                            if (clubName != null) {
                                HeaderPlayScreens(
                                    modifier = Modifier.statusBarsPadding(),
                                    title = clubName,
                                    onBackIconPressed = {
                                        onBackPressed()
                                    },
                                    onActionIconPressed = {
                                        if (!directornot) {
                                            context.startActivity(
                                                Intent(
                                                    context,
                                                    ClanHubActivity::class.java
                                                ).apply {
                                                    putExtra("clubName", clubName)
                                                    putExtra("clubid", clubid)
                                                    putExtra("channelid", channelid)
                                                    putExtra("ongoingFrame", ongoingFrame)
                                                    putExtra("startTime", startTime)
                                                    putExtra("endTime", endTime)
                                                })
                                        } else {
                                            onDirectHubPressed()
                                        }
                                    },
                                    actionIcon = if (!directornot) {
                                        FeatherIcons.Users
                                    } else {
                                        FeatherIcons.User
                                    }
                                )
                            }
                        },
                    ) {
                        Column(
                            modifier = Modifier
                                .background(AyeTheme.colors.uiBackground)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.size(100.dp))
                            Spacer(modifier = Modifier.size(25.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth(0.9f)
                            ) {
                                Icon(
                                    FeatherIcons.ChevronLeft,
                                    "last month",
                                    tint = AyeTheme.colors.iconVector,
                                    modifier = Modifier.size(17.dp).clickable {
                                        viewMonth -= 1
                                        monthText = getMonthName(viewMonth)
                                        Log.i("viewmonth", viewMonth.toString())
                                    },
                                )
                                Text(
                                    text = monthText,
                                    style = MaterialTheme.typography.subtitle1,
                                    color = AyeTheme.colors.textSecondary
                                )
                                Icon(
                                    FeatherIcons.ChevronRight,
                                    "next month",
                                    tint = AyeTheme.colors.iconVector,
                                    modifier = Modifier.size(17.dp).clickable {
                                        if (!(viewMonth == currentMonth)) {
                                            viewMonth += 1
                                            monthText = getMonthName(viewMonth)
                                            Log.i("viewmonth", viewMonth.toString())
                                        }
                                    },
                                )
                            }
                            Spacer(modifier = Modifier.size(25.dp))
                            if (clubid != null) {
                                if (clubName != null) {
                                    if (userid != null) {
                                        if (channelid != null) {
                                            LazyColumn(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(AyeTheme.colors.uiBackground),
                                            ) {
                                                item {
                                                    AMonth(
                                                        AMonthClanViewModel(),
                                                        clubid.toString(),
                                                        viewMonth,
                                                        currentMonth,
                                                        todayDate,
                                                        clubName = clubName,
                                                        userid = userid,
                                                        channelid = channelid
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }


    override fun binding(): ViewBinding {
        return ActivityClanFramesBinding.inflate(layoutInflater)
    }

}