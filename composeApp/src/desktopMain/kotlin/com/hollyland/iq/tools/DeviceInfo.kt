package com.hollyland.iq.tools

import deviceInfoImpl

/**
 * @author Jacky
 *
 * @Date 2024/7/11
 **/
data class DeviceInfo(val name: String, val value: String)

val deviceInfoList by lazy {
    val deviceInfoImpl = deviceInfoImpl()
    listOf(
        DeviceInfo("产品名称", deviceInfoImpl.deviceName),
        DeviceInfo("设备 SN", deviceInfoImpl.deviceSn),
        DeviceInfo("设备系统版本", deviceInfoImpl.sysVersion),
        DeviceInfo("设备 IQ 版本", deviceInfoImpl.iqVersion),
    )
}