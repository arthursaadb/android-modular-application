plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.saad.modularapplicationexample"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    dataBinding {
        android.buildFeatures.dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }

    dynamicFeatures = mutableSetOf(
        ":features:movie_list",
        ":features:movie_favorites"
    )
}

dependencies {
    api(project(":core"))

    api(Dependencies.KOTLIN)
    api(Dependencies.APP_COMPAT)
    api(Dependencies.MATERIAL)
    api(Dependencies.CARD_VIEW)
    api(Dependencies.CONSTRAINT_LAYOUT)
    api(Dependencies.CORE_KTX)
    api(Dependencies.COLLECTION_KTX)
    api(Dependencies.FRAGMENT_KTX)
    api(Dependencies.LIFECYCLE_KTX)
    api(Dependencies.LIVEDATA_KTX)
    api(Dependencies.NAVIGATION_KTX)
    api(Dependencies.NAVIGATION_FRAGMENT_KTX)
    api(Dependencies.NAVIGATION_UI_KTX)
    api(Dependencies.VIEWMODEL_KTX)
    api(Dependencies.NAVIGATION_FRAGMENT)
    api(Dependencies.NAVIGATION_UI)
    api(Dependencies.NAVIGATION_MODULE)
    api(Dependencies.PAGING)
    api(Dependencies.HILT)
    api(Dependencies.COIL)
    api(Dependencies.JUNIT)
    api(Dependencies.EXTJUNIT)
    api(Dependencies.ESPRESSO)

    kapt(Dependencies.HILT_COMPILER)
}