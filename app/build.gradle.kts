plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.example.wallpaperapp4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wallpaperapp4"
        minSdk = 21
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //TabLayout and viewpager
    implementation("com.google.android.material:material:1.2.0")

    // retrofit

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // GSON

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // coroutine

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    //recycler view
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    //picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    implementation("androidx.fragment:fragment-ktx:1.3.0")


}