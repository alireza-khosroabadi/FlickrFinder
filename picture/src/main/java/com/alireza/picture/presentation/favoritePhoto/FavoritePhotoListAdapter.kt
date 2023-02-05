package com.alireza.picture.presentation.favoritePhoto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alireza.core.extentions.loadImage
import com.alireza.picture.databinding.ListItemFavoritePhotoLayoutBinding
import com.alireza.picture.domain.model.photoDetail.PhotoDetail

class FavoritePhotoListAdapter : RecyclerView.Adapter<FavoritePhotoListAdapter.ViewHolder>() {

    private var favoritePhotoList = listOf<PhotoDetail>()
    private var inflater: LayoutInflater? = null
    var onPhotoClick: ((photoId: String, photoUrl: String) -> Unit)? = null
    var onPhotoLongClick: ((photoId: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.context)
        return ListItemFavoritePhotoLayoutBinding.inflate(inflater!!, parent, false).run {
            ViewHolder(this)
        }
    }

    override fun getItemCount(): Int = favoritePhotoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(favoritePhotoList[position])
    }


    fun addFavoritePhoto(listItem: List<PhotoDetail>) {
        favoritePhotoList = listItem
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemFavoritePhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PhotoDetail) {
            binding.imgPhoto.loadImage(item.url, 400, 400)
            setOnPhotoClickListener(item)
            setOnPhotoLongClickListener(item)
        }

        private fun setOnPhotoLongClickListener(item: PhotoDetail) {
            binding.imgPhoto.setOnLongClickListener {
                onPhotoLongClick?.invoke(item.id)
                true
            }
        }

        private fun setOnPhotoClickListener(item: PhotoDetail) {
            binding.imgPhoto.setOnClickListener {
                onPhotoClick?.invoke(item.id, item.url)
            }
        }
    }

}