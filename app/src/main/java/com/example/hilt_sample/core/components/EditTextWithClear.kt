package com.example.hilt_sample.core.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText

class EditTextWithClear : AppCompatEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        setTouchBehavior()
    }

    private fun setTouchBehavior() {
        setOnTouchListener { _, event ->
            handleClearButtonClick(event)
            false
        }
    }

    private fun handleClearButtonClick(event: MotionEvent?) {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
               text?.clear()
            }
        }
    }

}
