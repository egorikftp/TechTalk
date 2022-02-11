package com.egoriku.activityresult.customContract.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.egoriku.activityresult.customContract.CreateMessageActivity
import com.egoriku.activityresult.ext.intentFor

class EditMessageContract : ActivityResultContract<String?, String?>() {

    override fun createIntent(context: Context, input: String?): Intent {
        return context.intentFor<CreateMessageActivity>(
            EXTRA_MESSAGE to input
        )
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        intent ?: return null
        if (resultCode != Activity.RESULT_OK) return null

        return intent.getStringExtra(EXTRA_MESSAGE)
    }
}