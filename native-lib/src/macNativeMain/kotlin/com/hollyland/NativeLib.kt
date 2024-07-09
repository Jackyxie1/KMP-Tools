package com.hollyland

import jni.JNIEnvVar
import jni.jclass
import jni.jstring
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import kotlinx.cinterop.invoke
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
@CName("Java_com_hollyland_iq_tools_NativeLib_nativeFunction")
fun nativeFunction(env: CPointer<JNIEnvVar>, clazz: jclass): jstring? {
    val result = "Windows Native"
    return memScoped {
        env.pointed.pointed?.NewStringUTF?.invoke(env, result.cstr.ptr)
    }
}