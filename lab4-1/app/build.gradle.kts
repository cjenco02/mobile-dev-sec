

plugins {
    alias(libs.plugins.android.application)
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("C:\\Users\\cjenc\\repos\\mobile-dev-sec\\keystore\\keystore.jks")
            storePassword = "keystore"
            keyAlias = "mykey"
            keyPassword = "mykey!"
        }
    }
    namespace = "com.csec467.serviceinvoker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.csec467.serviceinvoker"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("release")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}