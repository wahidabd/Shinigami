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


/**
 * Created by Wahid on 4/19/2023.
 * Github github.com/wahidabd.
 */


class HeaderDetail @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var binding: LayoutHeaderDetailBinding private set

    @DrawableRes
    var imgLeft = 0

    @DrawableRes
    var imgRight1 = 0

    @DrawableRes
    var imgRight2 = 0

    @DrawableRes
    var imgRight3 = 0

    private var onIconLeftClicked: (() -> Unit)? = null
    private var onIconRight1Clicked: (() -> Unit)? = null
    private var onIconRight2Clicked: (() -> Unit)? = null
    private var onIconRight3Clicked: (() -> Unit)? = null

    init {
        binding = LayoutHeaderDetailBinding.inflate(LayoutInflater.from(context), this)
        setupAttributes(attrs)
        setupView()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.HeaderDetail, 0, 0)
        imgLeft = attributes.getResourceId(R.styleable.HeaderDetail_iconLeft, 0)
        imgRight1 = attributes.getResourceId(R.styleable.HeaderDetail_iconRight1, 0)
        imgRight2 = attributes.getResourceId(R.styleable.HeaderDetail_iconRight2, 0)
        imgRight3 = attributes.getResourceId(R.styleable.HeaderDetail_iconRight3, 0)
        attributes.recycle()
    }

    private fun setupView() {
        with(binding) {

            if (imgLeft != 0) {
                imgIconLeft.apply {
                    setBackgroundResource(imgLeft)
                    onClick {
                        onIconLeftClicked?.invoke()
                    }
                }
            }

            if (imgRight1 != 0) {
                imgIconRight1.apply {
                    setBackgroundResource(imgRight1)
                    onClick {
                        onIconRight1Clicked?.invoke()
                    }
                }
            }

            if (imgRight2 != 0) {
                imgIconRight2.apply {
                    setBackgroundResource(imgRight2)
                    onClick {
                        onIconRight2Clicked?.invoke()
                    }
                }
            }

            if (imgRight2 != 0) {
                imgIconRight2.apply {
                    setBackgroundResource(imgRight2)
                    onClick {
                        onIconRight2Clicked?.invoke()
                    }
                }
            }

        }
    }

    fun setLeftButton(onClicked: (() -> Unit)) {
        onIconLeftClicked = onClicked
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
//
//            genres?.forEach {
//                tvGenre.append("$it, ")
//            }

            imgPoster.setImageUrl(context, poster)
            imgBanner.setImageUrl(context, poster)
        }
    }

}