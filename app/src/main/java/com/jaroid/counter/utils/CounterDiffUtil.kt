package com.jaroid.counter.utils

import androidx.recyclerview.widget.DiffUtil
import com.jaroid.counter.model.Counter

class CounterDiffUtil(private val oldList: List<Counter>, private val newList: List<Counter>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].value == newList[newItemPosition].value
                && oldList[oldItemPosition].dateInMillis == newList[newItemPosition].dateInMillis


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].isTheSameContent(newList[newItemPosition])
}
