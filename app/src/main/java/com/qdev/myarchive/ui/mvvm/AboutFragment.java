package com.qdev.myarchive.ui.mvvm;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qdev.myarchive.R;
import com.qdev.myarchive.ui.mvvm.pattern._PatternJavaFragment;

public class AboutFragment extends Fragment {

    private AboutViewModel mViewModel;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);
        // TODO: Use the ViewModel
    }

}