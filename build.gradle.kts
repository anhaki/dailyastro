// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.android.dynamic-feature") version "8.2.0" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false

}

buildscript{
    dependencies {
        classpath ("com.android.tools.build:gradle:4.2.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }

}
