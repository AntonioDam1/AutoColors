plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    /* Persistencia de datos */
    kotlin("kapt")
}

android {
    namespace = "com.example.autocolorsprueba"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.autocolorsprueba"
        minSdk = 28
        targetSdk = 33
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

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.4.2")
    testImplementation("junit:junit:4.12")


    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:core:1.5.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")


    implementation("org.jetbrains.dokka:android-documentation-plugin:1.9.10")

    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    val room_version = "2.5.0"
    /*room database*/
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    /*IMPLEMENTACIONES DEL GOOGLE MAPS*/
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    implementation ("com.github.skydoves:colorpickerview:2.3.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.google.code.gson:gson:2.10.1")
    implementation("androidx.activity:activity:1.6bn   .1")
//    implementation("androidx.activity:activity-ktx:1.8.2")
//    implementation("androidx.fragment:fragment-ktx:1.6.2")


}