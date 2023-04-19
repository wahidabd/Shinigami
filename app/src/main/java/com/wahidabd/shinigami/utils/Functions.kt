package com.wahidabd.shinigami.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.shinigami.R


/**
 * Created by Wahid on 4/10/2023.
 * Github github.com/wahidabd.
 */


fun TextView.setTypeBackground(context: Context, type: String) {
    this.text = type
    when (type) {
        "Manhwa" -> this.background = ContextCompat.getDrawable(context, R.drawable.bg_manhwa)
        "Manhua" -> this.background = ContextCompat.getDrawable(context, R.drawable.bg_manhua)
        "Manga" -> this.background = ContextCompat.getDrawable(context, R.drawable.bg_manga)
        "" -> this.gone()
        else -> this.background = ContextCompat.getDrawable(context, R.drawable.bg_manhwa)
    }
}

fun View.visibleIf(condition: () -> Boolean){
    if (this.visibility != View.FOCUSABLES_ALL && condition.invoke()){
        this.visible()
    }
}