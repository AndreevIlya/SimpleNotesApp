package com.example.simplenotesapp.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.core.view.inputmethod.EditorInfoCompat
import androidx.core.view.inputmethod.InputConnectionCompat
import androidx.core.view.inputmethod.InputContentInfoCompat
import com.google.android.material.textfield.TextInputEditText
import java.lang.RuntimeException


class EmojiEditText : TextInputEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    lateinit var keyBoardInputCallbackListener: KeyBoardInputCallbackListener

    override fun onCreateInputConnection(outAttrs: EditorInfo?): InputConnection {
        if (outAttrs == null) throw RuntimeException("No editor info!")
        val ic = super.onCreateInputConnection(outAttrs)
        EditorInfoCompat.setContentMimeTypes(outAttrs, mimeTypes)
        return InputConnectionCompat.createWrapper(ic, outAttrs) { inputContentInfo, flags, opts ->
            if (flags and
                InputConnectionCompat.INPUT_CONTENT_GRANT_READ_URI_PERMISSION != 0
            ) {
                try {
                    inputContentInfo.requestPermission()
                } catch (e: Exception) {
                    return@createWrapper false
                }
            }
            var supported = false
            for (mimeType in mimeTypes) {
                if (inputContentInfo.description.hasMimeType(mimeType)) {
                    supported = true
                    break
                }
            }
            if (!supported) {
                return@createWrapper false
            }

            keyBoardInputCallbackListener.onCommitContent(inputContentInfo, flags, opts)
            true
        }
    }

    interface KeyBoardInputCallbackListener {
        fun onCommitContent(
            inputContentInfo: InputContentInfoCompat?,
            flags: Int, opts: Bundle?
        )
    }

    private companion object {
        val mimeTypes = arrayOf(
            "image/png",
            "image/gif",
            "image/jpeg",
            "image/webp"
        )
    }
}