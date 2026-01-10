package com.ytmusicclone.data.network.ktorclient

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorClientProvider @Inject constructor() {
    val client by lazy {
        HttpClient(CIO){
            expectSuccess = true
            engine {
                requestTimeout = 10_000
            }
            install(HttpRequestRetry){
                retryOnExceptionIf { _,cause ->
                    true
                }
                exponentialDelay()
                retryOnServerErrors(10)
            }
        }
    }
}