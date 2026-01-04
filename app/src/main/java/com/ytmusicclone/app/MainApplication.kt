package com.ytmusicclone.app

import android.app.Application
import android.os.Process
import android.util.Log
import com.ytmusicclone.utility.android.AndroidNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(SupervisorJob() + Dispatchers.Default).launch {
            AndroidNetwork.observeNetworkConnectivityStatus(applicationContext).collect {
                Log.d("dcm33e3ds", it.toString())
            }
        }
        // This is where you plant the "listener" for crashes
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            // 1. Log it so you can see it in Logcat
            Log.e("GLOBAL_CRASH", "Crash in thread: ${thread.name}")
            Log.e("GLOBAL_CRASH", "Reason: ${throwable.message}")
            throwable.printStackTrace()
            // 2. CRITICAL: After logging, you MUST let the app actually die.
            // If you don't kill the process, the app might stay in a "zombie"
            // state where the screen is frozen and buttons don't work.
            Process.killProcess(Process.myPid())
            exitProcess(1)
        }
    }
}