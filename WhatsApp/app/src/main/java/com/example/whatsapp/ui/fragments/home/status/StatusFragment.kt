package com.example.whatsapp.ui.fragments.home.status

import android.view.LayoutInflater
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentStatusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatusFragment : BaseFragment<FragmentStatusBinding>() {


    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentStatusBinding {
        return FragmentStatusBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {

    }
    //endregion


    //region Companion Object
    companion object {
        const val STATUS_SELECTION_KEY = "STATUS_SELECTION_KEY"

        fun newInstance(): StatusFragment {
            return StatusFragment()
        }
    }

    //endregion


}