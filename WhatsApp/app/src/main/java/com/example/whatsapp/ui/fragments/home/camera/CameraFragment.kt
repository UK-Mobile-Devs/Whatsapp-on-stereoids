package com.example.whatsapp.ui.fragments.home.camera

import android.view.LayoutInflater
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentCameraBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraFragment : BaseFragment<FragmentCameraBinding>() {


    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentCameraBinding {
        return FragmentCameraBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {

    }
    //endregion


    //region Companion Object
    companion object {
        const val CAMERA_SELECTION_KEY = "CAMERA_SELECTION_KEY"

        fun newInstance(): CameraFragment {
            return CameraFragment()
        }
    }

    //endregion


}