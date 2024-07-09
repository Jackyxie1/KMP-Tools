rootProject.name = "IQTools"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/public")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/central")
//        }
//
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/jitpack/")
//        }
//
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/google")
//        }
//
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/maven-public/")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/android-group-snapshot")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/android-group")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/tencent-maven/")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/composeDev/")
//        }
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/public")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/central")
//        }
//
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/jitpack/")
//        }
//
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/google")
//        }
//
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/maven-public/")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/android-group-snapshot")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/android-group")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/tencent-maven/")
//        }
//        maven {
//            isAllowInsecureProtocol = true
//            url = uri("http://192.168.10.60:8081/nexus/repository/composeDev/")
//        }
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp", ":native-lib")
