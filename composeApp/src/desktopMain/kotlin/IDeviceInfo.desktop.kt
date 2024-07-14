import com.hollyland.iq.tools.component.DeviceInfoImpl

actual fun deviceInfoImpl(): IDeviceInfo = deviceInfoImpl

private val deviceInfoImpl = DeviceInfoImpl()