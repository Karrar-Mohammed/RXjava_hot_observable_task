package com.example.rxjavahotobservable.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.setFragmentResult
import com.example.rxjavahotobservable.base.BaseFragment
import com.example.rxjavahotobservable.databinding.FragmentFirstBinding
import com.example.rxjavahotobservable.util.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentFirstBinding
        get() = FragmentFirstBinding::inflate

    override fun setup() {
        createObservable()
    }

    private fun createObservable() {
        val observable = Observable.create<String> { emitter ->
            binding.inputText.doOnTextChanged { text, _, _, _ ->
                emitter.onNext(text.toString())
            }
        }.debounce(1500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).publish()
        observable.connect()

        observable.subscribeBy(
            onError = { Log.i(TAG, "fail: ${it.message}") },
            onNext = { sendTextToSecondFragment(it) }
        )

    }

    private fun sendTextToSecondFragment(text: String) {
        setFragmentResult(Constants.REQUEST_KEY, bundleOf(Constants.BUNDLE_KEY to text))
    }

    companion object {
        const val TAG = "KARRAR"
    }


}