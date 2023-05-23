package com.wahidabd.shinigami.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.shinigami.R
import com.wahidabd.shinigami.databinding.LayoutHeaderDetailBinding


class  HeaderDetail @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var binding: LayoutHeaderDetailBinding private set

    init {
        binding = LayoutHeaderDetailBinding.inflate(LayoutInflater.from(context), this)
        setupAttributes(attrs)
        setupView()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.HeaderDetail, 0, 0)
        attributes.recycle()
    }

    private fun setupView() {
        with(binding) {
        }
    }

    fun setInformation(
        title: String,
        author: String,
        status: String,
        poster: String,
        genres: List<String>?,
        banner: String? = emptyString()
    ) {
        with(binding) {
            tvTitle.text = title
            tvAuthor.text = author
            tvStatus.text = status

            imgPoster.setImageUrl(context, poster)
            imgBanner.setImageUrl(context, poster)
        }
    }

}