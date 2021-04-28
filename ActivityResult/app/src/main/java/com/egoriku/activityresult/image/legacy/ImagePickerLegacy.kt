package com.egoriku.activityresult.image.legacy

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.clear
import coil.load
import com.egoriku.activityresult.MIMETYPE_IMAGES
import com.egoriku.activityresult.R
import com.egoriku.activityresult.databinding.ImagePickerBinding
import com.egoriku.activityresult.ext.toast

class ImagePickerLegacy : AppCompatActivity(R.layout.image_picker) {

    private val viewBinding by viewBinding(ImagePickerBinding::bind)

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        viewBinding.loadImage(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.pickImageActivity.setOnClickListener {
            pickImage.launch(MIMETYPE_IMAGES)
        }
    }

    private fun ImagePickerBinding.loadImage(uri: Uri?) {
        image.clear()
        image.load(uri) {
            listener(
                onError = { request, throwable ->
                    request.context.toast(throwable.message)
                }
            )
        }
    }
}