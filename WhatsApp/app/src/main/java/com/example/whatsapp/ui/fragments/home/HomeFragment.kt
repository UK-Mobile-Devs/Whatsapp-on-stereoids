package com.example.whatsapp.ui.fragments.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeFragmentVM>()


    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentHomeBinding {
       return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        val myRv = view?.findViewById(R.id.fabbtn) as FloatingActionButton

        myRv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_contactsFragment)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navigation, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initViews() {
        super.initViews()
    }

    override fun observeViewModel() {
        viewModel.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvBody.text = it.uid
            }.autoDispose()
    }
}