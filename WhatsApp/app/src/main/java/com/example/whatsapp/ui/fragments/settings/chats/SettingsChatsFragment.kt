package com.example.whatsapp.ui.fragments.settings.chats

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentSettingsChatBinding
import com.example.whatsapp.utils.SPManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsChatsFragment : BaseFragment<FragmentSettingsChatBinding>() {

    @Inject
    lateinit var sharedPrefManager: SPManager

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentSettingsChatBinding {
        return FragmentSettingsChatBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
        observeUiInteractions()
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiInteractions()
        initializeUi()
    }
    private fun initializeUi(){
        if(sharedPrefManager.getCheckedInteger() == 0){
            binding.tvThemecolour.text = getString(R.string.lightmode)
        }
        if(sharedPrefManager.getCheckedInteger() == 1){
            binding.tvThemecolour.text = getString(R.string.darkmode)
        }
        //todo store selected options in preferences and initialize ui from that data here
    }

    private fun initDialog(
        @StringRes title: Int, optionsList: Array<String>) {
        val builder = AlertDialog.Builder(context)
        val checkedIndex = sharedPrefManager.getCheckedInteger()
        builder.setTitle(getString(title))
        builder.setSingleChoiceItems(optionsList, checkedIndex) { dialog, _ ->

            (dialog as AlertDialog).listView.checkedItemPosition.let { position ->
                if (optionsList.size >= position) {
                    when(position){
                        0 -> {
                            sharedPrefManager.saveCheckedInteger(position)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            sharedPrefManager.enableLightMode()
                            initializeUi()
                            dialog.dismiss()
                        }
                        1 -> {
                            sharedPrefManager.saveCheckedInteger(position)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            sharedPrefManager.enableDarkMode()
                            initializeUi()
                            dialog.dismiss()
                        }
                    }
                }
            }
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()


    }

    private fun observeUiInteractions() {
        binding.themeTransfer.setOnClickListener {
            val darkModeTheme = resources.getStringArray(R.array.dark_mode_theme)
            initDialog(R.string.lightmode, darkModeTheme)
        }

        binding.wallpaperTransfer.setOnClickListener {

        }

        binding.enterIsSend.setOnClickListener {

        }

        binding.mediaVisibility.setOnClickListener {

        }

        binding.fontSizeLayout.setOnClickListener {

        }

        binding.archivedChatsLayout.setOnClickListener {

        }
        binding.languageLayout.setOnClickListener {

        }
        binding.backupLayout.setOnClickListener {

        }
        binding.chatHistoryLayout.setOnClickListener {

        }
    }

    override fun observeViewModel() {

    }
}
