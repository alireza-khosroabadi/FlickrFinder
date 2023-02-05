package com.alireza.picture.presentation.recentPhoto

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.alireza.core.data.error.AppError
import com.alireza.core.extentions.safeNavigation
import com.alireza.core.presentation.fragment.BaseObserverFragment
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.R
import com.alireza.picture.databinding.FragmentRecentPhotoBinding
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentPhotoFragment : BaseObserverFragment<FragmentRecentPhotoBinding>() {

    private val recentPhotoViewModel: RecentPhotoViewModel by viewModels()
    private val recentPhotoAdapter: RecentPhotoListAdapter by lazy { RecentPhotoListAdapter() }


    override fun donOnCreateView() {
        setupPhotoAdapterListener()
    }

    override fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch { observeRecentPhotoState() }
                launch { observeErrorState() }
            }
        }
    }

    private suspend fun observeRecentPhotoState() {
        recentPhotoViewModel.recentPhotoState.collect { data ->
            when (data) {
                Loading -> showLoading(true)
                is RecentPhotoList -> showRecentPhoto(data.photoList)
            }
        }
    }

    private suspend fun observeErrorState() {
        recentPhotoViewModel.errorState.collect { error ->
            when (error) {
                is ErrorState -> showError(error.message)
                is ExceptionState -> showError(error.error)
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        with(binding) {
            swipeToRefreshState(isRefreshing = false, isEnable = false)
            progressLoading.isVisible = loading
            if (loading) {
                emptyState.isVisible = false
                errorState.isVisible = false
            }
        }
    }

    private fun showError(data: AppError) {
        with(binding) {
            showLoading(false)
            emptyState.isVisible = false
            errorState.isVisible = true
            swipeToRefreshState(isRefreshing = false, isEnable = true)
            errorState.setAppError(data)
        }
    }

    private fun showError(message: String) {
        with(binding) {
            showLoading(false)
            emptyState.isVisible = false
            errorState.isVisible = true
            swipeToRefreshState(isRefreshing = false, isEnable = true)
            errorState.setCaption(message)
        }
    }

    private fun showRecentPhoto(photoList: List<RecentPhoto>) {
        with(binding) {
            showLoading(false)
            swipeToRefreshState(isRefreshing = false, isEnable = true)
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
            RecentPhotoFragmentDirections.actionRecentPhotoFragmentToSearchPhotoFragment()
                .also { action ->
                    safeNavigation(action, R.id.recentPhotoFragment)
                }
        }
    }

    private fun favoriteClickListener() {
        binding.imgFavorite.setOnClickListener {
            RecentPhotoFragmentDirections.actionRecentPhotoFragmentToFavoritePhotoFragment()
                .also { action ->
                    safeNavigation(action, R.id.recentPhotoFragment)
                }
        }
    }

    private fun initializeRecentPhotoListRecyclerView() {
        with(binding.rvPhotoList) {
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = recentPhotoAdapter
        }
    }

    private fun setupPhotoAdapterListener() {
        recentPhotoAdapter.onPhotoClick = { photoId, url ->
            RecentPhotoFragmentDirections.actionRecentPhotoFragmentToPhotoDetailFragment(
                photoId,
                url
            )
                .also {
                    safeNavigation(it, R.id.recentPhotoFragment)
                }
        }
    }

    private fun setupSwipeToRefresh() {
        with(binding) {
            swipeToRefreshState(isRefreshing = false, isEnable = false)
            swipeToRefresh.setOnRefreshListener {
                recentPhotoViewModel.loadRecentPhoto(true)
            }
        }
    }

    private fun swipeToRefreshState(isRefreshing: Boolean, isEnable: Boolean) {
        binding.swipeToRefresh.isRefreshing = isRefreshing
        binding.swipeToRefresh.isEnabled = isEnable
    }


    private fun emptyStateListener() {
        binding.emptyState.setOnButtonClickListener {
            recentPhotoViewModel.loadRecentPhoto(true)
        }
    }

    private fun errorStateListener() {
        binding.errorState.setOnButtonClickListener {
            recentPhotoViewModel.loadRecentPhoto(true)
        }
    }
}