package com.jaroid.counter.model

import java.util.*

data class Counter(
    val value: Int, val dateInMillis: Long
) {
    var id: String = UUID.randomUUID().toString()
    override fun equals(other: Any?): Boolean {
        return other is Counter && other.id == id
    }

    fun isTheSameContent(other: Any?): Boolean {
        return other is Counter && other.id == id && other.value == value
    }
}