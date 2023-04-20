package com.example.assignmentcenter

interface Navigable {
    enum class Destination {
        List, Add
    }
    fun navigate(to: Destination)
}