package com.example.whatsapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {

    lateinit var binding: Binding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        intent?.extras?.let {
            intentExtras(it)
        }
        super.onCreate(savedInstanceState)
        binding = inflateBinding(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }

    abstract fun inflateBinding(layoutInflater: LayoutInflater): Binding

    abstract fun observeViewModel()

    open fun intentExtras(args: Bundle) {}

    open fun initViews() { }

}