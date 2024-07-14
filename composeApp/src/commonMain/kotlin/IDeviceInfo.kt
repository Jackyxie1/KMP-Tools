interface IDeviceInfo {
    val deviceName: String
    val deviceSn: String
    val sysVersion: String
    val iqVersion: String
}

expect fun deviceInfoImpl(): IDeviceInfo