object Dependencies {

    object Gradle {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"

        // LifeCycle
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Test {
        const val test = "junit:junit:${Versions.junit}"
        const val testKoin = "org.koin:koin-test:${Versions.koin}"
    }

    object AndroidTest {
        const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Dependency {
        // okhttp & retrofit2
        const val okhttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
        const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2}"

        // RxJava
        const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

        // room
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val rxRoom = "androidx.room:room-rxjava2:${Versions.room}"

        // Glide
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

        // koin
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"

        // circleImageView
        const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageview}"

        // naver login
        const val naverLogin = "com.naver.nid:naveridlogin-android-sdk:${Versions.naverLogin}"

        // naver csr
        const val naverCSR = "com.naver.speech.clientapi:naverspeech-ncp-sdk-android:${Versions.naverCsr}"
    }
}