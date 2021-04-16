package com.example.android.codelabs.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * Created on 16/04/2021
 * @author André Straube
 */

/**
 * ComponentActivity extension to initialize 'viewBinding' with by lazy
 */
@MainThread
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

/**
 * ComponentActivity extension to initialize 'viewModels' with by lazy
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModels(noinline factoryProducer: (() -> ViewModelProvider.Factory)): Lazy<VM> {
    return ViewModelLazy(VM::class, { viewModelStore }, factoryProducer)
}

/**
 * Context extension to 'hideKeyboard' in a 'view'
 */
fun Context.hideKeyboard(view: View, flags: Int = InputMethodManager.HIDE_NOT_ALWAYS) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, flags)
}