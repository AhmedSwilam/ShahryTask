package com.shahry.com.eg.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahry.com.eg.adapter.PostsAdapter
import com.shahry.com.eg.databinding.FragmentDetailsBinding
import com.shahry.com.eg.viewmodel.AuthorViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var authorId: String? = null
    private lateinit var postsAdapter: PostsAdapter
    private val viewModel:AuthorViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorId = arguments?.getString("authorId")
        binding.progressBar2.visibility = View.VISIBLE
        initViewModel()
        initPostsRecyclerView()
    }

    private fun initViewModel() {
        viewModel.postsLiveData.observe(viewLifecycleOwner, {
            if (it != null) {

                binding.progressBar2.visibility = View.GONE
                postsAdapter.setPostsData(it)
            } else {
                Toast.makeText(activity, "Error Loading Data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getPosts(authorId!!)
    }

    private fun initPostsRecyclerView() {
        val recPosts = binding.recPosts
        recPosts.layoutManager = LinearLayoutManager(activity)
        val decore = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recPosts.addItemDecoration(decore)
        postsAdapter = PostsAdapter()
        recPosts.adapter = postsAdapter
    }


}