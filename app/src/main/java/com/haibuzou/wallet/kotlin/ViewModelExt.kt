@file:JvmName("ViewModelExt")
package com.haibuzou.wallet.kotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

fun <T : ViewModel> Fragment.obtainViewModel(viewModel: Class<T>) =
    ViewModelProviders.of(this).get(viewModel)

fun <T : ViewModel> FragmentActivity.obtainViewModel(viewModel: Class<T>) =
    ViewModelProviders.of(this).get(viewModel)
