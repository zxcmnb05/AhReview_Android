plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = Versions.applicationId
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
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

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.constraint)
    implementation(Dependencies.Google.material)
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    testImplementation(Dependencies.Test.test)
    androidTestImplementation(Dependencies.AndroidTest.junitExt)
    androidTestImplementation(Dependencies.AndroidTest.espresso)

    implementation(Dependencies.Dependency.okhttp)
    implementation(Dependencies.Dependency.retrofit)
    implementation(Dependencies.Dependency.retrofitConverter)
    implementation(Dependencies.Dependency.retrofitAdapter)

    implementation(Dependencies.AndroidX.livedata)
    implementation(Dependencies.AndroidX.viewModel)
    implementation(Dependencies.AndroidX.lifecycleRuntime)

    implementation(Dependencies.Dependency.rxjava)
    implementation(Dependencies.Dependency.rxAndroid)

    implementation(Dependencies.Dependency.roomRuntime)
    annotationProcessor(Dependencies.Dependency.roomCompiler)
    implementation(Dependencies.Dependency.rxRoom)

    implementation(Dependencies.Dependency.glide)
    annotationProcessor(Dependencies.Dependency.glideCompiler)

    implementation(Dependencies.Dependency.circleImageView)

    implementation(Dependencies.Dependency.koin)
    testImplementation(Dependencies.Test.testKoin)

    implementation(Dependencies.Google.gson)

    implementation(Dependencies.Dependency.circleImageView)

    implementation(Dependencies.Dependency.naverLogin)

    implementation(Dependencies.Dependency.naverCSR)
}