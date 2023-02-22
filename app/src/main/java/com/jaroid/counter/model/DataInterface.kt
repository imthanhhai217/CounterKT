package com.jaroid.counter.model

interface DataInterface {
    val items: List<Counter>
    fun addOrUpdateItem(counter: Counter, isUpdate:Boolean)
    fun removeItem(counter: Counter)
}