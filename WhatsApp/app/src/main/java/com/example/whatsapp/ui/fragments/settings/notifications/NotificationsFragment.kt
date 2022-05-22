package com.example.whatsapp.ui.fragments.settings.notifications

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.whatsapp.R
import com.example.whatsapp.base.BaseFragment
import com.example.whatsapp.databinding.FragmentNotificationsBinding
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

    private fun observeUiInteractions() {
        //Message Layouts
        binding.vibrateLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.vibrate))
            val vibrates = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            val checkedItem = 1
            builder.setSingleChoiceItems(vibrates, checkedItem) { dialog, _ ->
                when((dialog as AlertDialog).listView.checkedItemPosition){
                    0 -> binding.vibrateDesc.text = getString(R.string.off)
                    1 -> binding.vibrateDesc.text = getString(R.string.defaultanswer)
                    2 -> binding.vibrateDesc.text = getString(R.string.tvshort)
                    3 -> binding.vibrateDesc.text = getString(R.string.tvlong)

                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        binding.popUpLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.popupnotification))
            val popup = arrayOf(getString(R.string.nopopup), getString(R.string.screenon), getString(R.string.screenoff), getString(R.string.alwaysshow))
            val checkedItem = 1
            builder.setSingleChoiceItems(popup, checkedItem) { dialog, _ ->
                when((dialog as AlertDialog).listView.checkedItemPosition){
                    0 -> binding.vibrateDesc.text = getString(R.string.nopopup)
                    1 -> binding.vibrateDesc.text = getString(R.string.screenon)
                    2 -> binding.vibrateDesc.text = getString(R.string.screenoff)
                    3 -> binding.vibrateDesc.text = getString(R.string.alwaysshow)

                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        binding.lightLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.light))
            val lights = arrayOf(getString(R.string.none), getString(R.string.white), getString(R.string.red), getString(R.string.yellow), getString(R.string.green), getString(R.string.cyan), getString(R.string.blue), getString(R.string.purple))
            val checkedItem = 1
            builder.setSingleChoiceItems(lights, checkedItem) { dialog, _ ->
                when((dialog as AlertDialog).listView.checkedItemPosition){
                    0 -> binding.vibrateDesc.text = getString(R.string.none)
                    1 -> binding.vibrateDesc.text = getString(R.string.white)
                    2 -> binding.vibrateDesc.text = getString(R.string.red)
                    3 -> binding.vibrateDesc.text = getString(R.string.yellow)
                    4 -> binding.vibrateDesc.text = getString(R.string.green)
                    5 -> binding.vibrateDesc.text = getString(R.string.cyan)
                    6 -> binding.vibrateDesc.text = getString(R.string.blue)
                    7 -> binding.vibrateDesc.text = getString(R.string.purple)

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
            builder.setTitle(getString(R.string.vibrate))
            val vibrate = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            val checkedItem = 1
            builder.setSingleChoiceItems(vibrate, checkedItem) { dialog, _ ->
                when((dialog as AlertDialog).listView.checkedItemPosition){
                    0 -> binding.vibrateDesc.text = getString(R.string.off)
                    1 -> binding.vibrateDesc.text = getString(R.string.defaultanswer)
                    2 -> binding.vibrateDesc.text = getString(R.string.tvshort)
                    3 -> binding.vibrateDesc.text = getString(R.string.tvlong)

                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        binding.popUpGroupLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.popupnotification))
            val popup = arrayOf(getString(R.string.nopopup), getString(R.string.screenon), getString(R.string.screenoff), getString(R.string.alwaysshow))
            val checkedItem = 1
            builder.setSingleChoiceItems(popup, checkedItem) { dialog, _ ->
                when((dialog as AlertDialog).listView.checkedItemPosition){
                    0 -> binding.vibrateDesc.text = getString(R.string.nopopup)
                    1 -> binding.vibrateDesc.text = getString(R.string.screenon)
                    2 -> binding.vibrateDesc.text = getString(R.string.screenoff)
                    3 -> binding.vibrateDesc.text = getString(R.string.alwaysshow)

                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        binding.lightGroupLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.light))
            val lights = arrayOf(getString(R.string.none), getString(R.string.white), getString(R.string.red), getString(R.string.yellow), getString(R.string.green), getString(R.string.cyan), getString(R.string.blue), getString(R.string.purple))
            val checkedItem = 1
            builder.setSingleChoiceItems(lights, checkedItem) { dialog, _ ->
                when((dialog as AlertDialog).listView.checkedItemPosition){
                    0 -> binding.vibrateDesc.text = getString(R.string.none)
                    1 -> binding.vibrateDesc.text = getString(R.string.white)
                    2 -> binding.vibrateDesc.text = getString(R.string.red)
                    3 -> binding.vibrateDesc.text = getString(R.string.yellow)
                    4 -> binding.vibrateDesc.text = getString(R.string.green)
                    5 -> binding.vibrateDesc.text = getString(R.string.cyan)
                    6 -> binding.vibrateDesc.text = getString(R.string.blue)
                    7 -> binding.vibrateDesc.text = getString(R.string.purple)

                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        //Calls Fragment
        binding.callsVibrateLayout.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.vibrate))
            val vibration = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            val checkedItem = 1
            builder.setSingleChoiceItems(vibration, checkedItem) { dialog, _ ->
                when((dialog as AlertDialog).listView.checkedItemPosition){
                    0 -> binding.vibrateDesc.text = getString(R.string.off)
                    1 -> binding.vibrateDesc.text = getString(R.string.defaultanswer)
                    2 -> binding.vibrateDesc.text = getString(R.string.tvshort)
                    3 -> binding.vibrateDesc.text = getString(R.string.tvlong)

                }
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }


    //    /**
//     * Vibrates the device. Used for providing feedback when the user performs an action.
//     */
//    fun vibrate(view: View) {
//        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
//    }
//    private fun defaultLed(){
//        val notificationManager= context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//        val notification_Channel_ID = "my_channel_id_01"
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel = NotificationChannel(
//                notification_Channel_ID,
//                "My Notifications",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//
//            // Configure the notification channel.
//
//            notificationChannel.description = "Channel description"
//            notificationChannel.enableLights(true)
//            notificationChannel.lightColor = Color.RED
//            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
//            notificationChannel.enableVibration(true)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//
//    }
    override fun observeViewModel() {
    }
}