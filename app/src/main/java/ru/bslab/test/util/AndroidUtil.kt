package ru.bslab.test.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.Serializable
import java.security.MessageDigest

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

fun String.toSha256(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}

data class Squad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {

    fun isNotEmpty(): Boolean {
        return (first as String).isNotEmpty() && (second as String).isNotEmpty() && (third as String).isNotEmpty() && (fourth as String).isNotEmpty()
    }

}

fun Triple<String, String, String>.isNotEmpty(): Boolean {
    return first.isNotEmpty() && second.isNotEmpty() && third.isNotEmpty()
}