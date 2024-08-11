# Proguard rules for optimizing the release build of the Subtitle Burner app

# Keep the names of methods in MainActivity
-keepclassmembers class com.papaguycodes.subtitleburner.MainActivity {
    public *;
}

# Keep the names of methods in VideoProcessor
-keepclassmembers class com.papaguycodes.subtitleburner.VideoProcessor {
    public *;
}

# Mobile FFmpeg
-keep class com.arthenica.mobileffmpeg.** { *; }
-dontwarn com.arthenica.mobileffmpeg.**

# Android
-keep class androidx.** { *; }
-dontwarn androidx.**

# Material Design Components
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# Kotlin
-keep class kotlin.** { *; }
-dontwarn kotlin.**

# Core Android libraries
-keep class android.** { *; }
-dontwarn android.**

# Keep anything in the app that is annotated with @Keep
-keep @androidx.annotation.Keep class * { *; }
