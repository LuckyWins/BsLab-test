package ru.bslab.test.features.base

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import ru.bslab.test.R

class LoadingDialog private constructor(internal var activity: Activity) {

    private var isShowing = false
    internal var alertDialog: AlertDialog? = null

    fun show(): LoadingDialog {
        if (!isShowing && alertDialog != null) {
            isShowing = true
            try {
                alertDialog!!.window!!.setBackgroundDrawable(ColorDrawable(0))
                //alertDialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimations
            } catch (e: Exception) {

            }

            alertDialog!!.show()
        }
        return this
    }

    fun hide(): LoadingDialog {
        isShowing = false
        if (alertDialog != null)
            alertDialog!!.dismiss()
        return this
    }

    companion object {

        operator fun get(activity: Activity): LoadingDialog {
            val dialog = LoadingDialog(activity)
            val builder = AlertDialog.Builder(dialog.activity)
            val v = LayoutInflater.from(dialog.activity).inflate(R.layout.view_fullscreen_progress, null)
            builder.setView(v)
            builder.setCancelable(false)
            dialog.alertDialog = builder.create()
            return dialog
        }
    }
}