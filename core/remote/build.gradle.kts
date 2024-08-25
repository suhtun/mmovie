plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.su.remote"

    compileSdk =34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.okhttp.logging.interceptor)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}