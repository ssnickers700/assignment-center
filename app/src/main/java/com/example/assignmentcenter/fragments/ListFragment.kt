package com.example.assignmentcenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentcenter.adapters.AssignmentAdapter
import com.example.assignmentcenter.Navigable
import com.example.assignmentcenter.data.AssignmentDatabase
import com.example.assignmentcenter.databinding.FragmentListBinding
import com.example.assignmentcenter.model.Assignment
import kotlin.concurrent.thread


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var adapter: AssignmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AssignmentAdapter()
        loadData()

        binding.list.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btAdd.setOnClickListener {
            (activity as? Navigable)?.navigate(Navigable.Destination.Add)
        }

        binding.btSort.setOnClickListener {
            adapter?.sort()
        }
    }

    fun loadData() = thread {
        val assignments = AssignmentDatabase
            .open(requireContext())
            .assignments.getAll()
            .map {entity ->
                Assignment(
                    resources.getIdentifier(
                        entity.icon,
                        "drawable",
                        requireContext().packageName
                    ),
                    entity.name,
                    entity.note,
                    entity.priority
                )
            }
//        requireActivity().runOnUiThread {
            adapter?.replace(assignments)
//        }
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

}