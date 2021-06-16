package com.qdev.myarchive.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qdev.myarchive.helper.ViewsWithLifecycleDispatcher
import java.io.Serializable
import java.lang.ref.WeakReference

//MIT License
//
//Copyright (c) Vladimir Kudashov
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.
//
//E-mail: wn1soft@gmail.com
//

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