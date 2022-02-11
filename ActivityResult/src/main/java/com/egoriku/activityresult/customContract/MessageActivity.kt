package com.egoriku.activityresult.customContract

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.activityresult.R
import com.egoriku.activityresult.customContract.contract.EditMessageContract
import com.egoriku.activityresult.databinding.ActivityDisplayBinding

class MessageActivity : AppCompatActivity(R.layout.activity_display) {

    private val viewBinding by viewBinding(ActivityDisplayBinding::bind)

    private val getMessage = registerForActivityResult(EditMessageContract()) {
        viewBinding.message.text = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.getMessage.setOnClickListener {
            getMessage.launch(viewBinding.message.text.toString())
        }
    }
}