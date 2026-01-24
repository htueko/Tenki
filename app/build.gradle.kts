import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.stability.analyzer)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kover)
    alias(libs.plugins.dokka)
}

android {
    namespace = "com.htueko.tenki"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.htueko.tenki"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    name = "proguard-android-optimize.txt",
                ),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/licenses/**"
            pickFirsts += "**/libc++_shared.so"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.addAll("-progressive", "-Xcontext-receivers")
    }
}

dokka {
    dokkaPublications.html {
        // The display name of my documentation site
        moduleName.set("Tenki Weather App")
        // Include the project README as the homepage
        includes.from("README.md")
        // Output path for GH Pages action
        outputDirectory.set(layout.buildDirectory.dir("dokka/html"))

        // --- Branding & Customization ---
        pluginsConfiguration.html {
            // My name in the footer
            footerMessage.set("© 2026 htueko. Built with Dokka and Jetpack Compose.")

            // Separate inherited members for cleaner API docs
            separateInheritedMembers.set(true)

            // For the logo-icon.svg in the project (later add it)
            // customAssets.from(file("assets/logo-icon.svg"))
        }

    }
    dokkaSourceSets.configureEach {
        // Source Links: Adds a "Source" button on every class/function
        sourceLink {
            localDirectory.set(file("src/main/kotlin"))
            remoteUrl.set(URI("https://github.com/htueko/Tenki/blob/master/app/src/main/kotlin"))
            remoteLineSuffix.set("#L")
        }
        // Link to Android/Kotlin external docs
        enableAndroidDocumentationLink.set(false)
        enableKotlinStdLibDocumentationLink.set(false)
        // Suppress generated code (e.g. Hilt, Room, BuildConfigs)
        perPackageOption {
            matchingRegex.set(".*\\.generated.*|.*\\.hilt.*|com\\.htueko\\.tenki\\.BuildConfig")
            suppress.set(true)
        }

    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // network libraries
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.okhttp)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // image loading library
    implementation(libs.coil.compose)

    // ui support libraries
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

    // Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.navigation.compose)

    // Shimmer effect
    implementation(libs.compose.shimmer)

    // room database
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)

    // Animation
    implementation(libs.lottie.compose)

    // Logging
    implementation(libs.timber)

    // Google truth
    testImplementation(libs.google.truth)
    androidTestImplementation(libs.google.truth)

    // testing flow
    // turbine
    testImplementation(libs.turbine)
    androidTestImplementation(libs.turbine)

    // Unit test
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)

    // Instrumentation test
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.mockk)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}