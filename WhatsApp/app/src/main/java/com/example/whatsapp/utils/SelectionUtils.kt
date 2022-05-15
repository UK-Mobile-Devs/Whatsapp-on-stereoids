package com.example.whatsapp.utils

import androidx.recyclerview.selection.MutableSelection
import androidx.recyclerview.selection.SelectionTracker

fun <T> SelectionTracker<T>.getSelectionFromTracker() : List<T> {
    val copy = MutableSelection<T>()
    this.copySelection(copy)
    return copy.toList()
}

