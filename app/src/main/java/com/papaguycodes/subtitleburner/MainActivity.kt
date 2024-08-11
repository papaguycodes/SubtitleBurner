package com.papaguycodes.subtitleburner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.papaguycodes.subtitleburner.utils.FFmpegUtils
import com.papaguycodes.subtitleburner.utils.FileUtils
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var videoUri: Uri
    private val subtitleUris = mutableListOf<Uri>()

    private lateinit var startTimeInput: EditText
    private lateinit var endTimeInput: EditText
    private lateinit var fontSizeSpinner: Spinner
    private lateinit var fontColorSpinner: Spinner
    private lateinit var formatSpinner: Spinner
    private lateinit var resolutionSpinner: Spinner
    private lateinit var aspectRatioSpinner: Spinner
    private lateinit var positionSpinner: Spinner
    private lateinit var subtitleDelayInput: EditText
    private lateinit var bitrateInput: EditText
    private lateinit var frameRateInput: EditText
    private lateinit var codecSpinner: Spinner
    private lateinit var progressBar: ProgressBar
    private lateinit var burnSubtitleButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("SubtitleAppPreferences", Context.MODE_PRIVATE)

        startTimeInput = findViewById(R.id.start_time_input)
        endTimeInput = findViewById(R.id.end_time_input)
        fontSizeSpinner = findViewById(R.id.font_size_spinner)
        fontColorSpinner = findViewById(R.id.font_color_spinner)
        formatSpinner = findViewById(R.id.format_spinner)
        resolutionSpinner = findViewById(R.id.resolution_spinner)
        aspectRatioSpinner = findViewById(R.id.aspect_ratio_spinner)
        positionSpinner = findViewById(R.id.position_spinner)
        subtitleDelayInput = findViewById(R.id.subtitle_delay_input)
        bitrateInput = findViewById(R.id.bitrate_input)
        frameRateInput = findViewById(R.id.frame_rate_input)
        codecSpinner = findViewById(R.id.codec_spinner)
        progressBar = findViewById(R.id.progress_bar)
        burnSubtitleButton = findViewById(R.id.burn_subtitle_button)

        burnSubtitleButton.setOnClickListener {
            selectOutputDirectory()
        }

        loadPreferences()
    }

    private fun loadPreferences() {
        val fontSize = sharedPreferences.getInt("fontSize", 40)
        val fontColor = sharedPreferences.getString("fontColor", "White")
        val position = sharedPreferences.getString("position", "Bottom")
        val fontStyle = sharedPreferences.getString("fontStyle", "Normal")

        fontSizeSpinner.setSelection(fontSize / 20 - 1)
        fontColorSpinner.setSelection((resources.getStringArray(R.array.font_color_array).indexOf(fontColor)))
        positionSpinner.setSelection(resources.getStringArray(R.array.position_array).indexOf(position))
    }

    private fun savePreferences() {
        sharedPreferences.edit().apply {
            putInt("fontSize", when (fontSizeSpinner.selectedItem.toString()) {
                "Small" -> 20
                "Medium" -> 40
                "Large" -> 60
                else -> 40
            })
            putString("fontColor", fontColorSpinner.selectedItem.toString())
            putString("position", positionSpinner.selectedItem.toString())
            apply()
        }
    }

    override fun onPause() {
        super.onPause()
        savePreferences()
    }

    private fun selectOutputDirectory() {
        // Code to allow user to select output directory and call burnSubtitle
    }

    private fun burnSubtitle(outputDir: Uri) {
        if (this::videoUri.isInitialized && subtitleUris.isNotEmpty()) {
            val filters = FFmpegUtils.buildSubtitleFilters(subtitleUris, fontSizeSpinner, fontColorSpinner, positionSpinner, subtitleDelayInput)
            val command = FFmpegUtils.buildFFmpegCommand(videoUri, outputDir, filters, startTimeInput, endTimeInput, bitrateInput, frameRateInput, codecSpinner)

            executeFFmpegCommand(command)
        } else {
            Snackbar.make(burnSubtitleButton, "Video or Subtitle files not selected", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun executeFFmpegCommand(command: Array<String>) {
        progressBar.visibility = View.VISIBLE
        FFmpegUtils.executeFFmpegCommand(command) { _, returnCode ->
            progressBar.visibility = View.GONE
            if (returnCode == 0) {
                Snackbar.make(burnSubtitleButton, "Subtitles successfully burned", Snackbar.LENGTH_LONG).show()
                // Optionally play the video here
            } else {
                Snackbar.make(burnSubtitleButton, "Failed to burn subtitles", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
