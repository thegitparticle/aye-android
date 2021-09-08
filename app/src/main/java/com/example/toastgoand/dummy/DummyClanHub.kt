package com.example.toastgoand.dummy

import com.example.toastgoand.home.clanhub.ClanHubDataClass
import com.example.toastgoand.home.clanhub.User

object DummyClanHub {
    val clanHub = ClanHubDataClass(
        id = 89,
        name = "Random ideas",
        memberCount = 3,
        dateCreated = "2021-07-14T22:45:43+05:30",
        profilePicture = "https://aye-media-bucket.s3.amazonaws.com/media/club_images/breakingbad_2.jpg",
        framesTotal = 16,
        members = "81,58,82",
        adminLeader = "81",

        users = listOf(
            User(
                userID = 81,
                username = "thegodfather",
                name = "Aditya Mathur",
                displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0001.JPEG"
            ),
            User(
                userID = 81,
                username = "san",
                name = "San",
                displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0032.JPG"
            ),
            User(
                userID = 81,
                username = "thegirlbella",
                name = "Bella",
                displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0113.JPG"
            ),
        )
    )
}