package com.qdev.myarchive.ui.mvvm.pattern;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qdev.myarchive.R;

public class _PatternJavaFragment extends Fragment
        implements _PatternJavaViewModel._PatternJavaView {

    private _PatternJavaViewModel mViewModel;

    public static _PatternJavaFragment newInstance(_PatternJavaViewModel.Parameters parameters) {
            _PatternJavaFragment fragment = new _PatternJavaFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("p", parameters);
            fragment.setArguments(bundle);
        return fragment;
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
        mViewModel.bind((_PatternJavaViewModel._PatternJavaMessagesView) getActivity(),
                this ,(_PatternJavaViewModel.Parameters) getArguments().get("p")
        );
    }

    @Override
    public void hideView() {

    }
}