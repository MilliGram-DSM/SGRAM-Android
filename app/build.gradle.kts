plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.sgram"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sgram"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.android.support:recyclerview-v7:28.0.0")
    implementation ("androidx.recyclerview:recyclerview:1.1.0")

    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Feature module Support
    implementation (libs.navigation.dynamic.features.fragment)

    // Testing Navigation
    androidTestImplementation (libs.navigation.testing)

    implementation(libs.socket.io.client)
}