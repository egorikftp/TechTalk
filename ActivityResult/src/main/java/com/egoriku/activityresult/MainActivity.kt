package com.egoriku.activityresult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.activityresult.customContract.MessageActivity
import com.egoriku.activityresult.databinding.ActivityMainBinding
import com.egoriku.activityresult.ext.intentFor
import com.egoriku.activityresult.fragment.FragmentResultApi
import com.egoriku.activityresult.image.ImagePickerActivity
import com.egoriku.activityresult.image.ImagePickerFragment
import com.egoriku.activityresult.image.legacy.ImagePickerLegacy
import com.egoriku.activityresult.image.separate.ImagePickerActivitySplit
import com.egoriku.activityresult.permissions.PermissionsActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            pickImageActivityLegacy.setOnClickListener {
                startActivity(this@MainActivity.intentFor<ImagePickerLegacy>())
            }

            pickImageActivity.setOnClickListener {
                startActivity(this@MainActivity.intentFor<ImagePickerActivity>())
            }

            pickImageFragment.setOnClickListener {
                supportFragmentManager.commit {
                    replace(R.id.container, ImagePickerFragment(), "picker")
                    addToBackStack("picker")
                }
            }

            pickImageSplit.setOnClickListener {
                startActivity(this@MainActivity.intentFor<ImagePickerActivitySplit>())
            }

            customContract.setOnClickListener {
                startActivity(this@MainActivity.intentFor<MessageActivity>())
            }

            permissions.setOnClickListener {
                startActivity(this@MainActivity.intentFor<PermissionsActivity>())
            }

            fragmentResult.setOnClickListener {
                supportFragmentManager.commit {
                    replace(R.id.container, FragmentResultApi(), "fragment_result")
                    addToBackStack("fragment_result")
                }
            }
        }
    }
}