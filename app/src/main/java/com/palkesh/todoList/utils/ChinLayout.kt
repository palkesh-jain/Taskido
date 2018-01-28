package com.palkesh.todoList.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.FrameLayout

class ChinLayout : FrameLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        val chin = insets.systemWindowInsetBottom
        setPadding(0, 0, 0, chin)
        return insets
    }
}
