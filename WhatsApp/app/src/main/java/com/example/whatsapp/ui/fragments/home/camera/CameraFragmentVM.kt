package com.example.whatsapp.ui.fragments.home.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraFragmentVM @Inject constructor() : BaseViewModel() {
    private val _isPermissionGranted = MutableLiveData(false)
    val isPermissionGranted: LiveData<Boolean> = _isPermissionGranted

    fun setPermission(granted: Boolean) {
        _isPermissionGranted.value = granted
    }
}