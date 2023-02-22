package com.jaroid.counter.model

class DataImplement : DataInterface {
    private val _items = mutableListOf<Counter>()

    private object Holder {
        val dataController = DataImplement()
    }

    var neeUpdate: (willUpdate: Boolean) -> Unit = {}

    override val items: MutableList<Counter>
        get() = _items

    override fun removeItem(counter: Counter) {
        _items.removeAll { it.id == counter.id }
    }

    override fun addOrUpdateItem(counter: Counter, isUpdate: Boolean) {
        if (isUpdate) {
            val item = _items.first {
                it.id == counter.id
            }
            if (item != null) {
                val index = _items.indexOf(counter)

                _items[index] = counter
            }
        } else {
            _items.add(counter)
        }
        neeUpdate(true)
    }

    companion object {
        val instance = Holder.dataController
    }
}