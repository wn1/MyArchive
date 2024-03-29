package com.qdev.myarchive.ui.mvvm

import androidx.lifecycle.ViewModel
import java.io.Serializable

class FileLViewModel : ViewModel() {

    class Parameters : Serializable

    var firstLoad = true

    lateinit var parameters: Parameters

    fun bind (parameters: Parameters) {
        this.parameters = parameters
        if (firstLoad) {
            firstLoad = false
            firstLoad()
        }
    }

    private fun firstLoad() {

    }
}