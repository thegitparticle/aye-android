package com.example.toastgoand.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material.icons.rounded.PersonAddAlt1
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.ui.layout.fillMaxWidth
import androidx.viewbinding.ViewBinding
import androidx.viewpager.widget.ViewPager
import coil.compose.rememberImagePainter
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.LoginActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityLandingBinding
import com.example.toastgoand.databinding.StartCallDialogBinding
import com.example.toastgoand.home.aye.TheAyeActivity
import com.example.toastgoand.home.clans.ClansFragment
import com.example.toastgoand.home.clans.components.LiveClanItem
import com.example.toastgoand.home.directs.DirectsFragment
import com.example.toastgoand.home.directs.DirectsViewModel
import com.example.toastgoand.home.directs.DirectsViewModelFactory
import com.example.toastgoand.home.invitepeopledirectly.InvitePeopleDirectlyActivity
import com.example.toastgoand.home.myprofile.MyProfileActivity
import com.example.toastgoand.home.startclan.StartClanActivity
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.Screen
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import com.example.toastgoand.uibits.CircleIcon
import com.example.toastgoand.uibits.TextRowButtonClan
import com.example.toastgoand.uibits.TextRowButtonDirect
import com.example.toastgoand.utilities.drawColorShadow
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import compose.icons.FeatherIcons
import compose.icons.feathericons.UserPlus


@AndroidEntryPoint
class LandingActivity : BaseActivity() {

    private lateinit var binding: ActivityLandingBinding

    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout

    @Inject
    lateinit var navigator: Navigator

    lateinit var prefHelper: PrefHelper

    private val viewModel: LandingViewModel by viewModels {
        LandingViewModelFactory(
            (this.application as ToastgoApplication).repository,
            (this.application as ToastgoApplication).repositoryMyClans,
            (this.application as ToastgoApplication).repositoryMyDirects,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityLandingBinding

        binding.floatingActionButton.setOnClickListener {
            val sheet = StreamBottomSheetFragment()
            sheet.show(supportFragmentManager, "StreamBottomSheetFragment")
        }

        Log.i("dpdebughere normal", "normal logging works")

        viewModel.deets.value?.image?.let { Log.i("dpdebughere", it) }

        val composePart = binding.headerButtons
        composePart.setContent {
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

                val painterAye = rememberImagePainter(data = R.drawable.aye_logo)
                val painterDp = rememberImagePainter(data = deetsHere.image)

                viewModel.deets.value?.image?.let { Log.i("dpdebughere compose", it) }

                val context = LocalContext.current

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = AyeTheme.colors.uiBackground),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    CircleIcon(
                        iconName = Icons.Rounded.PersonAddAlt1,
                        modifier = Modifier
                            .padding(horizontal = 20.dp),
                        onIconPressed =
                        {
                            val intent =
                                Intent(
                                    context,
                                    InvitePeopleDirectlyActivity::class.java
                                ).apply {}
                            startActivity(intent)
                        })

                    Image(
                        painter = painterAye,
                        contentDescription = "aye logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .width(55.dp)
                            .height(35.dp)
                            .clickable {
                                val intent = Intent(context, TheAyeActivity::class.java).apply {}
                                startActivity(intent)
                            }
                    )
                    Image(
                        painter = painterDp,
                        contentDescription = "user dp",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .size(30.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
                            .clickable {
                                val intent = Intent(context, MyProfileActivity::class.java).apply {}
                                startActivity(intent)
                            }
                            .background(AyeTheme.colors.appLeadVariant.copy(0.1f))
                    )
                }
            }
        }

        pager = binding.viewPager
        tab = binding.navView

        val adapter = HomePagerAdapter(supportFragmentManager)

        adapter.addFragment(ClansFragment(), "clans")
        adapter.addFragment(DirectsFragment(), "directs")

        pager.adapter = adapter

        tab.setupWithViewPager(pager)

        tab.isInlineLabel = false

        tab.getTabAt(0)?.setIcon(R.drawable.home_icon)?.tabLabelVisibility =
            TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
        tab.getTabAt(1)?.setIcon(R.drawable.direct_icon_light)?.tabLabelVisibility =
            TabLayout.TAB_LABEL_VISIBILITY_UNLABELED

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(xTab: TabLayout.Tab?) {
                if (tab.selectedTabPosition == 0) {
                    tab.getTabAt(0)?.setIcon(R.drawable.home_icon)
                    tab.getTabAt(1)?.setIcon(R.drawable.direct_icon_light)
                } else {
                    tab.getTabAt(0)?.setIcon(R.drawable.home_icon_light)
                    tab.getTabAt(1)?.setIcon(R.drawable.direct_icon)
                }
            }

        })

        getSupportActionBar()?.hide()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    ContentValues.TAG,
                    "Firebase Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            }

            val token = task.result

            Log.d(ContentValues.TAG, "Firebase token: " + token)
            prefHelper = PrefHelper(this)
            if (token != null) {
                prefHelper.put(Constant.FIREBASE_TOKEN, token)
            }

        })

    }

    override fun onResume() {
        super.onResume()
        binding.navView.visibility = View.VISIBLE
    }

    private fun showDefaultDialog() {
        val dialogBinding: StartCallDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(this),
                R.layout.start_call_dialog,
                null,
                false
            )

        val customDialog = AlertDialog.Builder(this, 0).create()

        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.btnOk?.setOnClickListener {
            customDialog.dismiss()
            navigator.navigateTo(Screen.QUICK)
        }

        dialogBinding?.backImageButton?.setOnClickListener {
            customDialog.dismiss()
            binding.navView.visibility = View.VISIBLE
        }
    }

    override fun binding(): ViewBinding {
        return ActivityLandingBinding.inflate(layoutInflater)
    }
}

class StreamBottomSheetFragment : SuperBottomSheetFragment() {

    private val viewModel: LandingViewModel by viewModels {
        LandingViewModelFactory(
            (getActivity()?.getApplication() as ToastgoApplication).repository,
            (getActivity()?.getApplication() as ToastgoApplication).repositoryMyClans,
            (getActivity()?.getApplication() as ToastgoApplication).repositoryMyDirects,
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_start_stream_sheet, container, false).apply {
            findViewById<ComposeView>(R.id.composeInSheet).setContent {
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

                    val clansHere: List<MyClansDataClass> by
                    viewModel.myClans.observeAsState(listOf<MyClansDataClass>())

                    val directsHere: List<MyDirectsDataClass> by viewModel.myDirects.observeAsState(
                        listOf<MyDirectsDataClass>()
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Spacer(Modifier.height(20.dp))
                        }
                        item {
                            Text(
                                text = "clans",
                                style = MaterialTheme.typography.h5,
                                color = AyeTheme.colors.appLead,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }
                        item {
                            Spacer(Modifier.height(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Divider(
                                    color = AyeTheme.colors.textSecondary,
                                    modifier = Modifier
                                        .alpha(
                                            0.1F
                                        )
                                        .fillMaxWidth(0.8f),
                                    thickness = 1.dp,
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                        }
                        items(
                            items = clansHere,
                            itemContent = {
                                TextRowButtonClan(clan = it, userid = deetsHere.user.id)
                            })
                        item {
                            Spacer(Modifier.height(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Divider(
                                    color = AyeTheme.colors.textSecondary,
                                    modifier = Modifier
                                        .alpha(
                                            0.1F
                                        )
                                        .fillMaxWidth(0.8f),
                                    thickness = 1.dp,
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                        }
                        item {
                            Spacer(Modifier.height(10.dp))
                        }
                        item {
                            Text(
                                text = "directs",
                                style = MaterialTheme.typography.h5,
                                color = AyeTheme.colors.appLead,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }
                        item {
                            Spacer(Modifier.height(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Divider(
                                    color = AyeTheme.colors.textSecondary,
                                    modifier = Modifier
                                        .alpha(
                                            0.1F
                                        )
                                        .fillMaxWidth(0.8f),
                                    thickness = 1.dp,
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                        }
                        items(
                            items = directsHere,
                            itemContent = {
                                TextRowButtonDirect(direct = it, userid = deetsHere.user.id)
                            })
                        item {
                            Spacer(Modifier.height(20.dp))
                        }
                    }
                }
            }
        }

    }

    override fun getCornerRadius() =
        requireContext().resources.getDimension(R.dimen.demo_sheet_rounded_corner)

}