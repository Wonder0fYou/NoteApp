plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "app.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(project(":domain"))

    //Room
    implementation(libs.androidx.room.common)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Hilt Navigation Compose
    implementation(libs.androidx.hilt.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}

kapt {
    correctErrorTypes = true
}