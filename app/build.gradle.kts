plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
    //Hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    //SafeArgs (Kotlin) - Navigation
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.uzuu.learn1_firebase"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.uzuu.learn1_firebase"
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.12.0"))

    // Firebase Auth (không cần version, không cần -ktx)
    implementation("com.google.firebase:firebase-auth")

    // Firebase Analytics (tuỳ chọn, nhưng nên có)
    implementation("com.google.firebase:firebase-analytics")

    // Google Sign-In (CHỈ cần nếu bạn dùng "Sign in with Google")
    // Không cần cho Email/Password auth
    // implementation("com.google.android.gms:play-services-auth:21.2.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.59.2")
    kapt("com.google.dagger:hilt-android-compiler:2.59.2")

    //dùng .await()
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")

    // ViewModel + viewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    // repeatOnLifecycle + lifecycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    // Activity KTX ,Kotlin extension: by viewModels()
    implementation("androidx.activity:activity-ktx:1.9.3")
    // Navigation (Fragment-based)
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.7")
    // Material Design (cho TextInputLayout, Button chuẩn)
    implementation("com.google.android.material:material:1.12.0")
}