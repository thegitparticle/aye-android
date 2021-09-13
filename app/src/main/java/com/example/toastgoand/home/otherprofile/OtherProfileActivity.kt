package com.example.toastgoand.home.otherprofile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityClanHubBinding
import com.example.toastgoand.databinding.ActivityOtherProfileBinding
import com.example.toastgoand.home.otherprofile.components.DetailsOtherProfile
import com.google.accompanist.appcompattheme.AppCompatTheme

class OtherProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityOtherProfileBinding

    private lateinit var viewModel: OtherProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityOtherProfileBinding
        viewModel = ViewModelProvider(this).get(OtherProfileViewModel::class.java)

        val otheruserid = intent.getIntExtra("otheruserid", 0)

        viewModel.getOtherProfileHere(otheruserid = otheruserid)

        setContent {
            AppCompatTheme {
                val otherProfile: List<OtherProfileDataClass> by viewModel.otherProfile.observeAsState(
                    listOf(
                        OtherProfileDataClass(
                            user = DepthDetails(
                                username = "",
                                phone = "",
                                full_name = "",
                                id = 0,
                                clubs_joined_by_user = "",
                                number_of_clubs_joined = 0,
                                contact_list = "",
                                total_frames_participation = 0,
                                country_code_of_user = "",
                                contact_list_sync_status = false
                            ),
                            bio = "",
                            image = "",
                            id = 0
                        )
                    )
                )

                DetailsOtherProfile(userDeets = otherProfile[0])
                Row() {
                    Button(
                        onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Blue
                        )
                    ) {
                        Text("add friends")
                    }
                    Button(
                        onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black
                        )
                    ) {
                        Text("invite friends")
                    }
                }
                Button(
                    onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Red
                    )
                ) {
                    Text("quit clan")
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityOtherProfileBinding.inflate(layoutInflater)
    }
}

