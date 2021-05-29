package com.qdev.myarchive.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qdev.myarchive.helper.ViewsWithLifecycleDispatcher
import java.io.Serializable
import java.lang.ref.WeakReference

class _PatternKotlinViewModel : ViewModel() {

    interface _PatternKotlinMessagesView { //For activity implement messages in any fragments
        fun showMessage (message: String)
    }

    interface _PatternKotlinView { //For fragment implements
        fun showHint()
        fun showItemInfo()
        fun hideView()
    }

    class Parameters : Serializable

    var firstLoad = true

    lateinit var parameters: Parameters

    var messagesView: WeakReference<_PatternKotlinMessagesView>? = null

    val liveDataField = MutableLiveData<Int>() //Event for fragment

    val viewDispatcher =
            ViewsWithLifecycleDispatcher<_PatternKotlinView>() //Events queue for fragment
    val messageDispatcher =
            ViewsWithLifecycleDispatcher<_PatternKotlinMessagesView>() //Events queue for activity

    fun bind (messagesView: _PatternKotlinMessagesView,
              view: _PatternKotlinView,
              parameters: Parameters) {
        //observe dispatcher to view lifecycle event
        viewDispatcher.addView(view)
        messageDispatcher.addView(messagesView)

        this.parameters = parameters
        this.messagesView = WeakReference(messagesView)

        if (firstLoad) {
            firstLoad = false
            firstLoad()
        }
    }

    fun unbind() {
        viewDispatcher.removeAllView()
    }

    private fun firstLoad() {
        viewDispatcher.dispatch {
            it.showHint()
        }
    }
}