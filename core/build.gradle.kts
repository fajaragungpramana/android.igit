import java.util.Properties

plugins {
    id(Plugin.LIBRARY)
    id(Plugin.KOTLIN)
    id(Plugin.KOTLIN_KAPT)
    id(Plugin.HILT)
}

android {
    namespace = "com.github.fajaragungpramana.igit.core"
    compileSdk = Version.TARGET_SDK

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        minSdk = Version.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", Config.API_BASE_URL, properties.getProperty("api.base_url_release"))
            buildConfigField("String", Config.API_TOKEN, properties.getProperty("api.token_release"))
            buildConfigField("String", Config.SQL_DATABASE, properties.getProperty("sql.database_release"))
        }

        debug {
            buildConfigField("String", Config.API_BASE_URL, properties.getProperty("api.base_url_debug"))
            buildConfigField("String", Config.API_TOKEN, properties.getProperty("api.token_debug"))
            buildConfigField("String", Config.SQL_DATABASE, properties.getProperty("sql.database_debug"))
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

    api(Dependency.AndroidX.DATA_STORE)
    implementation(Dependency.AndroidX.PAGING_RUNTIME_KTX)
    implementation(Dependency.AndroidX.ROOM_RUNTIME)
    kapt(Dependency.AndroidX.ROOM_COMPILER)

    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)

    api(Dependency.Square.RETROFIT)
    implementation(Dependency.Square.CONVERTER_GSON)
    implementation(Dependency.Square.LOGGING_INTERCEPTOR)

}

kapt {
    correctErrorTypes = true
}