package com.example.myfiles.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.Serializable
import java.lang.ref.WeakReference

class _PatternKotlinViewModel : ViewModel() {

    interface _PatternKotlinMessagesView { //For activity implement messages in any fragments
        fun showMessage (message: String)
    }

    interface _PatternKotlinView { //For fragment implements
        fun showMessage (message: String)
    }

    class Event

    class Parameters : Serializable

    var firstLoad = true

    lateinit var parameters: Parameters

    var messagesView: WeakReference<_PatternKotlinMessagesView>? = null
    var view: WeakReference<_PatternKotlinView>? = null

    val event = MutableLiveData<Event>() //Event for fragment

    fun bind (messagesView: _PatternKotlinMessagesView,
              view: _PatternKotlinView,
              parameters: Parameters) {

        this.parameters = parameters
        this.messagesView = WeakReference(messagesView)

        if (firstLoad) {
            firstLoad = false
            firstLoad()
        }

    }

    private fun firstLoad() {

    }
}