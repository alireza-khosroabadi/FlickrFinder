package com.alireza.picture.presentation.searchPhoto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alireza.core.extentions.loadImage
import com.alireza.picture.databinding.ListItemSearchPhotoLayoutBinding
import com.alireza.picture.domain.model.searchPhoto.SearchPhoto

class SearchPhotoListAdapter : RecyclerView.Adapter<SearchPhotoListAdapter.ViewHolder>() {

    private var photoList = listOf<SearchPhoto>()
    private var layoutInflater: LayoutInflater? = null
    var onPhotoClick: ((photoId:String,url:String)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (layoutInflater == null) layoutInflater = LayoutInflater.from(parent.context)
        return ListItemSearchPhotoLayoutBinding.inflate(layoutInflater!!, parent, false).run {
            ViewHolder(this)
        }
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(photoList[position])
    }


    fun addPhoto(newItems: List<SearchPhoto>) {
        photoList = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemSearchPhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: SearchPhoto) {
            binding.imgPhoto.loadImage(item.url)
            binding.tvTitle.text = item.title
            binding.imgPhoto.setOnClickListener {
                onPhotoClick?.invoke(item.id, item.urlLarge.ifEmpty { item.url })
            }
        }
    }
}