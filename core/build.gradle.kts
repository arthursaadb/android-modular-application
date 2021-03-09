plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes.forEach {
        it.buildConfigField("String", "TMDB_API_BASE_URL", "\"https://api.themoviedb.org/\"")
        it.buildConfigField("String", "API_KEY", "\"8be89195a64206c889833492c39467e9\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.HILT)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KTX)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.PAGING)

    kapt(Dependencies.ROOM_COMPILER)
    kapt(Dependencies.HILT_COMPILER)
}