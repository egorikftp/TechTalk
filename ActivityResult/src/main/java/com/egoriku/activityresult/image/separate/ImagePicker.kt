package com.egoriku.activityresult.image.separate

import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.egoriku.activityresult.MIMETYPE_IMAGES

class ImagePicker(
    private val owner: LifecycleOwner,
    registry: ActivityResultRegistry,
    callback: (imageUri: Uri?) -> Unit
) {

    private var getContent = registry.register(
        RESULT_REGISTRY_KEY,
        owner,
        ActivityResultContracts.GetContent(),
        callback
    )

    fun selectImage() {
        if (!owner.lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.w(TAG, "Linked LifecycleOwner is destroyed")
            return
        }

        getContent.launch(MIMETYPE_IMAGES)
    }

    private companion object {

        const val TAG = "ImagePicker"
        const val RESULT_REGISTRY_KEY = "pick_image"
    }
}