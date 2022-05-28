package com.example.whatsapp.base

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.whatsapp.utils.Constants
import com.example.whatsapp.utils.Constants.CAMERA_FRAGMENT_KEY
import com.example.whatsapp.utils.Constants.SETTINGS_FRAGMENT_KEY
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    lateinit var binding: Binding
    lateinit var observer: MyLifecycleObserver

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            initArgs(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflateBinding(inflater)
        initViews()
        return binding.root
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

    open fun initArgs(arguments: Bundle) {}

    open fun initViews() {}

    fun initializeLifecycleObserver(fragmentKey: String) {
        observer = MyLifecycleObserver(requireActivity().activityResultRegistry, fragmentKey)
        lifecycle.addObserver(observer)
    }

    class MyLifecycleObserver(
        private val registry: ActivityResultRegistry,
        private val fragmentKey: String
    ) :
        DefaultLifecycleObserver {
        private lateinit var getContent: ActivityResultLauncher<String>
        private lateinit var cameraContent: ActivityResultLauncher<Uri>

        override fun onCreate(owner: LifecycleOwner) {
            when (fragmentKey) {
                SETTINGS_FRAGMENT_KEY -> {
                    getContent =
                        registry.register(
                            Constants.SELECT_PROFILE_IMAGE_KEY,
                            owner,
                            ActivityResultContracts.GetContent()
                        ) { uri ->
                            // Handle the returned Uri
                        }
                }
                CAMERA_FRAGMENT_KEY -> {
                    cameraContent = registry.register(
                            Constants.CAMERA_FRAGMENT_KEY,
                            owner,
                            ActivityResultContracts.TakePicture()
                        ) {

                        }
                }
            }
        }

        fun selectImage() {
            getContent.launch("image/*")
        }

        fun launchCamera(uri: Uri) {
            cameraContent.launch(uri)
        }
    }
}

