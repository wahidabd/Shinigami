package com.wahidabd.shinigami.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wahidabd.library.utils.common.getApplicationSignature
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.shinigami.R
import java.io.File
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date


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

fun View.visibleIf(condition: () -> Boolean) {
    if (this.visibility != View.FOCUSABLES_ALL && condition.invoke()) {
        this.visible()
    }
}

fun Context.circularProgress(): CircularProgressDrawable {
    val circular = CircularProgressDrawable(this)
    circular.setColorSchemeColors(ContextCompat.getColor(this, R.color.lightGray))
    circular.strokeWidth = 10f
    circular.centerRadius = 40f
    circular.start()

    return circular
}

fun ImageView.setImageReader(image: String, progressDrawable: CircularProgressDrawable) {

    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    Glide.with(this.context)
        .load(image)
        .apply(requestOptions)
        .placeholder(progressDrawable)
        .downsample(DownsampleStrategy.AT_MOST)
        .override(Target.SIZE_ORIGINAL)
        .into(this)
}

fun View.animateTranslationY(alpha: Float) {
    this.animate()
        .translationY(this.height.toFloat())
        .alpha(alpha)
        .duration = 200
}

@RequiresApi(Build.VERSION_CODES.O)
fun currentTimestamp(): String {
    val date = DateTimeFormatter
        .ofPattern("yyyy/MM/dd HH:mm")
        .withZone(ZoneId.systemDefault())
        .format(Instant.now())

    return date.toString()
}

fun showMaterialAlert(
    context: Context,
    title: String,
    message: String,
    positiveButton: ((DialogInterface) -> Unit)? = null,
    negativeButton: ((DialogInterface) -> Unit)? = null
): MaterialAlertDialogBuilder {
    return MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setNegativeButton("Cancel") { dialog, _ ->
            negativeButton?.invoke(dialog)
        }
        .setPositiveButton("OK") { dialog, _ ->
            positiveButton?.invoke(dialog)
        }
}


fun TextView.greeting() {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR)

    this.text = when (currentHour) {
        in 0..11 -> resources.getString(R.string.label_morning)
        in 12 .. 15 -> resources.getString(R.string.label_afternoon)
        in 16 .. 21 -> resources.getString(R.string.label_afternoon)
        in 21 .. 23 -> resources.getString(R.string.label_afternoon)
        else -> ""
    }
}