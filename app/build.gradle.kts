plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.0"
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.ytmusicclone"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ytmusicclone"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //Compose Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)

    //Ktor client
    implementation("io.ktor:ktor-client-core:3.1.0")
    implementation("io.ktor:ktor-client-cio:3.1.0")

    //Kotlinx JSON Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.0")

    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    //FontAwesome Icons
    implementation("com.woowla.compose.icon.collections:fontawesome:6.7.2")

    //Coil Image Loading Library
    implementation("io.coil-kt.coil3:coil-compose:3.3.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")

    //Jetpack Media 3
    implementation("androidx.media3:media3-exoplayer:1.9.0")
    implementation("androidx.media3:media3-common:1.9.0")
    implementation("androidx.media3:media3-ui-compose-material3:1.9.0")

    //Immutable Collections for Kotlin
    implementation(libs.kotlinx.collections.immutable)

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")

    //Nav3 ViewModel Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    //Hilt Lifecycle ViewModel
    implementation(libs.androidx.hilt.lifecycle.viewmodel.compose)

    //Room Database
    implementation(libs.androidx.room.runtime)
    ksp("androidx.room:room-compiler:2.8.4")
    //Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.8.4")





//    //Bootstrap Icons
//    implementation("com.composables:icons-bootstrap-outline-android:2.2.1")
}