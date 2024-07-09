plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    val osName = System.getProperty("os.name").lowercase()

    if (osName.contains("windows")) {
        mingwX64("native") {
            binaries {
                sharedLib {
                    baseName = "nativeLib"
                }
            }
            compilations["main"].cinterops {
                val jni by creating {
                    defFile("src/main/c_interop/jni.def")
                    // 项目依赖的 jdk 的 include 文件夹路径，否则无法找到 jni.h 之类的文件
                    includeDirs {
                        println("current os is $osName")
                        // 如果操作系统是 windows，设置 JAVA_HOME
                        if (osName.lowercase().contains("windows")) {
                            System.getenv("JAVA_HOME")?.let {
                                allHeaders("$it/include", "$it/include/win32")
                            } ?: error("JAVA_HOME is not set")
                        }
                    }
                }
            }
        }

        sourceSets {
            val nativeMain by getting {
                kotlin.srcDirs("src/main/kotlin")
            }
        }
    } else if (osName.contains("mac")) {
        macosArm64("macNative") {
            binaries {
                sharedLib {
                    baseName = "nativeLib"
                }
            }
            compilations["main"].cinterops {
                val jni by creating {
                    defFile("src/main/c_interop/jni.def")
                    includeDirs {
                        println("current os is $osName")
                        if (osName.lowercase().contains("mac")) {
                            // 如果操作系统是 mac，设置 JAVA_HOME
                            System.getenv("JAVA_HOME")?.let {
                                println("mac java home is $it")
                                allHeaders("$it/include", "$it/include/darwin")
                            } ?: error("JAVA_HOME is not set")
                        }
                    }
                }
            }
        }
    }
}