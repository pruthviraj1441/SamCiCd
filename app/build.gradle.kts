plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "in.ibkart.agent"
    compileSdk = 34

    defaultConfig {
        applicationId = "in.ibkart.agent"
        minSdk = 24
        targetSdk = 34
        versionCode = 99
        versionName = "V1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("../ibkart_cicd.jks") // Adjust this based on your project structure
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: throw IllegalArgumentException("KEYSTORE_PASSWORD not set") // Use environment variable
            keyAlias = System.getenv("KEY_ALIAS") ?: throw IllegalArgumentException("KEY_ALIAS not set") // Use environment variable
            keyPassword = System.getenv("KEY_ALIAS_PASSWORD") ?: throw IllegalArgumentException("KEY_ALIAS_PASSWORD not set") // Use environment variable
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release") // Reference the signing config
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
