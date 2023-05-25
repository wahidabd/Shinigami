package com.wahidabd.shinigami.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.getCompatColor
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
        type = attributes.getInteger(R.styleable.MyToolbar_toolbarType, 0)
            .let { ToolbarType.values()[it] }
        attributes.recycle()
    }

    private fun setupView() {
        with(binding) {
            this@MyToolbar.toolbar = toolbar
            tvTitle.text = title

            when(type){
                ToolbarType.PRIMARY -> setBackgroundPrimary()
                ToolbarType.TRANSPARANT -> setBackgroundTransparant()
                ToolbarType.GRAYOPACITY -> setBackgroundGrayOpacity()
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

    private fun setBackgroundGrayOpacity() {
        binding.toolbar.apply {
            setBackgroundColor(context.getColor(R.color.darkGrayOpacity))
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
            setBackgroundColor(context.getColor(R.color.darkGray))
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
        binding.toolbar.setNavigationOnClickListener {
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
        binding.iconSecondary.onClick {
            onClick?.invoke()
        }
    }

    enum class ToolbarType {
        PRIMARY,
        TRANSPARANT,
        GRAYOPACITY,
    }

}