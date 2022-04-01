package com.example.whatsapp.ui.fragments.home

import com.example.whatsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

interface Inputs {

}

interface Outputs {

}

@HiltViewModel
class HomeFragmentVM : BaseViewModel(), Inputs, Outputs {

    val inputs : Inputs = this
    val outputs : Outputs = this

    init {

    }

    //region Inputs

    //endregion

    //region Outputs

    //endregion

}