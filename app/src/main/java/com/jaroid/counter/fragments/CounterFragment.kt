package com.jaroid.counter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaroid.counter.R
import com.jaroid.counter.databinding.FragmentCounterBinding
import com.jaroid.counter.model.Counter

class CounterFragment(private val counter:Counter) : Fragment() {

    lateinit var counterBinding:FragmentCounterBinding
    companion object {
        @JvmStatic
        fun newInstance(counter:Counter) = CounterFragment(counter = counter)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        counterBinding = FragmentCounterBinding.inflate(inflater,container,false)
        return counterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        counterBinding.counter = counter
    }



}