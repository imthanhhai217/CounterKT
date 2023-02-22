package com.jaroid.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jaroid.counter.fragments.ListCounterFragment

class MainActivity : AppCompatActivity(), NavigationInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerSimple, ListCounterFragment.newInstance()).commitNow()
    }

    override fun navigationToFragment(fragment: Fragment) {
        if (supportFragmentManager.fragments.size == 1) {
            supportFragmentManager.beginTransaction().add(R.id.containerSimple, fragment)
                .commitNow()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.fragments.last()).commitNow()
        } else {
            super.getOnBackPressedDispatcher()
        }
    }
}