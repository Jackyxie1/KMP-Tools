import com.hollyland.iq.tools.NativeLib

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")} Native ${NativeLib().nativeFunction()}"
}

actual fun getPlatform(): Platform = JVMPlatform()