package com.qdev.myarchive.ui.mvvm.pattern;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qdev.myarchive.helper.LateinitProperties;

import java.io.Serializable;
import java.lang.ref.WeakReference;

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

public class _PatternJavaViewModel extends ViewModel {

    interface _PatternJavaMessagesView { //For activity implement messages in any fragments
        void showMessage (String message);
    }

    interface _PatternJavaView { //For fragment implements
        void hideView ();
    }

    class Event {

    }

    // TODO: Implement the ViewModel
    class Parameters implements Serializable {

    }

    private Boolean firstLoad = true;

    private LateinitProperties parameters = new LateinitProperties<Parameters>();

    @Nullable
    private WeakReference<_PatternJavaMessagesView> messagesView = null;
    @Nullable
    private WeakReference<_PatternJavaView> view = null;
    @Nullable
    private MutableLiveData event = null; //Event for fragment

    public void bind(_PatternJavaMessagesView messagesView,
                     _PatternJavaView view,
                     Parameters parameters) {
        this.messagesView = new WeakReference(messagesView);
        this.view = new WeakReference(view);
        this.parameters.set(parameters);
        if (firstLoad) {
            firstLoad = false;
            firstLoad();
        }
    }

    private void firstLoad() {

    }

}