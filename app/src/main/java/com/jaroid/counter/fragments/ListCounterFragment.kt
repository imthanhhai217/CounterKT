package com.jaroid.counter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.jaroid.counter.NavigationInterface
import com.jaroid.counter.adapters.CounterAdapter
import com.jaroid.counter.databinding.FragmentListCounterBinding
import com.jaroid.counter.model.Counter
import com.jaroid.counter.model.DataImplement


class ListCounterFragment() : Fragment() {

    val navigationController by lazy {
        requireActivity() as NavigationInterface
    }

    private var _binding: FragmentListCounterBinding? = null
    private val binding get() = _binding!!

    private lateinit var counterAdapter: CounterAdapter
    private lateinit var fakeData: MutableList<Counter>

    companion object {
        const val TAG = "TAG"

        @JvmStatic
        fun newInstance() = ListCounterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData();
        initView()
    }

    private fun initData() {
        fakeData = mutableListOf()
        for (i in 0..10) {
            var counter = Counter(i, System.currentTimeMillis());
            fakeData.add(counter)
        }
        DataImplement.instance.items.clear()
        DataImplement.instance.items.addAll(fakeData)
    }

    fun addOrUpdateCounters() {
        var counter = Counter(
            DataImplement.instance.items.size, System.currentTimeMillis()
        )
        DataImplement.instance.addOrUpdateItem(counter, false)
        counterAdapter.updateData(DataImplement.instance.items)
    }

    private fun initView() {
        binding.clickListener = ListCounterListener(this)
        counterAdapter = CounterAdapter(fakeData, itemClickListener)
        binding.counterAdapter = counterAdapter
        itemTouchHelper.attachToRecyclerView(binding.rvCounter)
    }

    private val itemClickListener = object : CounterAdapter.ItemClickListener {
        override fun onItemClickListener(counter: Counter) {
            DataImplement.instance.items.firstOrNull { counter.id == it.id }.let {
                var newCounter = Counter(counter.value + 1001
                    ,counter.dateInMillis)
                newCounter.id = counter.id
                DataImplement.instance.addOrUpdateItem(newCounter,true)
                counterAdapter.updateData(DataImplement.instance.items)
            }
        }
    }

    private val itemTouchHelper by lazy {
        val simpleTouchCallback = object : SimpleCallback(UP or DOWN or START or END, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                var adapter = recyclerView.adapter as CounterAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                adapter.move(from, to)
                adapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        }

        ItemTouchHelper(simpleTouchCallback)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    class ListCounterListener {
        private var fragment: ListCounterFragment

        constructor(fragment: Fragment) {
            this.fragment = fragment as ListCounterFragment
        }

        fun addListener(view: View) {
            Log.d(TAG, "addListener: ")
//            fragment.navigationController.navigationToFragment(CounterFragment.newInstance())
            fragment.addOrUpdateCounters()
        }
    }


}