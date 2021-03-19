package com.example.myfiles.ui.mvvm.pattern;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfiles.R;

public class _PatternJavaFragment extends Fragment {

    private _PatternJavaViewModel mViewModel;

    public static _PatternJavaFragment newInstance() {
        return new _PatternJavaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(_PatternJavaViewModel.class);
        // TODO: Use the ViewModel
    }

}