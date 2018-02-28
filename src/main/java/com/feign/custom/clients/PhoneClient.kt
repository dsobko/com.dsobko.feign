package com.feign.custom.clients

import com.feign.custom.models.Phone
import com.feign.custom.models.PhoneResource
import feign.Headers
import feign.Param
import feign.RequestLine

interface PhoneClient {

    @RequestLine("GET /{model}")
    fun findByModel(@Param("model") model: String): PhoneResource

    @RequestLine("GET")
    fun findAll(): List<PhoneResource>

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    fun create(phone: Phone)

}