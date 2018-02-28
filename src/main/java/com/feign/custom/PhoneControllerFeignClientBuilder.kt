package com.feign.custom

import com.feign.custom.clients.PhoneClient
import feign.Feign
import feign.Logger
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger

class PhoneControllerFeignClientBuilder {

    val phoneClient = createClient(PhoneClient::class.java, "http://localhost:9999/api/phones")

    private fun <T> createClient(type: Class<T>, uri: String): T {
        return Feign.builder()
                .client(OkHttpClient())
                .encoder(GsonEncoder())
                .decoder(GsonDecoder())
                .logger(Slf4jLogger(type))
                .logLevel(Logger.Level.FULL)
                .target(type, uri)
    }
}