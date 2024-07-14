package com.hollyland.iq.tools.component

import IDeviceInfo

data class DeviceInfoImpl(
    override val deviceName: String = "探境PRO",
    override val deviceSn: String = "12345678",
    override val sysVersion: String = "V1.0",
    override val iqVersion: String = "23.0"
) : IDeviceInfo