plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.aiva"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.aiva"
        minSdk = 32
        targetSdk = 35
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
}

dependencies {

        implementation ("com.airbnb.android:lottie:6.5.0")
        implementation ("androidx.core:core-ktx:1.13.1")
        implementation ("androidx.appcompat:appcompat:1.7.0")
        implementation ("com.google.android.material:material:1.12.0")

        // MotionLayout (comes with constraintlayout)
        implementation ("androidx.constraintlayout:constraintlayout:2.2.0")

   
        implementation ("androidx.appcompat:appcompat:1.7.0")
        implementation ("androidx.core:core-ktx:1.13.1")
        implementation ("com.google.android.material:material:1.12.0")
        implementation ("com.airbnb.android:lottie:6.5.0")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
