package com.example.whatsapp.ui.fragments.settings.notifications

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
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

    private fun initDialog(@StringRes title : Int, optionsList : Array<String>, checkedIndex : Int, optionSelected : String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(title))
        builder.setSingleChoiceItems(optionsList, checkedIndex) { dialog, _ ->
            (dialog as AlertDialog).listView.checkedItemPosition?.let { position ->
                if(optionsList.size >= position){
                    when(optionSelected){
                        VIBRATE_OPTION -> binding.vibrateDesc.text = optionsList[position]
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
            initDialog(R.string.vibrate, vibrates, 0, VIBRATE_OPTION)
        }

        binding.popUpLayout.setOnClickListener {
            //todo get this from xml array and pass through constructor
            //todo get checkedIndex from sharedpreferences
            val popup = arrayOf(getString(R.string.nopopup), getString(R.string.screenon), getString(R.string.screenoff), getString(R.string.alwaysshow))
            initDialog(R.string.popupnotification, popup, 0, "")
        }

        binding.lightLayout.setOnClickListener {
            val lights = arrayOf(getString(R.string.none), getString(R.string.white), getString(R.string.red), getString(R.string.yellow), getString(R.string.green), getString(R.string.cyan), getString(R.string.blue), getString(R.string.purple))
            initDialog(R.string.light, lights, 0, "")
        }

        binding.highPriorityLayout.setOnClickListener {

        }

        //Group Layouts
        binding.groupVibrateLayout.setOnClickListener {
            val vibrate = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            initDialog(R.string.vibrate, vibrate, 0, "")
        }
        binding.popUpGroupLayout.setOnClickListener {
            val popup = arrayOf(getString(R.string.nopopup), getString(R.string.screenon), getString(R.string.screenoff), getString(R.string.alwaysshow))
            initDialog(R.string.popupnotification, popup, 0, "")
        }

        binding.lightGroupLayout.setOnClickListener {
            val lights = arrayOf(getString(R.string.none), getString(R.string.white), getString(R.string.red), getString(R.string.yellow), getString(R.string.green), getString(R.string.cyan), getString(R.string.blue), getString(R.string.purple))
            initDialog(R.string.light, lights, 0,"")
        }
        //Calls Fragment
        binding.callsVibrateLayout.setOnClickListener {
            val vibration = arrayOf(getString(R.string.off), getString(R.string.defaultanswer), getString(R.string.tvshort), getString(R.string.tvlong))
            initDialog(R.string.vibrate, vibration, 0,"")
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

    companion object{
        const val VIBRATE_OPTION = "VIBRATE_OPTION"
    }

}