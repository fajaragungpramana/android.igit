plugins {
    id(Plugin.APPLICATION)
    id(Plugin.KOTLIN)
    id(Plugin.KOTLIN_KAPT)
    id(Plugin.HILT)
    id(Plugin.NAVIGATION)
}

android {
    namespace = "com.github.fajaragungpramana.igit"
    compileSdk = Version.TARGET_SDK

    defaultConfig {
        applicationId = "com.github.fajaragungpramana.igit"
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    buildFeatures {
        viewBinding = true
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

    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":widget"))

    implementation(Dependency.AndroidX.ACTIVITY_KTX)
    implementation(Dependency.AndroidX.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependency.AndroidX.NAVIGATION_UI_KTX)

    implementation(Dependency.Common.COIL)
    implementation(Dependency.Common.LOTTIE)
    implementation(Dependency.Common.SHIMMER)

    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)

    androidTestImplementation(Dependency.AndroidX.UI_AUTOMATOR)
    androidTestImplementation(Dependency.AndroidX.ESPRESSO_CORE)
    androidTestImplementation(Dependency.AndroidX.ESPRESSO_CONTRIB)
    androidTestImplementation(Dependency.AndroidX.TEST_CORE_KTX)
    androidTestImplementation(Dependency.AndroidX.RUNNER)
    androidTestImplementation(Dependency.AndroidX.RULES)

    testImplementation(Dependency.AndroidX.CORE_TESTING)
    testImplementation(Dependency.Mockito.MOCKITO_CORE)
    testImplementation(Dependency.Mockito.MOCKITO_INLINE)
    testImplementation(Dependency.JetBrains.COROUTINE_TEST)

}

kapt {
    correctErrorTypes = true
}