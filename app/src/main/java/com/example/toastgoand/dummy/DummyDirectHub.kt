package com.example.toastgoand.dummy

import com.example.toastgoand.home.directhub.DirectHubDataClass
import com.example.toastgoand.home.directhub.User

object DummyDirectHub {
    val directHub = listOf(
        DirectHubDataClass(
            user = User(
                username = "san",
                phone = "+919849167641",
                fullName = "San",
                id = 82,
                clubsJoinedByUser = "77,78,79,80,85,86,89,90",
                numberOfClubsJoined = 8,
                contactList = "",
                totalFramesParticipation = 148,
                countryCodeOfUser = "IN",
                contactListSyncStatus = true
            ),
            bio = "blahed",
            image = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0032.JPG",
            id = 84
        )
    )
}