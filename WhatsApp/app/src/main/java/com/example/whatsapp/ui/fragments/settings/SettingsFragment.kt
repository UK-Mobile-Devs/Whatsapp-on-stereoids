package com.example.whatsapp.ui.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.navigation.NavController
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        binding.imageView.setOnClickListener{
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            val ACTIVITY_SELECT_IMAGE = 1234
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE)
        }
    }

    //region BaseFragment Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }
    override fun observeViewModel() {

    }
}