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