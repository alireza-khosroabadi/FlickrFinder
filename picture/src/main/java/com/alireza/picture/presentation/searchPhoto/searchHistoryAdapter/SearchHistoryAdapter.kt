package com.alireza.picture.presentation.searchPhoto.searchHistoryAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alireza.picture.databinding.ListItemSearchHistoryLayoutBinding
import com.alireza.picture.domain.model.searchHistory.SearchHistory

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    private var searchHistoryList = mutableListOf<SearchHistory>()
    private var inflater: LayoutInflater? = null
    var listener: SearchHistoryListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.context)
        return ListItemSearchHistoryLayoutBinding.inflate(inflater!!, parent, false).run {
            ViewHolder(this)
        }
    }

    override fun getItemCount(): Int = searchHistoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(searchHistoryList[position])
    }

    fun addSearchHistory(itemList: List<SearchHistory>) {
        searchHistoryList = itemList.toMutableList()
        notifyDataSetChanged()
    }

    fun addSearchHistory(item: SearchHistory) {
        searchHistoryList.add(item)
        notifyItemInserted(searchHistoryList.size - 1)
    }


    inner class ViewHolder(private val binding: ListItemSearchHistoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: SearchHistory) {
            binding.tvTitle.text = item.query
            binding.containerLayout.setOnClickListener {
                listener?.onSearchHistoryItemClick(item)
            }
            binding.imgRemoveHistory.setOnClickListener {
                listener?.onRemoveHistoryClick(item)
            }
        }
    }
}