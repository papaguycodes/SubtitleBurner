package com.papaguycodes.subtitleburner

import android.content.Context
import android.net.Uri
import com.papaguycodes.subtitleburner.utils.FFmpegUtils

class VideoProcessor(private val context: Context) {

    fun burnSubtitles(videoUri: Uri, subtitleUris: List<Uri>, outputUri: Uri, params: Map<String, Any>) {
        // Code to burn subtitles to video using FFmpegUtils
    }
}
