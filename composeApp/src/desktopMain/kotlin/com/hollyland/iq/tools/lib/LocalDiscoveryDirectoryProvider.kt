package com.hollyland.iq.tools.lib

import uk.co.caprica.vlcj.factory.discovery.provider.DiscoveryDirectoryProvider
import uk.co.caprica.vlcj.factory.discovery.provider.DiscoveryProviderPriority
import java.io.File

// 在 test-sandbox 运行时使用
class TestDiscoveryDirectoryProvider : DiscoveryDirectoryProvider {
    override fun priority(): Int = DiscoveryProviderPriority.USER_DIR

    override fun directories(): Array<String> {
        val os = when (Platform.currentPlatform) {
            is Platform.Linux -> return emptyArray()
            is Platform.MacOS -> "macos"
            is Platform.Windows -> "windows"
            Platform.Android -> error("Invalid platform: ${Platform.currentPlatform}")
        }

        val arch = when (Platform.currentPlatform.arch) {
            Arch.X86_64 -> "x64"
            Arch.AARCH64 -> "arm64"
        }

        val libs = File(System.getProperty("user.dir")).resolve("src/desktopMain/appResources/${os}-${arch}/lib")
        println("============ TestDiscoveryDirectoryProvider: directories path: ${libs.absolutePath}")
        if (!libs.exists()) {
            println("============ TestDiscoveryDirectoryProvider: directories path: not exists")
            return emptyArray()
        }
        return arrayOf(libs.absolutePath)
    }

    override fun supported(): Boolean = true
}

// 在打包后的 app 使用
class ComposeResourcesDiscoveryDirectoryProvider : DiscoveryDirectoryProvider {

    init {
        directories().forEach {
            println("========== ComposeResourcesDiscoveryDirectoryProvider: $it")
        }
    }

    override fun priority(): Int = DiscoveryProviderPriority.USER_DIR

    override fun directories(): Array<String> {
        println("============ ComposeResourcesDiscoveryDirectoryProvider: directories")
        val path = System.getProperty("compose.application.resources.dir") ?: return emptyArray()
        val libs = File(path).resolve("lib")

        println("============ ComposeResourcesDiscoveryDirectoryProvider: directories path: $path")
        if (!libs.exists()) return emptyArray()
        return arrayOf(libs.absolutePath)
    }

    override fun supported(): Boolean = true
}