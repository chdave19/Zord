package com.ytmusicclone.utility.android

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

object AndroidNetwork{
    private val _networkStatus = MutableStateFlow(true)
    val networkStatus = _networkStatus.asStateFlow()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    fun observeNetworkConnectivityStatus(context: Context): Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        _networkStatus.emit(isNetworkAvailable(context, connectivityManager))
        val callback = object: ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                scope.launch {
                    _networkStatus.emit(true)
                }
            }
            override fun onLost(network: Network) {
                super.onLost(network)
                scope.launch {
                    _networkStatus.emit(false)
                }
            }
            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                scope.launch {
                    _networkStatus.emit(false)
                }
            }
            override fun onUnavailable() {
                super.onUnavailable()
                scope.launch {
                    _networkStatus.emit(false)
                }
            }
        }
        connectivityManager.registerNetworkCallback(request, callback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}

fun isNetworkAvailable(context: Context, connectivityManager: ConnectivityManager): Boolean{
    val network = connectivityManager.activeNetwork?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}

enum class ConnectivityStatus{
    AVAILABLE, JUST_LOST, LOSING, UNAVAILABLE
}