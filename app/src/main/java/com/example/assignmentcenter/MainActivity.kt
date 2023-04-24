package com.example.assignmentcenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.assignmentcenter.fragments.EditFragment
import com.example.assignmentcenter.fragments.ListFragment
import com.example.assignmentcenter.fragments.PreviewFragment

class MainActivity : AppCompatActivity(), Navigable {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, listFragment, listFragment.javaClass.name)
            .commit()
    }

    override fun navigate(to: Navigable.Destination, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            when (to) {
                Navigable.Destination.List -> replace(
                    R.id.container,
                    listFragment,
                    listFragment.javaClass.name
                )
                Navigable.Destination.Add -> {
                    replace(R.id.container, EditFragment(), EditFragment::class.java.name)
                    addToBackStack(EditFragment::class.java.name)
                }
                Navigable.Destination.Preview -> {
                    if (fragment is PreviewFragment) {
                        replace(R.id.container, fragment)
                        addToBackStack(PreviewFragment::class.java.name)
                    }
                }
                Navigable.Destination.Edit -> {
                    if (fragment is EditFragment) {
                        replace(R.id.container, fragment)
                        addToBackStack(EditFragment::class.java.name)
                    }
                }
            }
        }.commit()
    }

}