package com.example.assignmentcenter

import androidx.fragment.app.Fragment
import com.example.assignmentcenter.fragments.PreviewFragment

interface Navigable {
    enum class Destination {
        List, Add, Preview, Edit
    }
    fun navigate(to: Destination, fragment: Fragment = PreviewFragment())
}