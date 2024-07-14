import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
//    alias(libs.plugins.open.javafx)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.viewModelCompose)
            implementation(libs.kotlinx.coroutines.swing)
//            implementation(libs.kotlinx.coroutines.javafx)
            implementation(libs.vlcj)
        }
    }
}

val osName = System.getProperty("os.name").lowercase()
if (osName.contains("windows")) {
    tasks.register<Copy>("copyNativeLib") {
        dependsOn(":native-lib:linkReleaseSharedNative")
        println("Copying native library to desktopJar...")
        from(
            project(":native-lib").layout.buildDirectory.get().asFile.resolve("bin/native/releaseShared")
                .apply {
                    println("from $this")
                })
        into(layout.buildDirectory.dir("libs"))
        include("**/*.dll")
    }
}

android {
    namespace = "com.hollyland.iq.tools"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.hollyland.iq.tools"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "IQ Tools"
            packageVersion = "1.0.1"
        }
    }

    if (osName.contains("windows")) {
        afterEvaluate {
            // 此处为了将 native 库同 msi、exe 一起打包，否则找不到相关的 native 库
            tasks.getByName("createRuntimeImage") {
                dependsOn("copyNativeLib")
                doLast {
                    val dllFile = project.layout.buildDirectory.dir("libs").get().asFile.listFiles()
                    println("dllFile: $dllFile")
                    // 移动 dll 文件到 /compose/tmp/main/runtime/bin
                    dllFile?.forEach {
                        println("file: $it")
                        it.copyTo(
                            project.layout.buildDirectory.dir("compose/tmp/main/runtime/bin")
                                .get().asFile.resolve(it.name), true
                        )
                    }
                }
            }
        }
    }
}

//javafx {
//    version = "19"
//    modules("javafx.media", "javafx.swing")
//}
