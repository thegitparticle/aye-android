package com.example.toastgoand.home.directframes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityClanFramesBinding
import com.example.toastgoand.databinding.ActivityDirectFramesBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clanframes.ClanFramesViewModel
import com.example.toastgoand.home.clanframes.components.AMonth
import com.example.toastgoand.home.clanframes.components.AStrip
import com.example.toastgoand.home.clanhub.ClanHubActivity
import com.example.toastgoand.home.clanhub.components.ClanMetrics
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.directhub.DirectHubActivity
import com.example.toastgoand.uibits.TopHeaderPlayScreens
import com.google.accompanist.appcompattheme.AppCompatTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronLeft
import compose.icons.feathericons.ChevronRight
import kotlinx.datetime.*
import spencerstudios.com.bungeelib.Bungee

class DirectFramesActivity : BaseActivity() {
    private lateinit var binding: ActivityDirectFramesBinding

    private lateinit var viewModel: DirectFrameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDirectFramesBinding

        setContent {
            AppCompatTheme() {
                val otherName = intent.getStringExtra("otherName")
                val context = LocalContext.current

                fun onHubPressed() {
                    startActivity(
                        Intent(
                            this,
                            DirectHubActivity::class.java
                        ).apply {
                            putExtra("otherName", otherName)
                        })
                    Bungee.slideUp(this)
                }

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
                    }
                    else if (month == 3) {
                        monthName = "MARCH"
                        return monthName
                    }
                    else if (month == 4) {
                        monthName = "APRIL"
                        return monthName
                    }
                    else if (month == 5) {
                        monthName = "MAY"
                        return monthName
                    }
                    else if (month == 6) {
                        monthName = "JUNE"
                        return monthName
                    }
                    else if (month == 7) {
                        monthName = "JULY"
                        return monthName
                    }
                    else if (month == 8) {
                        monthName = "AUGUST"
                        return monthName
                    }
                    else if (month == 9) {
                        monthName = "SEPTEMBER"
                        return monthName
                    }
                    else if (month == 10) {
                        monthName = "OCTOBER"
                        return monthName
                    }
                    else if (month == 11) {
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

                Column(modifier = Modifier.fillMaxSize()) {
                    TopHeaderPlayScreens(
                        modifier = Modifier.fillMaxWidth(),
                        onLeftIconPressed = {
                           onHubPressed()
                        },
                        title = {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Channel name
                                if (otherName != null) {
                                    Text(
                                        text = otherName,
                                        style = MaterialTheme.typography.subtitle1
                                    )
                                }
                            }
                        },
                    )
                    Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            imageVector = FeatherIcons.ChevronLeft,
                            contentDescription = "last month",
                            modifier = Modifier.clickable {
                                viewMonth -= 1
                                monthText = getMonthName(viewMonth)
                                Log.i("viewmonth", viewMonth.toString())
                            }
                        )
                        Text(
                            text = monthText,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Icon(
                            imageVector = FeatherIcons.ChevronRight,
                            contentDescription = "next month",
                            modifier = Modifier.clickable {
                                viewMonth += 1
                                monthText = getMonthName(viewMonth)
                                Log.i("viewmonth", viewMonth.toString())
                            }
                        )
                    }
                    AMonth(viewMonth, currentMonth, todayDate)
                }
            }
        }
    }


    override fun binding(): ViewBinding {
        return ActivityDirectFramesBinding.inflate(layoutInflater)
    }
}