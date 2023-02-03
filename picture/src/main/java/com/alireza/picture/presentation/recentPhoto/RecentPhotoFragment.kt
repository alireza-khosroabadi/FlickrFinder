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
import com.alireza.picture.presentation.searchPhoto.SearchPhotoFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentPhotoFragment : BaseObserverFragment<FragmentRecentPhotoBinding>() {

    private val recentPhotoViewModel: RecentPhotoViewModel by viewModels()
    private val recentPhotoAdapter:RecentPhotoListAdapter by lazy { RecentPhotoListAdapter() }


    override fun donOnCreateView() {
        setupPhotoAdapterListener()
    }

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
        with(binding){
            swipeToRefresh.isRefreshing = false
            swipeToRefresh.isEnabled = false
            progressLoading.isVisible = loading
            if (loading) {
                emptyState.isVisible = false
                errorState.isVisible = false
            }
        }
    }

    private fun showError(data: Throwable) {
        //TODO Error handling with costume Classes
        with(binding){
            showLoading(false)
            emptyState.isVisible = false
            errorState.isVisible = true
            swipeToRefresh.isRefreshing = false
            swipeToRefresh.isEnabled = true
            errorState.setCaption(data.localizedMessage)
        }
    }

    private fun showError(message: String) {
        with(binding){
            showLoading(false)
            emptyState.isVisible = false
            errorState.isVisible = true
            swipeToRefresh.isRefreshing = false
            swipeToRefresh.isEnabled = true
            errorState.setCaption(message)
        }
    }

    private fun showRecentPhoto(photoList: List<RecentPhoto>) {
        with(binding){
            showLoading(false)
            swipeToRefresh.isRefreshing = false
            swipeToRefresh.isEnabled = true
            emptyState.isVisible = photoList.isEmpty()
            rvPhotoList.isVisible = binding.emptyState.isVisible.not()
            recentPhotoAdapter.addPhoto(photoList)
        }
    }

    override fun getViewBinding(): FragmentRecentPhotoBinding =
        FragmentRecentPhotoBinding.inflate(layoutInflater)

    override fun setupViews() {
        initializeRecentPhotoListRecyclerView()
        setupListeners()
        setupSwipeToRefresh()
    }

    private fun setupListeners() {
        emptyStateListener()
        errorStateListener()
        searchClickListener()
        favoriteClickListener()
    }

    private fun searchClickListener() {
        binding.imgSearch.setOnClickListener {
            RecentPhotoFragmentDirections.actionRecentPhotoFragmentToSearchPhotoFragment().also {action ->
                safeNavigation(action, R.id.recentPhotoFragment )
            }
        }
    }

    private fun favoriteClickListener() {
        binding.imgFavorite.setOnClickListener {
            RecentPhotoFragmentDirections.actionRecentPhotoFragmentToFavoritePhotoFragment().also {action ->
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

    private fun setupPhotoAdapterListener() {
        recentPhotoAdapter.onPhotoClick = { photoId,url ->
            RecentPhotoFragmentDirections.actionRecentPhotoFragmentToPhotoDetailFragment(photoId,url)
                .also {
                    safeNavigation(it, R.id.recentPhotoFragment)
                }
        }
    }

    private fun setupSwipeToRefresh() {
        with(binding){
            swipeToRefresh.isRefreshing = false
            swipeToRefresh.isEnabled = false
            swipeToRefresh.setOnRefreshListener {
                recentPhotoViewModel.loadRecentPhoto()
            }
        }
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