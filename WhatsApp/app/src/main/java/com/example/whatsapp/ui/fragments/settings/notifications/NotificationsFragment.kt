package com.example.whatsapp.ui.fragments.settings.notifications

import android.app.AlertDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(){
    private lateinit var navController: NavController


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
    /**
     * Vibrates the device. Used for providing feedback when the user performs an action.
     */
    fun vibrate(view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
    }
    private fun defaultLed(){
        val notificationManager= context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notification_Channel_ID = "my_channel_id_01"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                notification_Channel_ID,
                "My Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )

            // Configure the notification channel.

            notificationChannel.description = "Channel description"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }
    private fun observeUiInteractions() {
        //Message Layouts
        binding.vibrateLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Vibrate")
            val vibrates = arrayOf("Off", "Default", "Short", "Long")
            val checkedItem = 1
            builder.setSingleChoiceItems(vibrates, checkedItem) { dialog, which ->
                val i = (dialog as AlertDialog).listView.checkedItemPosition
                if (i == 0) {
                    binding.vibrateDesc.text = getString(R.string.off)
                } else if (i == 1) {
                    binding.vibrateDesc.text = getString(R.string.defaultanswer)
                } else if (i == 2) {
                    binding.vibrateDesc.text = getString(R.string.tvshort)
                } else if (i == 3) {
                    binding.vibrateDesc.text = getString(R.string.tvlong)
                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        binding.popUpLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Popup notification")
            val popup = arrayOf("No popup", "Only when screen is 'on' ", "Only when screen is 'off'", "Always show popup")
            val checkedItem = 1
            builder.setSingleChoiceItems(popup, checkedItem) { dialog, which ->
                val i = (dialog as AlertDialog).listView.checkedItemPosition
                if (i == 0) {
                    binding.tvPopUpTonesDesc.text = getString(R.string.nopopup)
                } else if (i == 1) {
                    binding.tvPopUpTonesDesc.text = getString(R.string.screenon)
                } else if (i == 2) {
                    binding.tvPopUpTonesDesc.text = getString(R.string.screenoff)
                } else if (i == 3) {
                    binding.tvPopUpTonesDesc.text = getString(R.string.alwaysshow)
                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        binding.lightLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Light")
            val light = arrayOf("None", "White", "Red", "Yellow", "Green", "Cyan", "Blue", "Purple")
            val checkedItem = 1
            builder.setSingleChoiceItems(light, checkedItem) { dialog, which ->
                val i = (dialog as AlertDialog).listView.checkedItemPosition
                    if (i == 0) {
                        binding.tvLightDesc.text = getString(R.string.none)
                    } else if (i == 1) {
                        binding.tvLightDesc.text = getString(R.string.white)
                    } else if (i == 2) {
                        binding.tvLightDesc.text = getString(R.string.red)
                    } else if (i == 3) {
                        binding.tvLightDesc.text = getString(R.string.yellow)
                    } else if (i == 4) {
                        binding.tvLightDesc.text = getString(R.string.green)
                    } else if (i == 5) {
                        binding.tvLightDesc.text = getString(R.string.cyan)
                    } else if (i == 6) {
                        binding.tvLightDesc.text = getString(R.string.blue)
                    } else if (i == 7) {
                        binding.tvLightDesc.text = getString(R.string.purple)
                    }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        binding.highPriorityLayout.setOnClickListener {

        }

        //Group Layouts
        binding.groupVibrateLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Vibrate")
            val vibrate = arrayOf("Off", "Default", "Short", "Long")
            val checkedItem = 1
            builder.setSingleChoiceItems(vibrate, checkedItem) { dialog, which ->
                val i = (dialog as AlertDialog).listView.checkedItemPosition
                if (i == 0) {
                    binding.tvGroupVibrateDesc.text = getString(R.string.off)
                } else if (i == 1) {
                    binding.tvGroupVibrateDesc.text = getString(R.string.defaultanswer)
                } else if (i == 2) {
                    binding.tvGroupVibrateDesc.text = getString(R.string.tvshort)
                } else if (i == 3) {
                    binding.tvGroupVibrateDesc.text = getString(R.string.tvlong)
                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        binding.popUpGroupLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Popup notification")
            val popup = arrayOf("No popup", "Only when screen is 'on' ", "Only when screen is 'off'", "Always show popup")
            val checkedItem = 1
            builder.setSingleChoiceItems(popup, checkedItem) { dialog, which ->
                val i = (dialog as AlertDialog).listView.checkedItemPosition
                if (i == 0) {
                    binding.tvPopUpGroupTonesDesc.text = getString(R.string.nopopup)
                } else if (i == 1) {
                    binding.tvPopUpGroupTonesDesc.text = getString(R.string.screenon)
                } else if (i == 2) {
                    binding.tvPopUpGroupTonesDesc.text = getString(R.string.screenoff)
                } else if (i == 3) {
                    binding.tvPopUpGroupTonesDesc.text = getString(R.string.alwaysshow)
                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        binding.lightGroupLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Light")
            val lights = arrayOf("None", "White", "Red", "Yellow", "Green", "Cyan", "Blue", "Purple")
            val checkedItem = 1
            builder.setSingleChoiceItems(lights, checkedItem) { dialog, which ->
                val i = (dialog as AlertDialog).listView.checkedItemPosition
                if (i == 0) {
                    binding.tvLightGroupDesc.text = getString(R.string.none)
                } else if (i == 1) {
                    binding.tvLightGroupDesc.text = getString(R.string.white)
                } else if (i == 2) {
                    binding.tvLightGroupDesc.text = getString(R.string.red)
                } else if (i == 3) {
                    binding.tvLightGroupDesc.text = getString(R.string.yellow)
                } else if (i == 4) {
                    binding.tvLightGroupDesc.text = getString(R.string.green)
                } else if (i == 5) {
                    binding.tvLightGroupDesc.text = getString(R.string.cyan)
                } else if (i == 6) {
                    binding.tvLightGroupDesc.text = getString(R.string.blue)
                } else if (i == 7) {
                    binding.tvLightGroupDesc.text = getString(R.string.purple)
                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        //Calls Fragment
        binding.callsVibrateLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Vibrate")
            val vibration = arrayOf("Off", "Default", "Short", "Long")
            val checkedItem = 1
            builder.setSingleChoiceItems(vibration, checkedItem) { dialog, which ->
                val i = (dialog as AlertDialog).listView.checkedItemPosition
                if (i == 0) {
                    binding.tvCallsVibrateDesc.text = getString(R.string.off)
                } else if (i == 1) {
                    binding.tvCallsVibrateDesc.text = getString(R.string.defaultanswer)
                } else if (i == 2) {
                    binding.tvCallsVibrateDesc.text = getString(R.string.tvshort)
                } else if (i == 3) {
                    binding.tvCallsVibrateDesc.text = getString(R.string.tvlong)
                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }



    override fun observeViewModel() {
    }
}