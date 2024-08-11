# SubtitleBurner
Subtitle Burner is an Android application that allows you to burn subtitles directly into your videos, making them compatible with a wide range of TV models, both old and new. This app is fully offline, offering complete customization over subtitle appearance and video output.

# Features
- Fully Offline: No internet connection required.
- Subtitle Support: Works with .srt, .ass, and other popular formats.
- Customization: Modify font size, color, position, delay, and more.
- Compatibility: Output videos compatible with a variety of TVs.
- User-Friendly: Simple and intuitive interface.

# Main Components
- MainActivity.kt: The main entry point of the app, handling UI interactions, user inputs, and calling the appropriate functions to process video files.
- VideoProcessor.kt: A utility class responsible for processing videos, burning subtitles, and handling video output formats.
- FileUtils.kt: Utility class for file management tasks, such as extracting file names and generating output URIs.
- FFmpegUtils.kt: Contains FFmpeg command builders and methods for executing FFmpeg commands asynchronously.

# Installation & Setup
# Prerequisites
- Android Studio: Version 4.0 or above.
- Java Development Kit (JDK): Version 8 or above.
- Minimum Android SDK: 21 (Lollipop).
- FFmpeg: This project uses the mobile-ffmpeg library to handle video processing
 
# Installation Steps
1.Clone the repository
```bash
git clone https://github.com/papaguycodes/SubtitleBurner.git
```
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.

# Usage Guide
# User Interface Overview
- Video Selection: Users can select the video file from their device storage.
- Subtitle Selection: Users can add one or more subtitle files.
- Customization Options:
1. Font Size: Choose between Small, Medium, or Large.
2. Font Color: Options include White, Yellow, Red, etc.
3. Position: Place subtitles at the Bottom, Top, or a custom position.
4. Subtitle Delay: Adjust delay in milliseconds.
5. Bitrate & Frame Rate: Customize the video output settings.
6. Output Format: Choose between MP4, MKV, or AVI formats.

# Workflow
- Select a Video: Use the file picker to choose a video file.
- Add Subtitles: Select subtitle files.
- Customize Settings: Adjust font, color, and other settings as needed.
- Burn Subtitles: Choose the output directory and burn subtitles to the video.
- Output: The final video will be saved in the specified location, compatible with a wide range of TVs.

# Technical Details
# FFmpeg Integration
The application uses FFmpeg to perform all video processing tasks. The mobile-ffmpeg library provides a wrapper around FFmpeg, allowing its commands to be executed within the Android environment.

# Customization
- Subtitle Filters: The filters used in FFmpeg for subtitle burning are highly customizable, allowing for various aesthetic and functional adjustments.
- Output Compatibility: The app ensures that the output video format is suitable for both old and new TVs by allowing customization of video bitrate, frame rate, and codec.

# Troubleshooting & Support
# Common Issues
- App Crashes on Video Processing: Ensure that the video format is supported and that the device has enough processing power.
- Subtitles Not Displaying Properly: Check the subtitle file format and ensure it is correctly formatted.

# Future Enhancements
- Batch Processing: Allow users to burn subtitles to multiple videos at once.
- Additional Output Formats: Support for more video formats such as MOV, FLV, etc.
- Advanced Subtitles Customization: Provide more granular controls over subtitle styling

# License
This project is licensed under the MIT License.
