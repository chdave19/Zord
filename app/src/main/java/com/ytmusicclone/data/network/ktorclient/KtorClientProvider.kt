package com.ytmusicclone.data.network.ktorclient

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.request

object KtorClientProvider {
    val client by lazy {
        HttpClient(CIO){
            install(HttpTimeout){

            }
            expectSuccess = true
            engine {
                requestTimeout = 10_000
                endpoint {

                }
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