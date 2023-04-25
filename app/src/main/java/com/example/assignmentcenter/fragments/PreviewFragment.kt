package com.example.assignmentcenter.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentcenter.Navigable
import com.example.assignmentcenter.R
import com.example.assignmentcenter.adapters.AssignmentImagesAdapter
import com.example.assignmentcenter.data.AssignmentDatabase
import com.example.assignmentcenter.data.model.AssignmentEntity
import com.example.assignmentcenter.databinding.FragmentEditBinding
import com.example.assignmentcenter.databinding.FragmentPreviewBinding
import com.example.assignmentcenter.model.Assignment
import kotlin.concurrent.thread

class PreviewFragment : Fragment() {
    private lateinit var binding: FragmentPreviewBinding
    private var selectedIconResId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPreviewBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.image.setImageResource(0)
        val assignment = arguments?.getParcelable<Assignment>("assignment")

        assignment?.let {
            selectedIconResId = it.resId
            binding.assignmentNamePreview.text = it.name
            binding.assignmentNotePreview.text = it.note
            binding.assignmentPriorityPreview.text = it.priority.toString()
            binding.image.setImageResource(selectedIconResId)
        }

        binding.edit.setOnClickListener {
            val editFragment = EditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("assignment", assignment)
                }
            }
            (activity as? Navigable)?.navigate(Navigable.Destination.Edit, editFragment)
        }

        binding.share.setOnClickListener {
            shareAssignment()
        }
    }

    private fun shareAssignment() {
        val assignmentName = binding.assignmentNamePreview.text.toString()
        val assignmentNote = binding.assignmentNotePreview.text.toString()
        val assignmentPriority = binding.assignmentPriorityPreview.text.toString()

        val emailBody = """
            Assignment Details:
            Name: $assignmentName
            Note: $assignmentNote
            Priority: $assignmentPriority
        """.trimIndent()

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Assignment Details: $assignmentName")
            putExtra(Intent.EXTRA_TEXT, emailBody)
        }

        startActivity(Intent.createChooser(emailIntent, "Share assignment via:"))
    }


}