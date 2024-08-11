<!-- //💡In the Beginning, PaPaGuy wrote beautiful Codes < /> 💜❤️ // -->
package com.papaguycodes.subtitleburner.utils

import android.widget.EditText
import android.widget.Spinner
import com.arthenica.mobileffmpeg.FFmpeg
import com.arthenica.mobileffmpeg.ExecuteCallback
import android.net.Uri

object FFmpegUtils {

    fun buildSubtitleFilters(subtitleUris: List<Uri>, fontSizeSpinner: Spinner, fontColorSpinner: Spinner, positionSpinner: Spinner, subtitleDelayInput: EditText): String {
        val fontSize = when (fontSizeSpinner.selectedItem.toString()) {
            "Small" -> 20
            "Medium" -> 40
            "Large" -> 60
            else -> 40
        }
        val fontColor = when (fontColorSpinner.selectedItem.toString()) {
            "White" -> "white"
            "Yellow" -> "yellow"
            "Red" -> "red"
            else -> "white"
        }
        val position = when (positionSpinner.selectedItem.toString()) {
            "Bottom" -> "y=h-line_h-10"
            "Top" -> "y=10"
            "Custom" -> "y=(h-line_h)/2"
            else -> "y=h-line_h-10"
        }
        val subtitleDelay = subtitleDelayInput.text.toString().toIntOrNull() ?: 0

        return subtitleUris.joinToString(",") { uri ->
            "subtitles=${uri.path}:force_style='FontSize=$fontSize,PrimaryColour=&H00${fontColor.uppercase()}&,Alignment=2':$position,setpts=PTS+$subtitleDelay/TB"
        }
    }

    fun buildFFmpegCommand(videoUri: Uri, outputDir: Uri, filters: String, startTimeInput: EditText, endTimeInput: EditText, bitrateInput: EditText, frameRateInput: EditText, codecSpinner: Spinner): Array<String> {
        val startTime = startTimeInput.text.toString().toIntOrNull() ?: 0
        val endTime = endTimeInput.text.toString().toIntOrNull()
        val bitrate = bitrateInput.text.toString().toIntOrNull() ?: 1000
        val frameRate = frameRateInput.text.toString().toIntOrNull() ?: 30
        val codec = codecSpinner.selectedItem.toString()

        val outputFileName = FileUtils.getFileName(context, videoUri).replace(".mp4", "-with-subs.mp4")
        val outputUri = FileUtils.getOutputUri(context, outputFileName, outputDir)

        val command = mutableListOf<String>().apply {
            addAll(arrayOf("-i", videoUri.path))
            addAll(arrayOf("-vf", filters))
            if (endTime != null && endTime > startTime) {
                addAll(arrayOf("-ss", startTime.toString(), "-to", endTime.toString()))
            }
            addAll(arrayOf(
                "-c:v", codec,
                "-b:v", "${bitrate}k",
                "-r", frameRate.toString(),
                "-c:a", "copy",
                outputUri.path.toString()
            ))
        }.toTypedArray()

        return command
    }

    fun executeFFmpegCommand(command: Array<String>, callback: ExecuteCallback) {
        FFmpeg.executeAsync(command, callback)
    }
}
