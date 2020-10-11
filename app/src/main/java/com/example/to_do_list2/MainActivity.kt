package com.example.to_do_list2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list2.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ListFragment()
        fragmentTransaction.add(R.id.main, fragment)
        fragmentTransaction.commit()
    }
}