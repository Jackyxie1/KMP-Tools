package com.hollyland.iq.tools

class NativeLib {
    
    external fun nativeFunction(): String
    
    companion object {
        init {
            System.loadLibrary("nativeLib")
        }
    }
}