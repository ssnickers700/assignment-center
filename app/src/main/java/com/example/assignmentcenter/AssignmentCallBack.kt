package com.example.assignmentcenter

import androidx.recyclerview.widget.DiffUtil
import com.example.assignmentcenter.model.Assignment

class AssignmentCallBack(val notSorted: List<Assignment>, val sorted: List<Assignment>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = notSorted.size

    override fun getNewListSize(): Int = sorted.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] === sorted[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] == sorted[newItemPosition]

}