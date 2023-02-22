package com.jaroid.counter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jaroid.counter.R
import com.jaroid.counter.adapters.CounterAdapter.CounterViewHolder
import com.jaroid.counter.databinding.ItemBinding
import com.jaroid.counter.model.Counter
import com.jaroid.counter.utils.CounterDiffUtil

class CounterAdapter(
    private var listCounter: MutableList<Counter>, private val iItemClickListener: ItemClickListener
) : RecyclerView.Adapter<CounterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        var dataBinding = ItemBinding.bind(view)
        return CounterViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        var counter = listCounter[position]
        holder.dataBinding.counterModel = counter
        holder.dataBinding.itemClick = iItemClickListener
    }

    override fun getItemCount(): Int {
        return listCounter.size
    }

    fun move(from: Int, to: Int) {
        Log.d("TAG", "from $from  to $to")
        var newListSwap = listCounter.swap(from, to);
        listCounter.clear()
        listCounter.addAll(newListSwap)
        notifyItemMoved(from, to)
        notifyItemMoved(to, from)
    }

    fun updateData(newListCounter: MutableList<Counter>) {
        val result = DiffUtil.calculateDiff(CounterDiffUtil(listCounter, newListCounter))
        listCounter.clear()
        listCounter.addAll(newListCounter)
        result.dispatchUpdatesTo(this)

    }

    private fun <T> Iterable<T>.swap(from: Int, to: Int): Iterable<T> {
        val fromItem: T? = find { it == from }
        val toItem: T? = find { it == to }
        return mapIndexed { index, value -> if (index == from && toItem != null) toItem else if (index == to && fromItem != null) fromItem else value };
    }

    class CounterViewHolder(itemBinding: ItemBinding) : ViewHolder(itemBinding.root) {
        var dataBinding: ItemBinding = itemBinding
    }

    interface ItemClickListener {
        fun onItemClickListener(counter: Counter)
    }
}