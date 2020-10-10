package com.example.to_do_list2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.to_do_list2.fragment.list_fragment
import kotlinx.android.synthetic.main.fragment_list_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

//        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//
//        ft.replace(R.id.home_fragment, list_fragment())
//        ft.commit()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.)
        }
    }
}