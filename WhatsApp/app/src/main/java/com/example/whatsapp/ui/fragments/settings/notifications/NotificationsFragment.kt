package com.example.whatsapp.ui.fragments.settings.notifications

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentNotificationsBinding
import com.example.whatsapp.utils.Constants.CALLS_VIBRATE_OPTION
import com.example.whatsapp.utils.Constants.GROUP_LIGHT_OPTION
import com.example.whatsapp.utils.Constants.GROUP_POP_UP_OPTION
import com.example.whatsapp.utils.Constants.GROUP_VIBRATE_OPTION
import com.example.whatsapp.utils.Constants.SINGLE_LIGHT_OPTION
import com.example.whatsapp.utils.Constants.SINGLE_POP_UP_OPTION
import com.example.whatsapp.utils.Constants.SINGLE_VIBRATE_OPTION
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(){


    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentNotificationsBinding {
        return FragmentNotificationsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiInteractions()

    }

    private fun initDialog(@StringRes title : Int, optionsList : Array<String>, checkedIndex : Int, optionSelected : String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(title))
        builder.setSingleChoiceItems(optionsList, checkedIndex) { dialog, _ ->
            (dialog as AlertDialog).listView.checkedItemPosition.let { position ->
                if(optionsList.size >= position){
                    when(optionSelected){
                        SINGLE_VIBRATE_OPTION -> binding.vibrateDesc.text = optionsList[position]
                        SINGLE_POP_UP_OPTION -> binding.tvPopUpTonesDesc.text = optionsList[position]
                        SINGLE_LIGHT_OPTION -> binding.tvLightDesc.text = optionsList[position]
                        GROUP_VIBRATE_OPTION -> binding.tvGroupVibrateDesc.text = optionsList[position]
                        GROUP_POP_UP_OPTION -> binding.tvPopUpGroupTonesDesc.text = optionsList[position]
                        GROUP_LIGHT_OPTION -> binding.tvLightGroupDesc.text = optionsList[position]
                        CALLS_VIBRATE_OPTION -> binding.tvCallsVibrateDesc.text = optionsList[position]
                    }
                }
            }

            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun observeUiInteractions() {
        //Message Layouts
        binding.vibrateLayout.setOnClickListener {
            val vibrates = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            initDialog(R.string.vibrate, vibrates, 0, SINGLE_VIBRATE_OPTION)
        }

        binding.popUpLayout.setOnClickListener {
            //todo get this from xml array and pass through constructor
            //todo get checkedIndex from sharedpreferences
            val popup = arrayOf(getString(R.string.nopopup), getString(R.string.screenon), getString(R.string.screenoff), getString(R.string.alwaysshow))
            initDialog(R.string.popupnotification, popup, 0, SINGLE_POP_UP_OPTION)
        }

        binding.lightLayout.setOnClickListener {
            val lights = arrayOf(getString(R.string.none), getString(R.string.white), getString(R.string.red), getString(R.string.yellow), getString(R.string.green), getString(R.string.cyan), getString(R.string.blue), getString(R.string.purple))
            initDialog(R.string.light, lights, 0, SINGLE_LIGHT_OPTION)
        }

        binding.highPriorityLayout.setOnClickListener {

        }

        //Group Layouts
        binding.groupVibrateLayout.setOnClickListener {
            val vibrate = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            initDialog(R.string.vibrate, vibrate, 0, GROUP_VIBRATE_OPTION)
        }
        binding.popUpGroupLayout.setOnClickListener {
            val popup = arrayOf(getString(R.string.nopopup), getString(R.string.screenon), getString(R.string.screenoff), getString(R.string.alwaysshow))
            initDialog(R.string.popupnotification, popup, 0, GROUP_POP_UP_OPTION)
        }

        binding.lightGroupLayout.setOnClickListener {
            val lights = arrayOf(getString(R.string.none), getString(R.string.white), getString(R.string.red), getString(R.string.yellow), getString(R.string.green), getString(R.string.cyan), getString(R.string.blue), getString(R.string.purple))
            initDialog(R.string.light, lights, 0,GROUP_LIGHT_OPTION)
        }
        //Calls Fragment
        binding.callsVibrateLayout.setOnClickListener {
            val vibration = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            initDialog(R.string.vibrate, vibration, 0,CALLS_VIBRATE_OPTION)
        }
    }

    override fun observeViewModel() {

    }
}