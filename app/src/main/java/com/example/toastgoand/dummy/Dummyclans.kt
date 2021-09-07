package com.example.toastgoand.dummy

import com.example.toastgoand.home.clans.DisplayPhoto
import com.example.toastgoand.home.clans.MyClanDataClass

object Dummyclans {
    val myClans = listOf(
        MyClanDataClass(
            clubProfilePic = "https://aye-media-bucket.s3.amazonaws.com/media/club_images/office_1.jpg",
            clubName = "Chitti > Thanos",
            clubID = 77,
            pnChannelID = "77_c",
            clubMembers = "82,83,84",
            frameTotal = 2,
            ongoingFrame = false,
            startTime = "null",
            endTime = "null",
            displayPhotos = listOf(
                DisplayPhoto(
                    userID = 82,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0032.JPG"
                ),
                DisplayPhoto(
                    userID = 83,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/photo_2021-07-13_22.43.37.jpeg"
                ),
                DisplayPhoto(
                    userID = 84,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/46.jpeg"
                ),
            )
        ),
        MyClanDataClass(
            clubProfilePic = "https://aye-media-bucket.s3.amazonaws.com/media/club_images/photo_2021-07-15_16.15.53.jpeg",
            clubName = "Ebiza Out",
            clubID = 80,
            pnChannelID = "80_c",
            clubMembers = "87,83,82,88,84,101,113",
            frameTotal = 2,
            ongoingFrame = false,
            startTime = "null",
            endTime = "null",
            displayPhotos = listOf(
                DisplayPhoto(
                    userID = 82,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0032.JPG"
                ),
                DisplayPhoto(
                    userID = 83,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/photo_2021-07-13_22.43.37.jpeg"
                ),
                DisplayPhoto(
                    userID = 84,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/46.jpeg"
                ),
                DisplayPhoto(
                    userID = 87,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/8.jpeg"
                ),
                DisplayPhoto(
                    userID = 88,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/64.jpeg"
                ),
                DisplayPhoto(
                    userID = 101,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/26.jpeg"
                ),
            )
        ),
        MyClanDataClass(
            clubProfilePic = "https://aye-media-bucket.s3.amazonaws.com/media/club_images/friends_2.jpg",
            clubName = "Saifabad",
            clubID = 78,
            pnChannelID = "78_c",
            clubMembers = "82,86,119,121",
            frameTotal = 1,
            ongoingFrame = false,
            startTime = "null",
            endTime = "null",
            displayPhotos = listOf(
                DisplayPhoto(
                    userID = 82,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0032.JPG"
                ),
                DisplayPhoto(
                    userID = 83,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/default.jpgg"
                ),
                DisplayPhoto(
                    userID = 84,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/default.jpg"
                ),
                DisplayPhoto(
                    userID = 121,
                    displayPic = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_6659.JPEG"
                ),
            )
        )
    )
}