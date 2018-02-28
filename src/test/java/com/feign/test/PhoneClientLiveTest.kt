package com.feign.test

import com.feign.custom.PhoneControllerFeignClientBuilder
import com.feign.custom.clients.PhoneClient
import com.feign.custom.models.Phone
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.slf4j.LoggerFactory
import java.util.UUID

@RunWith(JUnit4::class)
class PhoneClientLiveTest {

    private val log = LoggerFactory.getLogger(this.javaClass)

    private var phoneClient: PhoneClient? = null

    @Before
    fun setup() {
        val feignClientBuilder = PhoneControllerFeignClientBuilder()
        phoneClient = feignClientBuilder.phoneClient
    }

    @Test
    @Throws(Exception::class)
    fun givenPhoneClient_shouldRunSuccessfully() {
        val phones = phoneClient!!.findAll().map { it.phone }
        assertTrue(phones.size > 2)
        log.info("{}", phones)
    }

    @Test
    @Throws(Exception::class)
    fun givenPhoneClient_shouldFindOnePhone() {
        val phone = phoneClient!!.findByModel("Apple").phone
        assertTrue(phone.isBrandNew)
        log.info("{}", phone)
    }

    @Test
    @Throws(Exception::class)
    fun givenPhoneClient_shouldPostPhone() {
        val model = UUID.randomUUID().toString()
        var phone = Phone(model, 100500, 2018)
        phoneClient!!.create(phone)

        phone = phoneClient!!.findByModel(model).phone
        assertThat(phone.batteryCapacity, `is`(100500))
        log.info("{}", phone)
    }
}