package com.qdev.myarchive.ui.mvvm.pattern

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qdev.myarchive.R
import com.qdev.myarchive.ui.mvvm.FileLFragment
import com.qdev.myarchive.ui.mvvm._PatternKotlinViewModel

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

class _PatternKotlinFragment : Fragment(), _PatternKotlinViewModel._PatternKotlinView {

    companion object {

        fun newInstance(parameters: _PatternKotlinViewModel.Parameters) = FileLFragment().also {
            it.arguments = Bundle().apply {
                putSerializable("p", parameters)
            }
        }

    }

    private lateinit var viewModel: _PatternKotlinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.file_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(_PatternKotlinViewModel::class.java)
        viewModel.bind(activity as _PatternKotlinViewModel._PatternKotlinMessagesView,
            this, arguments?.get("p") as _PatternKotlinViewModel.Parameters
        )
    }

    override fun showHint() {
        TODO("Not yet implemented")
    }

    override fun showItemInfo() {
        TODO("Not yet implemented")
    }

    override fun hideView() {
        fragmentManager?.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.unbind()
    }
}