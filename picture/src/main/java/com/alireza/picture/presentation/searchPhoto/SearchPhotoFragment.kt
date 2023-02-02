package com.alireza.picture.presentation.searchPhoto

import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alireza.core.extentions.hideKeyBoard
import com.alireza.core.presentation.fragment.BaseObserverFragment
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.picture.databinding.FragmentSearchPhotoBinding
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.picture.domain.model.searchPhoto.SearchPhoto
import com.alireza.picture.presentation.searchPhoto.searchHistoryAdapter.SearchHistoryAdapter
import com.alireza.picture.presentation.searchPhoto.searchHistoryAdapter.SearchHistoryListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchPhotoFragment : BaseObserverFragment<FragmentSearchPhotoBinding>() {

    private var firstInit: Boolean = true
    private val mViewModel: SearchPhotoViewModel by viewModels()
    private val searchPhotoAdapter: SearchPhotoListAdapter by lazy { SearchPhotoListAdapter() }
    private val searchHistoryAdapter: SearchHistoryAdapter by lazy { SearchHistoryAdapter() }

    override fun donOnCreateView() {
        searchViewListener()
        onSearchIconClickListener()
        searchHistoryAdapterListener()
        setupOnBackClickListener()
        setupOnClearAllHistoryClickListener()
    }

    override fun observe() {
        observeSearchHistory()
        observeErrorState()
        observeSearchPhoto()
    }


    override fun getViewBinding(): FragmentSearchPhotoBinding =
        FragmentSearchPhotoBinding.inflate(layoutInflater)

    override fun setupViews() {
        setupSearchPhotoRecyclerView()
        setupSearchHistoryRecyclerView()
    }

    private fun searchViewListener() {
        binding.searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchPhoto()
                true
            }
            false
        }
    }

    private fun setupSearchHistoryRecyclerView() {
        with(binding.rvSearchHistory) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = searchHistoryAdapter
        }
    }

    private fun setupSearchPhotoRecyclerView() {
        with(binding.rvSearchPhotoList) {
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            adapter = searchPhotoAdapter
        }
    }

    private fun observeSearchHistory() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                mViewModel.searchHistoryState.collect { state ->
                    when (state) {
                        Loading -> Unit
                        is SearchHistoryList -> showHistory(state.lastHistory)
                    }
                }
            }
        }
    }

    private fun observeSearchPhoto() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                mViewModel.searchPhotoState.collect { state ->
                    when (state) {
                        is SearchPhotoList -> showPhotoList(state.photoList)
                        SearchPhotoLoading -> {
                            if (firstInit.not())
                                showLoading(true)
                            firstInit = false
                        }
                    }
                }
            }
        }
    }


    private fun observeErrorState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                mViewModel.errorState.collect { error ->
                    when (error) {
                        is ErrorState -> showError(error.message)
                        is ExceptionState -> showError(error.throwable)
                    }
                }
            }
        }
    }

    private fun showHistory(lastHistory: List<SearchHistory>) {
        binding.historyGroup.isVisible = lastHistory.isEmpty().not()
        searchHistoryAdapter.addSearchHistory(lastHistory)
    }

    private fun showLoading(loading: Boolean) {
        binding.progressLoading.isVisible = loading
        binding.rvSearchPhotoList.isVisible = false
        if (loading) {
            binding.emptyState.isVisible = false
            binding.errorState.isVisible = false
        }
    }

    private fun showPhotoList(photoList: List<SearchPhoto>) {
        showLoading(false)
        binding.historyGroup.isVisible = false
        binding.rvSearchPhotoList.isVisible = photoList.isEmpty().not()
        binding.emptyState.isVisible = photoList.isEmpty()
        searchPhotoAdapter.addPhoto(photoList)
    }

    private fun onSearchIconClickListener() {
        binding.searchViewContainer.setEndIconOnClickListener {
            searchPhoto()
        }

    }

    private fun searchPhoto() {
        if (binding.searchView.text.toString().isNotEmpty())
            searchPhoto(binding.searchView.text.toString())
    }

    private fun searchPhoto(query: String) {
        mViewModel.searchPhoto(query)
        showLoading(true)
        hideKeyBoard()
    }

    private fun searchHistoryAdapterListener() {
        searchHistoryAdapter.listener = object : SearchHistoryListener {
            override fun onSearchHistoryItemClick(searchItem: SearchHistory) {
                binding.searchView.setText(searchItem.query)
                searchPhoto(searchItem.query)
            }

            override fun onRemoveHistoryClick(searchHistory: SearchHistory) {
                mViewModel.removeHistory(searchHistory)
            }
        }
    }

    private fun setupOnClearAllHistoryClickListener() {
        binding.tvClearHistory.setOnClickListener {
            mViewModel.clearAllHistory()
        }
    }

    private fun setupOnBackClickListener() {
        binding.imgBack.setOnClickListener { findNavController().navigateUp() }
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
}