package com.example.rxjavahotobservable.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.rxjavahotobservable.base.BaseFragment
import com.example.rxjavahotobservable.databinding.FragmentSecondBinding
import com.example.rxjavahotobservable.util.Constants

class SecondFragment: BaseFragment<FragmentSecondBinding>() {
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentSecondBinding
        get() = FragmentSecondBinding::inflate

    override fun setup() {
        setFragmentResultListener(Constants.REQUEST_KEY) { _, result ->
            val text = result.getString(Constants.BUNDLE_KEY)
            binding.textView.text = text
        }

    }




}