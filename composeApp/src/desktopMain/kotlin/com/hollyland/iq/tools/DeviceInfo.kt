package com.hollyland.iq.tools

/**
 * @author Jacky
 *
 * @Date 2024/7/11
 **/
data class DeviceInfo(val name: String, val value: String)

val deviceInfoList by lazy {
    listOf(
        DeviceInfo("产品名称", "探境PRO"),
        DeviceInfo("设备 SN", "12345678"),
        DeviceInfo("设备系统版本", "V1.0"),
        DeviceInfo("设备 IQ 版本", "23.0"),
    )
}