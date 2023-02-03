package com.alireza.core.view.emptyState

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.alireza.core.R
import com.alireza.core.data.error.AppError
import com.alireza.core.databinding.EmptyStateViewBinding


/**
 * EmptyState is a custom view to show empty state or error state
 * */

@StringRes
private var DEFAULT_HINT: Int = R.string.empty_state_view_hint

@DrawableRes
private var DEFAULT_ICON: Int = R.drawable.ic_empty_state

@StringRes
private var DEFAULT_BUTTON_TEXT: Int = R.string.empty_state_view_btn

private var DEFAULT_SHOW_ICON: Boolean = true

private var DEFAULT_SHOW_HINT: Boolean = true

private var DEFAULT_SHOW_BUTTON: Boolean = true


class EmptyStateView @JvmOverloads constructor(
    ctx: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(ctx, attributeSet, defStyleAttr, defStyleRes) {

    private val mBinding: EmptyStateViewBinding by lazy {
        EmptyStateViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private var onButtonClick : OnClickListener? = null

    private fun initAttribute(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val attribute = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EmptyStateView,
            defStyleAttr,
            defStyleRes
        )
        try {
            attribute.getResourceId(R.styleable.EmptyStateView_hint, DEFAULT_HINT).let { resId ->
                mBinding.emptyStateTitle.text = context.getString(resId)
            }
            attribute.getResourceId(R.styleable.EmptyStateView_img, DEFAULT_ICON).let { resId ->
                mBinding.emptyStateIcon.setImageResource(resId)
            }
            attribute.getResourceId(R.styleable.EmptyStateView_buttonText, DEFAULT_BUTTON_TEXT).let { resId ->
                mBinding.emptyStateViewButton.text = context.getString(resId)
            }
            attribute.getBoolean(R.styleable.EmptyStateView_showImage, DEFAULT_SHOW_ICON).let { isShow ->
                mBinding.emptyStateIcon.isVisible = isShow
            }
            attribute.getBoolean(R.styleable.EmptyStateView_hint, DEFAULT_SHOW_HINT).let { isShow ->
                mBinding.emptyStateTitle.isVisible = isShow
            }
            attribute.getBoolean(R.styleable.EmptyStateView_showButton, DEFAULT_SHOW_BUTTON).let { isShow ->
                mBinding.emptyStateViewButton.isVisible = isShow
            }

        } finally {
            attribute.recycle()
        }
    }

    init {
        initAttribute(attributeSet, defStyleAttr, defStyleRes)
        setupListener()
    }

    private fun setupListener() {
        mBinding.emptyStateViewButton.setOnClickListener { view->
            onButtonClick?.onClick(view)
        }
    }


    fun setOnButtonClickListener(onClickListener: OnClickListener){
        this.onButtonClick = onClickListener
    }

    fun setTitle(@StringRes resId:Int){
        mBinding.emptyStateTitle.setText(resId)
    }

    fun setTitle(hint:String){
        mBinding.emptyStateTitle.text = hint
    }

    fun setCaption(@StringRes resId:Int){
        mBinding.emptyStateCaption.setText(resId)
    }

    fun setCaption(hint:String){
        mBinding.emptyStateCaption.text = hint
    }

    fun setIcon(@DrawableRes resId:Int){
        mBinding.emptyStateIcon.setImageResource(resId)
    }

    /**
     * For this method and error type we should expand it to load more types and diffrence between error types.
     * */
    fun setAppError(appError: AppError){
        mBinding.emptyStateIcon.setImageResource(appError.icon)
        mBinding.emptyStateTitle.setText(appError.title)
    }

}