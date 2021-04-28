package com.egoriku.activityresult.image

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.clear
import coil.load
import com.egoriku.activityresult.MIMETYPE_IMAGES
import com.egoriku.activityresult.R
import com.egoriku.activityresult.databinding.ImagePickerBinding
import com.egoriku.activityresult.ext.toast

class ImagePickerFragment : Fragment(R.layout.image_picker) {

    private val viewBinding by viewBinding(ImagePickerBinding::bind)

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { contentUri ->
            with(viewBinding.image) {
                clear()
                load(contentUri) {
                    listener(
                        onError = { request, throwable ->
                            request.context.toast(throwable.message)
                        }
                    )
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.pickImageActivity.setOnClickListener {
            pickImage.launch(MIMETYPE_IMAGES)
        }
    }
}

