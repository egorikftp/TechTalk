package com.egoriku.activityresult.fragment.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.egoriku.activityresult.R
import com.egoriku.activityresult.fragment.REQUEST_KEY
import com.egoriku.activityresult.fragment.VALUE_SUB_TITLE
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SubTitleInputDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Sub Title")
            .setView(R.layout.edit_text)
            .setPositiveButton(android.R.string.ok) { _, _ -> sendResult() }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun sendResult() {
        val input = dialog?.findViewById<TextView>(android.R.id.text1)?.text.toString()

        setFragmentResult(REQUEST_KEY, bundleOf(VALUE_SUB_TITLE to input))
    }
}