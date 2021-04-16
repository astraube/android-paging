package com.example.android.codelabs.paging

import android.view.View

/**
 * Created on 16/04/2021
 * @author André Straube
 *
 * Security to avoid simultaneous double-clicking
 */
@PublishedApi
internal object Debounce {

    @Volatile private var enabled: Boolean = true
    private val enableAgain = Runnable { enabled = true }

    fun canPerform(view: View): Boolean {
        if (enabled) {
            enabled = false
            view.post(enableAgain)
            return true
        }
        return false
    }
}

@PublishedApi
internal fun <T : View> T.onClickDebounced(click: (view: T) -> Unit) {
    setOnClickListener {
        if (Debounce.canPerform(it)) {
            @Suppress("UNCHECKED_CAST")
            click(it as T)
        }
    }
}