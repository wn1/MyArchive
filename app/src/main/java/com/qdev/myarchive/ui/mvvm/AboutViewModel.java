package com.qdev.myarchive.ui.mvvm;

import androidx.lifecycle.ViewModel;

import com.qdev.myarchive.helper.LateinitProperties;

public class AboutViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    class Parameters {

    }

    Boolean firstLoad = true;

    LateinitProperties parameters = new LateinitProperties<Parameters>();

    public void bind(Parameters parameters) {
        this.parameters.set(parameters);
        if (firstLoad) {
            firstLoad = false;
            firstLoad();
        }
    }

    private void firstLoad() {

    }

}