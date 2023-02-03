package com.alireza.picture.presentation.recentPhoto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alireza.core.extentions.loadImage
import com.alireza.picture.databinding.ListItemRecentPhotoLayoutBinding
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto

class RecentPhotoListAdapter : RecyclerView.Adapter<RecentPhotoListAdapter.ViewHolder>() {

    private var photoList = listOf<RecentPhoto>()
    private var layoutInflater: LayoutInflater? = null
    var onPhotoClick: ((photoId:String,url:String)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (layoutInflater == null) layoutInflater = LayoutInflater.from(parent.context)
        return ListItemRecentPhotoLayoutBinding.inflate(layoutInflater!!, parent, false).run {
            ViewHolder(this)
        }
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(photoList[position])
    }


    fun addPhoto(newItems: List<RecentPhoto>) {
        photoList = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemRecentPhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: RecentPhoto) {
            binding.imgPhoto.loadImage(item.url, 400, 400)
            binding.imgPhoto.setOnClickListener {
                onPhotoClick?.invoke(item.id, item.urlLarge.ifEmpty { item.url })
            }
        }
    }
}