plugins {
    id("com.android.application") version "8.2.0"
    id("org.jetbrains.kotlin.android") version "2.0.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    kotlin("kapt") version "2.0.0"
}

android {
    namespace = "com.liquiditylens"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.liquiditylens"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
    
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    // Kotlin
    implementation("androidx.core:core-ktx:1.12.0")
    
    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.8.2")
    
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")
    
    // MPAndroidChart for candlestick charts
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    
    // Room for candle caching
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // OkHttp for WebSocket
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    
    // Gson for JSON parsing
    implementation("com.google.code.gson:gson:2.10.1")
}
