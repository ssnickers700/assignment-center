package com.example.assignmentcenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
        val transaction = supportFragmentManager.beginTransaction()

        when (to) {
            Navigable.Destination.List -> {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                transaction.replace(R.id.container, listFragment, listFragment.javaClass.name)
            }
            Navigable.Destination.Add -> {
                transaction.replace(R.id.container, EditFragment(), EditFragment::class.java.name)
                transaction.addToBackStack(EditFragment::class.java.name)
            }
            Navigable.Destination.Preview -> {
                if (fragment is PreviewFragment) {
                    transaction.replace(R.id.container, fragment)
                    transaction.addToBackStack(PreviewFragment::class.java.name)
                }
            }
            Navigable.Destination.Edit -> {
                if (fragment is EditFragment) {
                    transaction.replace(R.id.container, fragment)
                    transaction.addToBackStack(EditFragment::class.java.name)
                }
            }
        }
        transaction.commit()
    }

}