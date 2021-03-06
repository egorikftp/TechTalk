package com.egoriku.activityresult.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.activityresult.R
import com.egoriku.activityresult.databinding.ActivityPermissionsBinding

class PermissionsActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityPermissionsBinding::bind)

    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            updatePermissionsState()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
        viewBinding.requestPermissions.setOnClickListener {
            requestPermissions.launch(permissions)
        }
        updatePermissionsState()
    }

    private fun updatePermissionsState() {
        val permissionsStates: Map<String, Boolean> = permissions.associateWith { permission ->
            ActivityCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
        val state = buildString {
            permissionsStates.forEach { (permission, granted) ->
                append("$permission - ${if (granted) "GRANTED" else "DENIED"}\n")
            }
        }

        with(viewBinding) {
            requestPermissions.isEnabled = permissionsStates.any { (_, granted) -> !granted }
            permissionsState.text = state
        }
    }

    companion object {

        private val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
}