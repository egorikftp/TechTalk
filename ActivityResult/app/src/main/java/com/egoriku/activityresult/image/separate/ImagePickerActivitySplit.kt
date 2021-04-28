package com.egoriku.activityresult.image.separate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.clear
import coil.load
import com.egoriku.activityresult.R
import com.egoriku.activityresult.databinding.ImagePickerBinding

class ImagePickerActivitySplit : AppCompatActivity(R.layout.image_picker) {

    private val viewBinding by viewBinding(ImagePickerBinding::bind)

    private val imagePicker = ImagePicker(
        registry = activityResultRegistry,
        owner = this
    ) { imageUri ->
        with(viewBinding.image) {
            clear()
            load(imageUri) {
                listener(
                    onError = { request, throwable ->
                        Toast.makeText(request.context, throwable.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.pickImageActivity.setOnClickListener {
            imagePicker.selectImage()
        }
    }
}