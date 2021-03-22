package com.example.myfiles.ui.mvvm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfiles.R

class _PatternKotlinFragment : Fragment(), _PatternKotlinViewModel._PatternKotlinView {

    companion object {
        fun newInstance() = FileListFragment()
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
                this,
                _PatternKotlinViewModel.Parameters()
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