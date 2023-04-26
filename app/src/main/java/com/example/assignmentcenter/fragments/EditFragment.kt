package com.example.assignmentcenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentcenter.adapters.AssignmentImagesAdapter
import com.example.assignmentcenter.Navigable
import com.example.assignmentcenter.R
import com.example.assignmentcenter.data.AssignmentDatabase
import com.example.assignmentcenter.data.model.AssignmentEntity
import com.example.assignmentcenter.databinding.FragmentEditBinding
import com.example.assignmentcenter.model.Assignment
import kotlin.concurrent.thread


class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var adapter: AssignmentImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEditBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AssignmentImagesAdapter()
        binding.images.apply {
            adapter = this@EditFragment.adapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }


        val assignment = arguments?.getParcelable<Assignment>("assignment")
        assignment?.let {
            binding.assignmentNameEdit.setText(it.name)
            binding.assignmentNoteEdit.setText(it.note)
            binding.assignmentPriorityEdit.setText(it.priority.toString())
            adapter.selectedIdRes = it.resId
        }

        binding.screenTitle.text =
            if (assignment == null)
                resources.getString(R.string.create_screen_title)
            else
                resources.getString(R.string.edit_screen_title)

        binding.save.setOnClickListener {
            val priorityInput = binding.assignmentPriorityEdit.text.toString()

            val newAssignment = AssignmentEntity(
                id = assignment?.id ?: 0,
                icon = resources.getResourceEntryName(adapter.selectedIdRes),
                name = binding.assignmentNameEdit.text.toString(),
                note = binding.assignmentNoteEdit.text.toString(),
                priority = if (priorityInput.isNotBlank()) Integer.parseInt(priorityInput) else 0,
            )

            thread {
                val assignmentDatabase = AssignmentDatabase.open(requireContext())
                if (assignment != null) {
                    assignmentDatabase.assignments.updateAssignment(newAssignment)
                } else {
                    assignmentDatabase.assignments.addAssignment(newAssignment)
                }
                (activity as? Navigable)?.navigate(Navigable.Destination.List)
            }
        }
    }


}