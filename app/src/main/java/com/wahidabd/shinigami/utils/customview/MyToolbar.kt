package com.wahidabd.shinigami.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.core.view.setMargins
import com.google.android.material.appbar.MaterialToolbar
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.LayoutMyToolbarBinding


/**
 * Created by Wahid on 5/20/2023.
 * Github github.com/wahidabd.
 */


class MyToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutMyToolbarBinding

    private var imgMain = 0
    private var imgSecond = 0
    private var title = emptyString()
    private var type: ToolbarType = ToolbarType.PRIMARY
    private var isNavigationBackShow: Boolean = true

    private lateinit var toolbar: MaterialToolbar

    init {
        binding = LayoutMyToolbarBinding.inflate(LayoutInflater.from(context), this)
        setupAttributes(attrs)
        setupView()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.MyToolbar, 0, 0)
        imgMain = attributes.getResourceId(R.styleable.MyToolbar_imgMain, 0)
        imgSecond = attributes.getResourceId(R.styleable.MyToolbar_imgSecond, 0)
        title = attributes.getString(R.styleable.MyToolbar_title).orEmpty()
        isNavigationBackShow = attributes.getBoolean(R.styleable.MyToolbar_isNavigationEnable, true)
        type = attributes.getInteger(R.styleable.MyToolbar_toolbarType, 0)
            .let { ToolbarType.values()[it] }
        attributes.recycle()
    }

    private fun setupView() {
        with(binding) {
            this@MyToolbar.toolbar = toolbar
            tvTitle.text = title
            toolbar.navigationIcon = null
            iconSecondary.setImageResource(imgSecond)

            if (!isNavigationBackShow) iconBack.gone()
            else iconBack.visible()

            when(type){
                ToolbarType.PRIMARY -> setBackgroundPrimary()
                ToolbarType.TRANSPARANT -> setBackgroundTransparant()
                ToolbarType.OPACITY -> setBackgroundOpacity()
            }
        }
    }

    fun setTitle(title: String){
        binding.tvTitle.text = title
    }

    fun enableTitle(state: Boolean){
        if (state) binding.tvTitle.visible()
        else binding.tvTitle.gone()
    }

    private fun setBackgroundOpacity() {
        binding.toolbar.apply {
            setBackgroundColor(context.getColor(R.color.primaryOpacity))
            binding.tvTitle.apply {
                visible()
                setTextColor(context.getColor(R.color.white))
            }
        }
    }

    private fun setBackgroundTransparant() {
        binding.toolbar.apply {
            background = null
            binding.tvTitle.gone()
        }
    }

    private fun setBackgroundPrimary() {
        binding.toolbar.apply {
            setBackgroundColor(context.getColor(R.color.darkBlue))
            binding.tvTitle.apply {
                visible()
                setTextColor(context.getColor(R.color.white))
            }
        }
    }

    fun setBackgroundColorResource(@ColorRes color: Int? = null){
        if (color == null) toolbar.background = null
        else toolbar.setBackgroundColor(ContextCompat.getColor(context, color))
    }

    fun setEnableBack(onClick: (() -> Unit)?){
        binding.iconBack.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_left))
        binding.iconBack.onClick {
            onClick?.invoke()
        }
    }

    fun setImageMainEnable(onClick: (() -> Unit)?) = with(binding.iconMain){
        visible()
        onClick {
            onClick?.invoke()
        }
    }

    fun setIconMain(@DrawableRes icon: Int) = with(binding.iconMain){
        visible()
        setImageDrawable(ContextCompat.getDrawable(context, icon))
    }

    fun setImageSecondaryEnable(onClick: (() -> Unit)?) {
        binding.iconSecondary.visible()
        binding.iconSecondary.onClick {
            onClick?.invoke()
        }
    }

    enum class ToolbarType {
        PRIMARY,
        TRANSPARANT,
        OPACITY,
    }

}