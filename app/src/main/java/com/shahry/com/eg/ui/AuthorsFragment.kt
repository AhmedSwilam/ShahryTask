package com.shahry.com.eg.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahry.com.eg.adapter.AuthorsAdapter
import com.shahry.com.eg.adapter.OnAuthorItemClicked
import com.shahry.com.eg.databinding.FragmentAuthorsBinding
import com.shahry.com.eg.model.AuthorModel
import com.shahry.com.eg.viewmodel.AuthorViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class AuthorsFragment : Fragment(), OnAuthorItemClicked {

    private lateinit var binding: FragmentAuthorsBinding
    private lateinit var authorRecyclerAdapter: AuthorsAdapter
    private val viewModel: AuthorViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorsBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAuthorsRecyclerView()
        initViewModel()

    }

    private fun initAuthorsRecyclerView() {
        val recAuthors = binding.recAuthors
        recAuthors.layoutManager = LinearLayoutManager(activity)
        val decorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recAuthors.addItemDecoration(decorator)
        authorRecyclerAdapter = AuthorsAdapter()
        recAuthors.adapter = authorRecyclerAdapter
        authorRecyclerAdapter.onAuthorItemClicked = this
    }

    private fun initViewModel() {
        viewModel.authorsLiveDataList.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                authorRecyclerAdapter.setUpdatedData(it)
            } else {
                Toast.makeText(activity, "Error Loading Data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getAuthorsList()
    }


    override fun onItemClicked(authorModel: AuthorModel) {
        val authorId = authorModel.id.toString()
        val action = AuthorsFragmentDirections.actionAuthorsFragmentToDetailsFragment(authorId)
        findNavController().navigate(action)
    }
}