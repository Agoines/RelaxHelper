package com.agoines.relaxhelp

import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage

private const val PACKAGE_NAME_HOOKED = "com.coloros.relax"
private const val TAG = "xposed-template"

class MainHook : IXposedHookLoadPackage, IXposedHookZygoteInit /* Optional */ {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == PACKAGE_NAME_HOOKED) {
            // Init EzXHelper
            EzXHelperInit.initHandleLoadPackage(lpparam)
            EzXHelperInit.setLogTag(TAG)
            EzXHelperInit.setToastTag(TAG)
            // Init hooks
            initHooks(lpparam)
        }

    }

    // Optional
    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        EzXHelperInit.initZygote(startupParam)
    }

    private fun initHooks(lpparam: XC_LoadPackage.LoadPackageParam) {

        XposedHelpers.findAndHookMethod("com.coloros.basic.utils.m",
            lpparam.classLoader,
            "a",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return false
                }
            })

        XposedHelpers.findAndHookMethod("com.coloros.spacevoice.b.a",
            lpparam.classLoader,
            "a",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return true
                }
            })
    }
}
