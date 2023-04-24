package com.example.assignmentcenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentcenter.R
import com.example.assignmentcenter.databinding.AssignmentImageBinding

class AssignmentImagesViewHolder(val binding: AssignmentImageBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(resId: Int, isSelected: Boolean) {
        binding.image.setImageResource(resId)
        binding.selectedFrame.visibility = if (isSelected) View.VISIBLE else View.INVISIBLE
    }
}

class AssignmentImagesAdapter : RecyclerView.Adapter<AssignmentImagesViewHolder>() {

    private val images = listOf(
        R.drawable.code, R.drawable.pet, R.drawable.home, R.drawable.study,
        R.drawable.tax, R.drawable.food, R.drawable.shopping, R.drawable.sport,
        R.drawable.baby, R.drawable.car, R.drawable.travel
    )
    private var selectedPosition: Int = 0
    var selectedIdRes: Int = 0
        get() = images[selectedPosition]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentImagesViewHolder {
        val binding = AssignmentImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AssignmentImagesViewHolder(binding).also { vh ->
            binding.root.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = vh.layoutPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: AssignmentImagesViewHolder, position: Int) {
        holder.bind(images[position], position == selectedPosition)
    }
}