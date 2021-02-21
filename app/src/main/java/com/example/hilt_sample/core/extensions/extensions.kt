package com.example.hilt_sample.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText

fun EditText.addOnTextChange(func: (CharSequence?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            func(s)
        }


    })
}

fun EditText.clearText(){
    this.text.clear()
}


