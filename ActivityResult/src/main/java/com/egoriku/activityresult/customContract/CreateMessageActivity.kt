package com.egoriku.activityresult.customContract

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.activityresult.R
import com.egoriku.activityresult.customContract.contract.EXTRA_MESSAGE
import com.egoriku.activityresult.databinding.ActivityCreateMessageBinding
import com.egoriku.activityresult.ext.extraNotNull

class CreateMessageActivity : AppCompatActivity(R.layout.activity_create_message) {

    private val viewBinding by viewBinding(ActivityCreateMessageBinding::bind)

    private val extraMessage by extraNotNull<String>(EXTRA_MESSAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            viewBinding.message.setText(extraMessage)
            send.setOnClickListener {
                sendResult()
            }
        }
    }

    private fun sendResult() {
        val data = Intent().putExtra(EXTRA_MESSAGE, viewBinding.message.text.toString())
        setResult(RESULT_OK, data)
        finish()
    }
}