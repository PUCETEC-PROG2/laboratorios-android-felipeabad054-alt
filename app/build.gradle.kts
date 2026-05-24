plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD
=======
    alias(libs.plugins.kotlin.android)
>>>>>>> 70b8f37419920975ebc94b55c686692351e2fe96
    alias(libs.plugins.kotlin.compose)
}

android {
<<<<<<< HEAD
    namespace = "ec.edu.puce.myapplication"
    compileSdk = 36

    defaultConfig {
        applicationId = "ec.edu.puce.myapplication"
        minSdk = 26
=======
    namespace = "ec.edu.puce.githubclient"
    compileSdk = 36

    defaultConfig {
        applicationId = "ec.edu.puce.githubclient"
        minSdk = 27
>>>>>>> 70b8f37419920975ebc94b55c686692351e2fe96
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
<<<<<<< HEAD

=======
>>>>>>> 70b8f37419920975ebc94b55c686692351e2fe96
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
<<<<<<< HEAD

=======
>>>>>>> 70b8f37419920975ebc94b55c686692351e2fe96
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
<<<<<<< HEAD

=======
    kotlinOptions {
        jvmTarget = "11"
    }
>>>>>>> 70b8f37419920975ebc94b55c686692351e2fe96
    buildFeatures {
        compose = true
    }
}

dependencies {

<<<<<<< HEAD
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
=======
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
>>>>>>> 70b8f37419920975ebc94b55c686692351e2fe96
}