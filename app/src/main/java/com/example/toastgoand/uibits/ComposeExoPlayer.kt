package com.example.toastgoand.uibits

import android.content.Intent
import android.net.Uri
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Icon
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.example.toastgoand.R
import com.example.toastgoand.composestyle.AyeTheme
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import compose.icons.FeatherIcons
import compose.icons.feathericons.X
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.android.exoplayer2.MediaItem

@Composable
fun ComposeExoPlayer(videoLink: String, thumbNail: Uri, isPlaying: Boolean) {
    val context = LocalContext.current
    val exoPlayer = remember(context) { SimpleExoPlayer.Builder(context).build() }

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            context.startActivity(
                Intent(
                    context,
                    ViewVideoActivity::class.java
                ).apply {
                    putExtra("videolink", videoLink)
                })
        }) {

        VideoCard(
            videoLink = videoLink,
            thumbNail = thumbNail,
            isPlaying = isPlaying,
            exoPlayer = exoPlayer,
        )
    }
}

@Composable
fun VideoCard(
    videoLink: String,
    thumbNail: Uri,
    isPlaying: Boolean,
    exoPlayer: SimpleExoPlayer,
) {
    val isPlayerUiVisible = remember { mutableStateOf(false) }
    val isPlayButtonVisible = if (isPlayerUiVisible.value) true else !isPlaying

    Box(
        modifier = Modifier
            .height(256.dp)
            .fillMaxWidth()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        if (isPlaying) {
            exoPlayer.setMediaItem(MediaItem.fromUri(videoLink.toUri()))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            exoPlayer.play()

            VideoPlayerWithControls(exoPlayer, videoLink, isPlaying)
        } else {
            exoPlayer.pause()
            VideoThumbnail(thumbNail)
        }
    }
}

@Composable
fun VideoThumbnail(url: Uri) {
    Image(
        painter = rememberImagePainter(data = url, builder = {
            crossfade(true)
            size(512, 512)
        }),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(256.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun VideoPlayerWithControls(exoPlayer: SimpleExoPlayer, videoLink: String, isPlaying: Boolean) {
    val context = LocalContext.current
    val playerView = remember {
        val layout = LayoutInflater.from(context).inflate(R.layout.video_player, null)
        val playerView = (layout.findViewById(R.id.playerView) as PlayerView).apply {
            player = exoPlayer

        }
        layout.id = View.generateViewId()
        playerView
    }


    AndroidView({ playerView },
        Modifier
            .height(256.dp)
            .background(Color.Black))
}