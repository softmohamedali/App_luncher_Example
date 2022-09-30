package com.example.applauncherexample1.domain.models

import android.graphics.drawable.Drawable

data class AppObject(
    var name:String="",
    var packageName:String="",
    var image:Drawable,
    var isInPager:Boolean=false
) {
}