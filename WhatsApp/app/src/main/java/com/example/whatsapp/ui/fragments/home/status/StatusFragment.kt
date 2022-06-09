package com.example.whatsapp.ui.fragments.home.status

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.example.whatsapp.R
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

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)
    }

    //endregion

    //region Options Menu

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_status_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemSettings -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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