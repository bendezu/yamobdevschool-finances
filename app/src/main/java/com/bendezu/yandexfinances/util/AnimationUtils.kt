package com.bendezu.yandexfinances.util

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils

fun registerCircularRevealAnimation(context: Context, view: View, revealSettings: RevealAnimationSetting,
                                    startColor: Int, endColor: Int) {
    view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int,
                           oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            v.removeOnLayoutChangeListener(this)
            val duration = context.resources.getInteger(android.R.integer.config_mediumAnimTime)
            val cx = revealSettings.centerX
            val cy = revealSettings.centerY
            val width = revealSettings.width
            val height = revealSettings.height

            val finalRadius = Math.hypot(width.toDouble(),height.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius).setDuration(duration.toLong())
            anim.interpolator = FastOutSlowInInterpolator()
            anim.start()
            startColorAnimation(view, startColor, endColor, duration)
        }
    })
}

internal fun startColorAnimation(view: View, startColor: Int, endColor: Int, duration: Int) {
    val anim = ValueAnimator()
    anim.setIntValues(startColor, endColor)
    anim.setEvaluator(ArgbEvaluator())
    anim.addUpdateListener { valueAnimator -> view.setBackgroundColor(valueAnimator.animatedValue as Int) }
    anim.duration = duration.toLong()
    anim.start()
}

class RevealAnimationSetting(
    val centerX: Int,
    val centerY: Int,
    val width: Int,
    val height: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(centerX)
        parcel.writeInt(centerY)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RevealAnimationSetting> {
        override fun createFromParcel(parcel: Parcel): RevealAnimationSetting {
            return RevealAnimationSetting(parcel)
        }

        override fun newArray(size: Int): Array<RevealAnimationSetting?> {
            return arrayOfNulls(size)
        }
    }
}