package com.example.whatsapp.ui.fragments.home.calls

import android.view.LayoutInflater
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentCallsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallsFragment : BaseFragment<FragmentCallsBinding>() {


    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentCallsBinding {
        return FragmentCallsBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {

    }
    //endregion


    //region Companion Object
    companion object {
        const val CALLS_SELECTION_KEY = "CALLS_SELECTION_KEY"

        fun newInstance(): CallsFragment {
            return CallsFragment()
        }
    }

    //endregion


}