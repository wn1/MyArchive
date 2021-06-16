package com.qdev.myarchive.ui.mvvm.pattern;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qdev.myarchive.helper.LateinitProperties;

import java.io.Serializable;
import java.lang.ref.WeakReference;

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