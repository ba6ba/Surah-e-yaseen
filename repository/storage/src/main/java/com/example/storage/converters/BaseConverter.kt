package com.example.storage.converters

import java.lang.reflect.Type

interface BaseConverter<T> {

    val listType: Type?

    fun fromString(value: String): T?

    fun fromList(list: T?): String
}