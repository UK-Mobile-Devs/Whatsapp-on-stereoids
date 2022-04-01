package com.example.whatsapp.ui.fragments.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel : HomeFragmentVM by viewModels()

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentHomeBinding {
       return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
    }

    override fun observeViewModel() {

        viewModel.outputs.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvBody.text = it.uid
            }.autoDispose()
    }
}