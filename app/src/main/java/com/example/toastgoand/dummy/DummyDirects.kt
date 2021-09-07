package com.example.toastgoand.dummy

import com.example.toastgoand.home.directs.DisplayGuys
import com.example.toastgoand.home.directs.MyDirectDataClass

object DummyDirects {
    val myDirects = listOf(
        MyDirectDataClass(
            directChannelID = "81_82_d",
            displayGuys =
                DisplayGuys(
                    userID = "81",
                    profilePicture = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0001.JPEG",
                    fullName = "Aditya Mathur"
                ),
            frameTotal = 5,
            ongoingFrame = false,
            startTime = false,
            endTime = false
        ),
        MyDirectDataClass(
            directChannelID = "82_83_d",
            displayGuys =
            DisplayGuys(
                userID = "83",
                profilePicture = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/photo_2021-07-13_22.43.37.jpeg",
                fullName = "Ananya Garg"
            ),
            frameTotal = 5,
            ongoingFrame = false,
            startTime = false,
            endTime = false
        ),
        MyDirectDataClass(
            directChannelID = "121_82_d",
            displayGuys =
            DisplayGuys(
                userID = "121",
                profilePicture = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_6659.JPEG",
                fullName = "Vinay Porandla"
            ),
            frameTotal = 5,
            ongoingFrame = false,
            startTime = false,
            endTime = false
        )
    )
}
