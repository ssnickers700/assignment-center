package com.example.assignmentcenter.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentcenter.AssignmentCallBack
import com.example.assignmentcenter.databinding.ListItemBinding
import com.example.assignmentcenter.model.Assignment

class AssignmentViewHolder(val binding: ListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(assignment: Assignment) {
        binding.assignmentName.text = assignment.name
        binding.assignmentNote.text = assignment.note
        binding.image.setImageResource(assignment.resId)

    }
}

class AssignmentAdapter : RecyclerView.Adapter<AssignmentViewHolder>() {
    private val data = mutableListOf<Assignment>()
    private val handler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())
    private var onLongClickListener: OnLongClickListener? = null

    interface OnLongClickListener {
        fun onLongClick(assignment: Assignment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AssignmentViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnLongClickListener {
            onLongClickListener?.onLongClick(data[position])
            true
        }
    }

    fun replace(newData: List<Assignment>) {
        //val callBack = AssignmentCallBack(data, newData)
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
        //val result = DiffUtil.calculateDiff(callBack)
        //result.dispatchUpdatesTo(this)
    }

    fun sort() {
        val notSorted = data.toList()
        data.sortBy { it.priority }
        val callBack = AssignmentCallBack(notSorted, data)
        val result = DiffUtil.calculateDiff(callBack)
        handler.post {
            result.dispatchUpdatesTo(this)
        }
    }

    fun setOnLongClickListener(listener: OnLongClickListener) {
        onLongClickListener = listener
    }
}