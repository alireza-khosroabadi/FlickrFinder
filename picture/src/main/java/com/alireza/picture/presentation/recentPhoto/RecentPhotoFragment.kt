package com.alireza.picture.presentation.recentPhoto

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alireza.core.extentions.safeNavigation
import com.alireza.core.presentation.fragment.BaseObserverFragment
import com.alireza.picture.R
import com.alireza.picture.databinding.FragmentRecentPhotoBinding
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentPhotoFragment : BaseObserverFragment<FragmentRecentPhotoBinding>() {

    private val recentPhotoViewModel: RecentPhotoViewModel by viewModels()
    private val recentPhotoAdapter:RecentPhotoListAdapter by lazy { RecentPhotoListAdapter() }

    override fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){

                recentPhotoViewModel.recentPhotoState.collect{data ->
                    when(data){
                        is Error -> showError(data.message)
                        is Exception ->  showError(data.throwable)
                        Loading -> showLoading(true)
                        is RecentPhotoList -> showRecentPhoto(data.photoList)
                    }
                }
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        binding.progressLoading.isVisible = loading
        if (loading) {
            binding.emptyState.isVisible = false
            binding.errorState.isVisible = false
        }
    }

    private fun showError(data: Throwable) {
        //TODO Error handling with costume Classes
        showLoading(false)
        binding.emptyState.isVisible = false
        binding.errorState.isVisible = true
        binding.errorState.setCaption(data.localizedMessage)
    }

    private fun showError(message: String) {
        showLoading(false)
        binding.emptyState.isVisible = false
        binding.errorState.isVisible = true
        binding.errorState.setCaption(message)
    }

    private fun showRecentPhoto(photoList: List<RecentPhoto>) {
        showLoading(false)
        binding.emptyState.isVisible = photoList.isEmpty()
        binding.rvPhotoList.isVisible = binding.emptyState.isVisible.not()
        recentPhotoAdapter.addPhoto(photoList)
    }

    override fun getViewBinding(): FragmentRecentPhotoBinding =
        FragmentRecentPhotoBinding.inflate(layoutInflater)

    override fun setupViews() {
        initializeRecentPhotoListRecyclerView()
        setupListeners()
    }

    private fun setupListeners() {
        emptyStateListener()
        errorStateListener()
        searchClickListener()
    }

    private fun searchClickListener() {
        binding.imgSearch.setOnClickListener {
            RecentPhotoFragmentDirections.actionRecentPhotoFragmentToSearchPhotoFragment().also {action ->
                safeNavigation(action, R.id.recentPhotoFragment )
            }
        }
    }

    private fun initializeRecentPhotoListRecyclerView() {
        with(binding.rvPhotoList) {
            layoutManager =
                GridLayoutManager(requireContext(),3, GridLayoutManager.VERTICAL,false)
            adapter = recentPhotoAdapter
        }
    }

    override fun donOnCreateView() {

    }

    private fun emptyStateListener() {
        binding.emptyState.setOnButtonClickListener {
            recentPhotoViewModel.loadRecentPhoto()
        }
    }

    private fun errorStateListener() {
        binding.errorState.setOnButtonClickListener {
            recentPhotoViewModel.loadRecentPhoto()
        }
    }
}