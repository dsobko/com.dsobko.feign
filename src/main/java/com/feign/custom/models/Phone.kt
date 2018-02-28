package com.feign.custom.models

data class Phone(var model: String, var batteryCapacity: Int, var year: Int, val isBrandNew: Boolean = true)